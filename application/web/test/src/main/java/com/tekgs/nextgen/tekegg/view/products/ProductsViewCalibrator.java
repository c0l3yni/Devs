package com.tekgs.nextgen.tekegg.view.products;

import com.softwareonpurpose.calibrator4test.Calibrator;
import com.softwareonpurpose.uinavigator.UiRegion;
import com.tekgs.nextgen.tekegg.region.product.ProductRegionCalibratable;
import com.tekgs.nextgen.tekegg.region.product.ProductRegionCalibrator;

import java.util.List;

public class ProductsViewCalibrator extends Calibrator {
    private static final String DESCRIPTION = "'Product' view";
    private final ProductsViewExpected expected;
    private final ProductsView actual;

    private ProductsViewCalibrator(ProductsViewExpected expected, ProductsView actual) {
        super(DESCRIPTION, expected, actual);
        this.expected = expected;
        this.actual = actual;
        UiRegion.suppressConstructionLogging(true);
        List<ProductRegionCalibratable> expectedProducts = expected.inProducts().getProducts();
        List<ProductRegionCalibratable> actualProducts = actual.inProducts().getProducts();
        addCalibrationsExpected(expectedProducts, actualProducts);
        addCalibrationUnexpected(actualProducts);
    }

    public static ProductsViewCalibrator getInstance(ProductsViewExpected expected, ProductsView actual) {
        return new ProductsViewCalibrator(expected, actual);
    }

    private void addCalibrationsExpected(List<ProductRegionCalibratable> expectedProducts, List<ProductRegionCalibratable> actualProducts) {
        expectedProducts.forEach(expectedProduct -> {
            ProductRegionCalibratable productFound = addCalibrationFound(actualProducts, expectedProduct);
            if (productFound == null) {
                addChildCalibrator(ProductRegionCalibrator.getInstance(expectedProduct, null));
            } else {
                actualProducts.remove(productFound);
            }
        });

    }

    private ProductRegionCalibratable addCalibrationFound(List<ProductRegionCalibratable> actualProducts, ProductRegionCalibratable expectedProduct) {
        ProductRegionCalibratable productFound = actualProducts.stream()
                .filter(actualProduct->actualProduct.equivalent(expectedProduct))
                .findFirst()
                .orElse(null);
        if(productFound != null){
            addChildCalibrator(ProductRegionCalibrator.getInstance(expectedProduct, productFound));
        }
        return productFound;
    }

    private void addCalibrationUnexpected(List<ProductRegionCalibratable> actualProducts) {
        actualProducts.forEach(productUnexpected -> addChildCalibrator(ProductRegionCalibrator.getInstance(null, productUnexpected)));
    }

    @Override
    protected void executeVerifications() {
        verify("'Empty' message", this.expected.getEmptyMessage(), this.actual.getEmptyMessage());
    }
}
