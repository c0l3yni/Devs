package com.tekgs.nextgen.tekegg.user.cart;

import com.tekgs.nextgen.tekegg.data.cart.*;
import com.tekgs.nextgen.tekegg.data.cart.item.ItemCalibratable;
import com.tekgs.nextgen.tekegg.data.cart.item.ItemDefinition;
import com.tekgs.nextgen.tekegg.data.cart.product.ProductCalibratable;
import org.softwareonpurpose.gauntlet.GauntletTest;
import org.testng.annotations.Test;


@Test(groups = {GauntletTest.Service.USER, GauntletTest.Endpoint.CARTS})
public class CartResponseTests extends GauntletTest {
    
    @Test(groups = {TestSuite.SMOKE})
    public void smoke() {
        CartResponseExpected expected = CartResponseExpected.getInstance();
        given();
        when();
        CartResponse actual = CartRequest.getInstance().head();
        then(CartResponseCalibrator.getInstance(expected, actual));
    }
    
    @Test(groups = {TestSuite.RELEASE}, dependsOnMethods = "smoke")
    public void getById() {
        CartCalibratable cartDefinition = CartDefinition.getInstance();
        Cart cart = CartProvider.getInstance().get(cartDefinition);
        given(cart);
        CartExpected cartExpected = CartExpected.getInstance(cart);
        CartResponseExpected expected = CartResponseExpected.getInstance(cartExpected);
        when();
        CartResponse actual = CartRequest.getInstance(cart).get();
        then(CartResponseCalibrator.getInstance(expected, actual));
    }
    
    @Test(groups = {TestSuite.RELEASE}, dependsOnMethods = "smoke")
    public void put() {
        CartCalibratable cartDefinition = CartDefinition.getInstance();
        Cart cart = CartProvider.getInstance().get(cartDefinition);
        ItemCalibratable cartItem = cart.getItems().get(0);
        ProductCalibratable product = cartItem.getProduct();
        int newQuantity = cartItem.getQuantity() + 1;
        ItemDefinition itemDefinition = ItemDefinition.getInstance(cartItem).withQuantity(newQuantity);
        given(cart);
        CartExpected cartExpected = CartExpected.getInstance(cart, product, newQuantity);
        CartResponseExpected expected = CartResponseExpected.getInstance(cartExpected);
        when();
        CartDefinition updatedCart = CartDefinition.getInstance(cart).withUpdatedItem(itemDefinition);
        CartResponse actual = CartRequest.getInstance(updatedCart).put();
        then(CartResponseCalibrator.getInstance(expected, actual));
    }
}
