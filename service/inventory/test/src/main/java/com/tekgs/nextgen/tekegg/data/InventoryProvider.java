package com.tekgs.nextgen.tekegg.data;

import com.tekgs.nextgen.tekegg.data.product.Product;
import com.tekgs.nextgen.tekegg.data.product.ProductCalibratable;

import java.util.List;

public class InventoryProvider {

    public static InventoryProvider getInstance(){
        return new InventoryProvider();
    }

    public List<Product> get(){
        return InventoryRepository.getInstance().queryAll();
    }

    public Product get(ProductCalibratable productDefinition){
        return InventoryRepository.getInstance().query(productDefinition);
    }
}
