package com.tekgs.nextgen.tekegg.view.cart.item;

import com.softwareonpurpose.calibrator4test.Calibrator;

public class ItemRegionCalibrator extends Calibrator {
    private static final String DESCRIPTION = "'Item' region";
    private final ItemRegionCalibratable expected;
    private final ItemRegionCalibratable actual;

    protected ItemRegionCalibrator(ItemRegionCalibratable expected, ItemRegionCalibratable actual) {
        super(DESCRIPTION, expected, actual);
        this.expected = expected;
        this.actual = actual;
    }

    public static Calibrator getInstance(ItemRegionCalibratable expected, ItemRegionCalibratable actual) {
        return new ItemRegionCalibrator(expected, actual);
    }

    @Override
    protected void executeVerifications() {
        verify("Product Description", expected.getProductDescription(), actual.getProductDescription());
        verify("Item Total", expected.getItemTotal(), actual.getItemTotal());
        verify("Unit price", expected.getUnitPrice(), actual.getUnitPrice());
        verify("Quantity", expected.getQuantity(), actual.getQuantity());
        verify("'Thumbnail' source", expected.getThumbnailSrc(), actual.getThumbnailSrc());
    }
}
