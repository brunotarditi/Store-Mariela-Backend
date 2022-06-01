package com.library.mariela.productservice.services;

import com.library.commonsservice.services.ICommonService;
import com.library.mariela.productservice.dtos.ProductDto;
import com.library.mariela.productservice.entities.Product;
import com.library.mariela.productservice.models.HistoricalPurchase;
import com.library.mariela.productservice.models.StockControl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IProductService extends ICommonService<Product, ProductDto> {
    List<ProductDto> getProductBrandById(Long brandId);
    void saveStock(Long productId, Optional<StockControl> stockControl, HistoricalPurchase purchase);
    HistoricalPurchase savePurchase(Long productId, HistoricalPurchase purchase);
    Map<String, Object> getProductsWithStocksAndPurchases(Long productId);
}
