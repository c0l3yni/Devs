package com.tekgs.nextgen.tekegg.data;

import com.tekgs.nextgen.tekegg.data.cart.*;
import com.tekgs.nextgen.tekegg.data.product.ProductCalibratable;
import com.tekgs.nextgen.tekegg.view.cart.CartView;
import org.softwareonpurpose.gauntlet.GauntletTest;
import org.testng.annotations.Test;

@Test(groups = {GauntletTest.Application.TEKEGG, GauntletTest.View.CART})
public class CartTests extends GauntletTest {
    @Test(groups = {TestSuite.SMOKE})
    public void smoke() {
        CartDefinition cartDefinition = CartDefinition.getInstance();
        Cart cart = CartProvider.getInstance().get(cartDefinition);
        given(cart);
        CartExpected expected = CartExpected.getInstance(cart);
        when();
        Cart actual = CartProvider.getInstance().get(cart);
        then(CartCalibrator.getInstance(expected, actual));
    }
    
    @Test(groups = {TestSuite.RELEASE}, dependsOnMethods = "smoke")
    public void fromCartView_updateQuantity() {
        CartDefinition cartDefinition = CartDefinition.getInstance();
        Cart cart = CartProvider.getInstance().get(cartDefinition);
        int newQuantity = cart.getItems().get(0).getQuantity() + 1;
        given(cart);
        ProductCalibratable product = cart.getItems().get(0).getProduct();
        CartExpected expected = CartExpected.getInstance(cart, product, newQuantity);
        when();
        CartView.directNav(cart).updateQuantity(product, newQuantity);
        Cart actual = CartProvider.getInstance().get(cart);
        then(CartCalibrator.getInstance(expected, actual));
    }
}
