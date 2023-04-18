package com.tekgs.nextgen.tekegg.view.products;

import com.tekgs.nextgen.tekegg.region.product.ProductListRegionCalibratable;

public interface ProductsViewCalibratable {
    ProductListRegionCalibratable inProducts();

    String getEmptyMessage();
}
