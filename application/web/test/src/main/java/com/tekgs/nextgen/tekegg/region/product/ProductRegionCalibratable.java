package com.tekgs.nextgen.tekegg.region.product;

public interface ProductRegionCalibratable {
    boolean equivalent(ProductRegionCalibratable comparator);

    String getProductDescription();

    String getOutOfStockMessage();

    String getButtonDisabledStatus();

}
