package com.tekgs.nextgen.tekegg.data.cart;

import com.softwareonpurpose.calibrator4test.Calibrator;
import com.tekgs.nextgen.tekegg.data.cart.item.ItemCalibratable;
import com.tekgs.nextgen.tekegg.data.cart.item.ItemCalibrator;

import java.util.ArrayList;
import java.util.List;

public class CartCalibrator extends Calibrator {
    private static final String DESCRIPTION = "Cart";
    private final CartCalibratable expected;
    private final CartCalibratable actual;
    
    private CartCalibrator(CartCalibratable expected, CartCalibratable actual) {
        super(DESCRIPTION, expected, actual);
        this.expected = expected;
        this.actual = actual;
        List<ItemCalibratable> expectedItemList = expected == null ? new ArrayList<>() : expected.getItems();
        List<ItemCalibratable> actualItemList = actual.getItems();
        addCalibrationsExpected(expectedItemList, actualItemList);
        addCalibrationsUnexpected(actualItemList);
    }
    
    public static CartCalibrator getInstance(CartCalibratable expected, CartCalibratable actual) {
        return new CartCalibrator(expected, actual);
    }

    private void addCalibrationsExpected(List<ItemCalibratable> expectedItemList, List<ItemCalibratable> actualItemList) {
        expectedItemList.forEach(expectedItem -> {
            ItemCalibratable itemFound = addCalibrationsFound(actualItemList, expectedItem);
            if (itemFound == null) {
                addChildCalibrator(ItemCalibrator.getInstance(expectedItem, null));
            } else {
                actualItemList.remove(itemFound);
            }
        });
    }

    private ItemCalibratable addCalibrationsFound(List<ItemCalibratable> actualItemList, ItemCalibratable expectedItem) {
        ItemCalibratable itemFound = actualItemList.stream()
                .filter(actualItem -> actualItem.equivalent(expectedItem))
                .findFirst()
                .orElse(null);
        if (itemFound != null) {
            addChildCalibrator(ItemCalibrator.getInstance(expectedItem, itemFound));
        }
        return itemFound;
    }

    private void addCalibrationsUnexpected(List<ItemCalibratable> actualItemList) {
        actualItemList.forEach(itemUnexpected -> addChildCalibrator(ItemCalibrator.getInstance(null, itemUnexpected)));
    }
    
    @Override
    protected void executeVerifications() {
        verify("'Cart' id ", this.expected.getId(), this.actual.getId());
    }
}
