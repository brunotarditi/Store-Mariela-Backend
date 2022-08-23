package com.library.mariela.productservice.services;

import com.library.commonsservice.dtos.BaseDto;
import com.library.commonsservice.services.CommonService;
import com.library.mariela.productservice.clients.IHistoricalPurchaseFeignClient;
import com.library.mariela.productservice.clients.IStockControlFeignClient;
import com.library.mariela.productservice.customExceptions.MyException;
import com.library.mariela.productservice.dtos.PurchaseStockControlDto;
import com.library.mariela.productservice.factories.ProductFactory;
import com.library.mariela.productservice.dtos.ProductDto;
import com.library.mariela.productservice.entities.Product;
import com.library.mariela.productservice.dtos.HistoricalPurchaseDto;
import com.library.mariela.productservice.dtos.StockControlDto;
import com.library.mariela.productservice.repositories.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService extends CommonService<Product, IProductRepository, ProductDto> implements IProductService {

    private final IHistoricalPurchaseFeignClient purchaseFeignClient;
    private final IStockControlFeignClient stockControlFeignClient;

    public ProductService(IProductRepository iProductRepository, ProductFactory productFactory, IHistoricalPurchaseFeignClient purchaseFeignClient, IStockControlFeignClient stockControlFeignClient) {
        super(iProductRepository, productFactory);
        this.purchaseFeignClient = purchaseFeignClient;
        this.stockControlFeignClient = stockControlFeignClient;

    }

    @Override
    public List<ProductDto> getProductBrandById(Long brandId) {
        List<Product> products = this.repository.findProductByBrandIdEnabled(brandId);
        return products
                .stream()
                .map(iFactory::createDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductCategoryById(Long categoryId) {
        List<Product> products = this.repository.findProductByCategoryIdEnabled(categoryId);
        return products
                .stream()
                .map(iFactory::createDto)
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseStockControlDto savePurchase(Long productId, PurchaseStockControlDto purchaseStock) {
        Optional<StockControlDto> stock = this.stockControlFeignClient.getStockControlByProductId(productId);
        HistoricalPurchaseDto purchaseDto = new HistoricalPurchaseDto();
        if (purchaseStock.isHasIva())
            purchaseDto.setCostPrice(purchaseStock.getCostPrice());
        else
            purchaseDto.setCostPrice(purchaseStock.getCostPrice() * 1.21);
        purchaseDto.setHasIva(purchaseStock.isHasIva());
        purchaseDto.setQuantity(purchaseStock.getQuantity());
        purchaseDto.setProductId(productId);
        this.purchaseFeignClient.save(purchaseDto);
        saveStock(productId, stock.orElse(null), purchaseStock);
        return purchaseStock;
    }

    @Override
    public void saveStock(Long productId, StockControlDto stockControl, PurchaseStockControlDto purchaseStock) {
        try {
            if (stockControl == null) {
                stockControl = new StockControlDto();
                stockControl.setProductId(productId);
                stockControl.setMinimum(purchaseStock.getMinimum());
                stockControl.setCurrent(purchaseStock.getQuantity());
                stockControl.setPercent(purchaseStock.getPercent());
                stockControl.setListOfPrice(purchaseStock.getCostPrice());
                this.stockControlFeignClient.save(stockControl);

            } else {
                stockControl.setMinimum(stockControl.getMinimum());
                stockControl.setCurrent(stockControl.getCurrent() + purchaseStock.getQuantity());
                stockControl.setPercent(stockControl.getPercent());
                stockControl.setListOfPrice(purchaseStock.getCostPrice());
                stockControl.setCreateAt(stockControl.getCreateAt());
                stockControl.setUpdateAt(new Date());
                stockControl.setProductId(productId);
                this.stockControlFeignClient.save(stockControl);
            }
        } catch (MyException e) {
            throw new MyException("No pudo guardarse el stock.");
        }
    }

    @Override
    public Map<String, Object> getProductWithStockAndPurchases(Long productId) {
        Map<String, Object> results = new HashMap<>();
        Optional<ProductDto> productDto = this.findById(productId);
        if (productDto.isEmpty()) {
            results.put("Message", "No existe el producto.");
            return results;
        }
        if (!productDto.get().isEnabled()) {
            results.put("Message", "No existe el producto.");
            return results;
        }
        results.put("Product", productDto);
        List<HistoricalPurchaseDto> purchases = purchaseFeignClient.getHistoricalPurchaseByProductId(productId);
        if (purchases.isEmpty()) {
            results.put("Purchases", "No hay compras de este producto.");
        } else {
            results.put("Purchases", purchases);
        }
        Optional<StockControlDto> stock = stockControlFeignClient.getStockControlByProductId(productId);
        if (stock.isEmpty()) {
            results.put("Stocks", "No hay stock para este producto.");
        } else {
            results.put("Stock", stock);
        }
        return results;
    }

    @Override
    public Map<String, Object> getAllProductsWithStocks() {
        Map<String, Object> results = new HashMap<>();
        List<ProductDto> allProductsDtos = this.findAll();
        List<ProductDto> productsDtosAvailable = allProductsDtos
                .stream()
                .filter(BaseDto::isEnabled)
                .collect(Collectors.toList());
        productsDtosAvailable
                .forEach(p -> stockControlFeignClient.getStockControlByProductId(p.getId())
                        .ifPresent(stockControl -> {
                            p.setStockControl(stockControl);
                            p.setPrice(stockControl.getListOfPrice() * ((float) stockControl.getPercent() / 100) + stockControl.getListOfPrice());
                        }));
        results.put("Products", productsDtosAvailable);
        return results;
    }

    @Override
    public Optional<StockControlDto> getStockControl(Long productId) {
        return stockControlFeignClient.getStockControlByProductId(productId);
    }

}
