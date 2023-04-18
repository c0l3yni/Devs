package com.tekgs.nextgen.tekegg.data.cart.item;

import com.softwareonpurpose.calibrator4test.Calibrator;
import com.tekgs.nextgen.tekegg.data.product.ProductCalibrator;

public class ItemCalibrator extends Calibrator {

    private static final String DESCRIPTION = "'Item'";
    private final ItemCalibratable expected;
    private final ItemCalibratable actual;

    protected ItemCalibrator(ItemCalibratable expected, ItemCalibratable actual) {
        super(DESCRIPTION, expected, actual);
        this.expected = expected;
        this.actual = actual;
        addChildCalibrator(ProductCalibrator.getInstance(expected.getProduct(), actual.getProduct()));
    }

    public static Calibrator getInstance(ItemCalibratable expectedItem, ItemCalibratable actualItem){
        return new ItemCalibrator(expectedItem,actualItem);
    }


    @Override
    protected void executeVerifications() {
        verify("'Item' Quantity", expected.getQuantity(),actual.getQuantity());
    }
}
