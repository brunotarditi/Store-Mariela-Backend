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
    public void saveStock(Long productId, Optional<StockControl> stockControl, int minimum, HistoricalPurchase purchase) {
        if (stockControl.isEmpty()) {
            StockControl stock = new StockControl();
            stock.setMinimum(minimum);
            stock.setCurrent(purchase.getQuantity());
            stock.setProductId(productId);
            this.stockControlFeignClient.save(stock);
        }  else {
            stockControl.get().setMinimum(minimum);
            stockControl.get().setCurrent(stockControl.get().getCurrent() + purchase.getQuantity());
            stockControl.get().setCreateAt(stockControl.get().getCreateAt());
            stockControl.get().setUpdateAt(new Date());
            stockControl.get().setProductId(productId);
            this.stockControlFeignClient.save(stockControl.get());
        }
    }

    @Override
    public HistoricalPurchase savePurchase(Long productId, int minimum, HistoricalPurchase purchase) {
        Optional<StockControl> stock = this.stockControlFeignClient.getStockControlByProductId(productId);
        try{
            saveStock(productId, stock, minimum, purchase);
            purchase.setProductId(productId);
            return this.purchaseFeignClient.save(purchase);
        }catch (MyException e){
            throw new MyException("No se pudo guardar la compra y el stock.");
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
