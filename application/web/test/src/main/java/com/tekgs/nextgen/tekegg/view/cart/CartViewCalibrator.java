package com.tekgs.nextgen.tekegg.view.cart;

import com.softwareonpurpose.calibrator4test.Calibrator;
import com.softwareonpurpose.uinavigator.UiRegion;
import com.tekgs.nextgen.tekegg.view.cart.item.ItemRegionCalibratable;
import com.tekgs.nextgen.tekegg.view.cart.item.ItemRegionCalibrator;

import java.util.List;

public class CartViewCalibrator extends Calibrator {
    private static final String DESCRIPTION = "'Cart' view";
    private final CartViewExpected expected;
    private final CartView actual;

    protected CartViewCalibrator(CartViewExpected expected, CartView actual) {
        super(DESCRIPTION, expected, actual);
        this.expected = expected;
        this.actual = actual;
        UiRegion.suppressConstructionLogging(true);
        List<ItemRegionCalibratable> expectedItems = expected.inItems().getItems();
        List<ItemRegionCalibratable> actualItems = actual.inItems().getItems();
        addCalibrationsExpected(expectedItems, actualItems);
        addCalibrationsUnexpected(actualItems);
    }

    public static CartViewCalibrator getInstance(CartViewExpected expected, CartView actual) {
        return new CartViewCalibrator(expected, actual);
    }

    private void addCalibrationsExpected(List<ItemRegionCalibratable> expectedItems, List<ItemRegionCalibratable> actualItems) {
        expectedItems.forEach(expectedItem -> {
            ItemRegionCalibratable itemFound = addCalibrationsFound(actualItems, expectedItem);
            if (itemFound == null) {
                addChildCalibrator(ItemRegionCalibrator.getInstance(expectedItem, null));
            } else {
                actualItems.remove(itemFound);
            }
        });
    }

    private ItemRegionCalibratable addCalibrationsFound(List<ItemRegionCalibratable> actualItems, ItemRegionCalibratable expectedItem) {
        ItemRegionCalibratable itemFound = actualItems.stream()
                .filter(actualItem -> actualItem.equivalent(expectedItem))
                .findFirst()
                .orElse(null);
        if (itemFound != null) {
            addChildCalibrator(ItemRegionCalibrator.getInstance(expectedItem, itemFound));
        }
        return itemFound;
    }

    private void addCalibrationsUnexpected(List<ItemRegionCalibratable> actualItems) {
        actualItems.forEach(itemUnexpected -> addChildCalibrator(ItemRegionCalibrator.getInstance(null, itemUnexpected)));
    }

    @Override
    protected void executeVerifications() {
        verify("'Total' amount", this.expected.getTotalAmount(), this.actual.getTotalAmount());
        verify("'Empty' message", this.expected.getEmptyMessage(), this.actual.getEmptyMessage());
        verify("'Minimum Total' message", this.expected.getMinimumTotalMessage(), this.actual.getMinimumTotalMessage());
        verify("'Checkout button' enabled", this.expected.isButtonEnabled(), this.actual.isButtonEnabled());
        verify("'Maximum Total' message", this.expected.getMaximumTotalMessage(), this.actual.getMaximumTotalMessage());
    }


}
