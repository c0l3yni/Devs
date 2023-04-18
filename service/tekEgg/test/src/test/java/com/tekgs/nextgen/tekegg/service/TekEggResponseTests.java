package com.tekgs.nextgen.tekegg.service;

import com.tekgs.nextgen.tekegg.data.cart.*;
import org.softwareonpurpose.gauntlet.GauntletTest;
import org.testng.annotations.Test;

@Test(groups = {GauntletTest.Service.TEKEGG, GauntletTest.Endpoint.TEKEGG})
public class TekEggResponseTests extends GauntletTest {

    @Test(groups = {TestSuite.SMOKE, TestSuite.ACCEPTANCE})
    public void Smoke(){
        TekEggResponseExpected expected = TekEggResponseExpected.getInstance();
        TekEggResponse actual = TekEggRequest.getInstance().head();
        then(TekEggResponseCalibrator.getInstance(expected, actual));
    }

    @Test(groups = {TestSuite.ACCEPTANCE} , dependsOnMethods = "Smoke")
    public void getCart(){
        CartCalibratable cartDefinition = CartDefinition.getInstance();
        Cart cart = CartProvider.getInstance().get(cartDefinition);
        given(cart);
        CartExpected cartExpected = CartExpected.getInstance(cart);
        TekEggResponseExpected expected = TekEggResponseExpected.getInstance(cartExpected);
        when();
        TekEggResponse actual = TekEggRequest.getInstance(cart).get();
        then(TekEggResponseCalibrator.getInstance(expected, actual));
    }
}
