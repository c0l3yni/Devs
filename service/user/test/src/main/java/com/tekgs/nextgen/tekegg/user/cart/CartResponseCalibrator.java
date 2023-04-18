package com.tekgs.nextgen.tekegg.user.cart;

import com.softwareonpurpose.calibrator4test.Calibrator;
import com.tekgs.nextgen.tekegg.data.cart.CartCalibratable;
import com.tekgs.nextgen.tekegg.data.cart.CartCalibrator;

import java.util.ArrayList;
import java.util.List;

public class CartResponseCalibrator extends Calibrator {
    private static final String DESCRIPTION = "'Cart' response";
    private final CartResponseExpected expected;
    private final CartResponse actual;

    protected CartResponseCalibrator(CartResponseExpected expected, CartResponse actual) {
        super(DESCRIPTION, expected, actual);
        this.expected = expected;
        this.actual = actual;
        List<CartCalibratable> expectedCartList = expected.getCarts();
        List<CartCalibratable> actualCartList = new ArrayList<>(actual.getCarts());
        addCalibrationsExpected(expectedCartList, actualCartList);
        addCalibrationsUnexpected(actualCartList);
    }

    public static CartResponseCalibrator getInstance(CartResponseExpected expected, CartResponse actual) {
        return new CartResponseCalibrator(expected, actual);
    }

    private void addCalibrationsExpected(List<CartCalibratable> expectedCartList, List<CartCalibratable> actualCartList) {
        expectedCartList.forEach(expectedCart -> {
            CartCalibratable cartFound = addCalibrationsFound(actualCartList, expectedCart);
            if (cartFound == null) {
                addChildCalibrator(CartCalibrator.getInstance(expectedCart, null));
            } else {
                actualCartList.remove(cartFound);
            }
        });
    }

    private CartCalibratable addCalibrationsFound(List<CartCalibratable> actualCartList, CartCalibratable expectedCart) {
        CartCalibratable cartFound = actualCartList.stream()
                .filter(actualCart -> actualCart.equivalent(expectedCart))
                .findFirst()
                .orElse(null);
        if (cartFound != null) {
            addChildCalibrator(CartCalibrator.getInstance(expectedCart, cartFound));
        }
        return cartFound;
    }

    private void addCalibrationsUnexpected(List<CartCalibratable> actualCartList) {
        actualCartList.forEach(unexpectedCart -> addChildCalibrator(CartCalibrator.getInstance(null, unexpectedCart)));
    }

    @Override
    protected void executeVerifications() {
        verify("Is response successful", expected.isSuccessful(), actual.isSuccessful());
    }
}
