package com.library.mariela.productservice.services;

import com.library.commonsservice.services.CommonService;
import com.library.mariela.productservice.clients.IHistoricalPurchaseFeignClient;
import com.library.mariela.productservice.clients.IStockControlFeignClient;
import com.library.mariela.productservice.customExceptions.MyException;
import com.library.mariela.productservice.factories.ProductFactory;
import com.library.mariela.productservice.dtos.ProductDto;
import com.library.mariela.productservice.entities.Product;
import com.library.mariela.productservice.models.HistoricalPurchase;
import com.library.mariela.productservice.models.StockControl;
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
        List<Product> products = this.repository.findProductByBrandId(brandId);
        return products
                .stream()
                .map(iFactory::createDto)
                .collect(Collectors.toList());
    }

    @Override
    public HistoricalPurchase savePurchase(Long productId, HistoricalPurchase purchase) {
        Optional<StockControl> stock = stockControlFeignClient.getStockControlByProductId(productId);
        purchase.setProductId(productId);
        HistoricalPurchase purchaseSave = this.purchaseFeignClient.save(purchase);
        saveStock(productId, stock.orElse(null), purchase);
        return purchaseSave;
    }

    @Override
    public void saveStock(Long productId, StockControl stockControl, HistoricalPurchase purchase) {
        try {
            if (stockControl == null) {
                stockControl = new StockControl();
                stockControl.setProductId(productId);
                stockControl.setMinimum(1);
                stockControl.setCurrent(purchase.getQuantity());
                this.stockControlFeignClient.save(stockControl);

            } else {
                stockControl.setMinimum(1);
                stockControl.setCurrent(stockControl.getCurrent() + purchase.getQuantity());
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
    public Map<String, Object> getProductsWithStocksAndPurchases(Long productId) {
        Map<String, Object> results = new HashMap<>();
        Optional<ProductDto> productDto = this.findById(productId);
        if (productDto.isEmpty()) {
            results.put("Message", "No existe el producto.");
            return results;
        }
        results.put("Product", productDto);
        List<HistoricalPurchase> purchases = purchaseFeignClient.getHistoricalPurchaseByProductId(productId);
        if (purchases.isEmpty()) {
            results.put("Purchases", "No hay compras de este producto.");
        } else {
            results.put("Purchases", purchases);
        }
        Optional<StockControl> stock = stockControlFeignClient.getStockControlByProductId(productId);
        if (stock.isEmpty()) {
            results.put("Stocks", "No hay stock para este producto.");
        } else {
            results.put("Stock", stock);
        }
        return results;
    }

}
