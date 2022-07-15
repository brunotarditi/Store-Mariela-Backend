package com.library.mariela.productservice.services;

import com.library.commonsservice.services.ICommonService;
import com.library.mariela.productservice.dtos.ProductDto;
import com.library.mariela.productservice.dtos.PurchaseStockControlDto;
import com.library.mariela.productservice.entities.Product;
import com.library.mariela.productservice.dtos.StockControlDto;

import java.util.List;
import java.util.Map;

public interface IProductService extends ICommonService<Product, ProductDto> {
    List<ProductDto> getProductBrandById(Long brandId);
    void saveStock(Long productId, StockControlDto stockControl, PurchaseStockControlDto purchaseStock);
    PurchaseStockControlDto savePurchase(Long productId, PurchaseStockControlDto purchaseStock);
    Map<String, Object> getProductWithStockAndPurchases(Long productId);
    Map<String, Object> getAllProductsWithStocks();
}
