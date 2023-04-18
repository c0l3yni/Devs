package com.tekgs.nextgen.tekegg.data.cart.product;

public interface ProductCalibratable {
    
    boolean equivalent(ProductCalibratable comparator);
    
    String getId();
}
