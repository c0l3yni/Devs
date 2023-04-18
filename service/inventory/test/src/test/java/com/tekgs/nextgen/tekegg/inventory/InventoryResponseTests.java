package com.tekgs.nextgen.tekegg.inventory;

import com.tekgs.nextgen.tekegg.data.InventoryProvider;
import com.tekgs.nextgen.tekegg.data.product.Product;
import com.tekgs.nextgen.tekegg.data.product.ProductDefinition;
import com.tekgs.nextgen.tekegg.data.product.ProductExpected;
import org.softwareonpurpose.gauntlet.GauntletTest;
import org.testng.annotations.Test;

@Test(groups = {GauntletTest.Service.INVENTORY, GauntletTest.Endpoint.INVENTORY})
public class InventoryResponseTests extends GauntletTest {
    
    @Test(groups = {TestSuite.SMOKE})
    public void smoke() {
        InventoryResponseExpected expected = InventoryResponseExpected.getInstance();
        given();
        when();
        InventoryResponse actual = InventoryRequest.getInstance().getAll();
        then(InventoryResponseCalibrator.getInstance(expected, actual));
    }

    @Test(groups = {TestSuite.DEBUG}, dependsOnMethods = "smoke")
    public void getById(){
        String productId = "1";
        ProductDefinition productDefinition = ProductDefinition.getInstance().withId(productId);
        Product product = InventoryProvider.getInstance().get(productDefinition);
        given(product);
        ProductExpected productExpected = ProductExpected.getInstance(product);
        InventoryResponseExpected expected = InventoryResponseExpected.getInstance(productExpected);
        when();
        InventoryResponse actual = InventoryRequest.getInstance(productId).getById();
        then(InventoryResponseCalibrator.getInstance(expected,actual));
    }
}
