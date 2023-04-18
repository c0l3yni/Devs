package com.tekgs.nextgen.tekegg.data.product;

public interface ProductCalibratable {
    
    String getDescription();
    
    boolean equivalent(ProductCalibratable comparator);
    
    Integer getStock();
    
    String getTitle();
    
    String getId();
    
    Integer getPrice();

    String getThumbnail();
}
