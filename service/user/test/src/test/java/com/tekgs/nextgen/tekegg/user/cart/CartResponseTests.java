package com.tekgs.nextgen.tekegg.user.cart;

import com.tekgs.nextgen.tekegg.data.cart.*;
import com.tekgs.nextgen.tekegg.data.cart.item.ItemCalibratable;
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
        CartResponse actual = CartRequest.getInstance().getAll();
        then(CartResponseCalibrator.getInstance(expected, actual));
    }
    
    @Test(groups = {TestSuite.RELEASE}, dependsOnMethods = "smoke")
    public void getById() {
        String cartId = "1";
        CartCalibratable cartDefinition = CartDefinition.getInstance().withID(cartId);
        Cart cart = CartProvider.getInstance().get(cartDefinition);
        given(cart);
        CartExpected cartExpected = CartExpected.getInstance(cart);
        CartResponseExpected expected = CartResponseExpected.getInstance(cartExpected);
        when();
        CartResponse actual = CartRequest.getInstance(cartId).getById();
        then(CartResponseCalibrator.getInstance(expected, actual));
    }
    
    @Test(groups = {TestSuite.RELEASE}, dependsOnMethods = "smoke")
    public void put() {
        CartCalibratable cartDefinition = CartDefinition.getInstance();
        Cart cart = CartProvider.getInstance().get(cartDefinition);
        given(cart);
        ItemCalibratable cartItem = cart.getItems().get(0);
        ProductCalibratable product = cartItem.getProduct();
        int newQuantity = cartItem.getQuantity() + 1;
        CartExpected cartExpected = CartExpected.getInstance(cart, product, newQuantity);
        CartResponseExpected expected = CartResponseExpected.getInstance(cartExpected);
        when();
        CartResponse actual = CartRequest.getInstance(cart.getId(), product.getId(), newQuantity).put();
        then(CartResponseCalibrator.getInstance(expected, actual));
    }
}
