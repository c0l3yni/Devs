package com.tekgs.nextgen.tekegg.data.cart.product;

import com.softwareonpurpose.calibrator4test.Calibrator;

public class ProductCalibrator extends Calibrator {
    private static final String DESCRIPTION = "'Product'";
    private final ProductCalibratable expected;
    private final ProductCalibratable actual;
    
    public ProductCalibrator(ProductCalibratable expected, ProductCalibratable actual) {
        super(DESCRIPTION, expected, actual);
        this.expected = expected;
        this.actual = actual;
    }
    
    public static Calibrator getInstance(ProductCalibratable expected, ProductCalibratable actual) {
        return new ProductCalibrator(expected, actual);
    }
    
    @Override
    protected void executeVerifications() {
        verify("'Product' id ", this.expected.getId(), this.actual.getId());
        verify("'Product' title ", this.expected.getTitle(), this.actual.getTitle());
        verify("'Product' stock ", this.expected.getStock(), this.actual.getStock());
        verify("'Product' price ", this.expected.getPrice(), this.actual.getPrice());
        verify("'Product' description ", this.expected.getDescription(), this.actual.getDescription());
        verify("'Thumbnail' source", this.expected.getThumbnail(), this.actual.getThumbnail());
    }
}
