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
    }
}
