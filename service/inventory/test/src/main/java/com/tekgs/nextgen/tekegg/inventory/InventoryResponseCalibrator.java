package com.tekgs.nextgen.tekegg.inventory;

import com.softwareonpurpose.calibrator4test.Calibrator;
import com.tekgs.nextgen.tekegg.data.product.ProductCalibratable;
import com.tekgs.nextgen.tekegg.data.product.ProductCalibrator;

import java.util.ArrayList;
import java.util.List;

public class InventoryResponseCalibrator extends Calibrator {

    public static final String DESCRIPTION = "'Inventory' response";
    private final InventoryResponseExpected expected;
    private final InventoryResponse actual;

    private InventoryResponseCalibrator(InventoryResponseExpected expected, InventoryResponse actual) {
        super(DESCRIPTION, expected, actual);
        this.expected = expected;
        this.actual = actual;
        List<ProductCalibratable> expectedProductList = expected.getProducts();
        List<ProductCalibratable> actualProductList = new ArrayList<>(actual.getProducts());
        addCalibrationsExpected(expectedProductList, actualProductList);
        addCalibrationsUnexpected(actualProductList);
    }

    public static InventoryResponseCalibrator getInstance(InventoryResponseExpected expected, InventoryResponse actual) {
        return new InventoryResponseCalibrator(expected, actual);
    }

    private void addCalibrationsExpected(List<ProductCalibratable> expectedProductList, List<ProductCalibratable> actualProductList) {
        expectedProductList.forEach(expectedProduct -> {
            ProductCalibratable productFound = addCalibrationsFound(actualProductList, expectedProduct);
            if (productFound == null){
                addChildCalibrator(ProductCalibrator.getInstance(expectedProduct, null));
            } else {
                actualProductList.remove(productFound);
            }
        });
    }

    private ProductCalibratable addCalibrationsFound(List<ProductCalibratable> actualProductList, ProductCalibratable expectedProduct) {
        ProductCalibratable productFound = actualProductList.stream()
                .filter(actualProduct -> actualProduct.equivalent(expectedProduct))
                .findFirst()
                .orElse(null);
        if (productFound != null) {
            addChildCalibrator(ProductCalibrator.getInstance(expectedProduct, productFound));
        }
        return productFound;
    }

    private void addCalibrationsUnexpected(List<ProductCalibratable> actualProductList) {
        actualProductList.forEach(unexpectedProduct -> addChildCalibrator(ProductCalibrator.getInstance(null, unexpectedProduct)));
    }

    @Override
    protected void executeVerifications() {
        verify("Is response successful", expected.isSuccessful(), actual.isSuccessful());
    }
}
