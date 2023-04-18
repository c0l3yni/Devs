package com.tekgs.nextgen.tekegg.view.payment;

import com.tekgs.nextgen.tekegg.data.cart.*;
import com.tekgs.nextgen.tekegg.data.cart.item.ItemDefinition;
import com.tekgs.nextgen.tekegg.data.financial.payment.TekEggPayment;
import com.tekgs.nextgen.tekegg.data.financial.payment.TekEggPaymentDefinition;
import com.tekgs.nextgen.tekegg.data.product.ProductCalibratable;
import com.tekgs.nextgen.tekegg.data.product.ProductDefinition;
import com.tekgs.nextgen.tekegg.view.cart.CartView;
import org.softwareonpurpose.gauntlet.GauntletTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = {GauntletTest.Application.TEKEGG, GauntletTest.View.PAYMENT})
public class PaymentViewTests extends GauntletTest {
    @DataProvider
    public static Object[][] directNavScenarios() {
        int fortyNineCents = 49;
        int fiftyCentsPaymentServiceLowerLimit = 50;
        int oneMillionDollars = 100000000;
        int negativeValue = -100;
        int oneLessThanOneMillion = 99999999;
        return new Object[][]{
                {CartDefinition.getInstance().withTotal(fortyNineCents)}
                , {CartDefinition.getInstance().withTotal(fiftyCentsPaymentServiceLowerLimit)}
                , {CartDefinition.getInstance().withTotal(oneLessThanOneMillion)}
                , {CartDefinition.getInstance().withTotal(oneMillionDollars)}
                , {CartDefinition.getInstance().withTotal(negativeValue)}
        };
    }

    @DataProvider
    public static Object[][] submitInvalidScenarios() {
        String invalid = "bogus";
        String validSource = "tok_visa";
        String validCurrency = "usd";
        return new Object[][]{
                {TekEggPaymentDefinition.getInstance().withCurrency(invalid).withSource(invalid)},
                {TekEggPaymentDefinition.getInstance().withCurrency(invalid).withSource(validSource)},
                {TekEggPaymentDefinition.getInstance().withCurrency(validCurrency).withSource(invalid)}
        };
    }

    @DataProvider
    public static Object[][] cartScenarios() {
        return new Object[][]{
                {1}, {-1}, {0}, {5}, {-5}
        };
    }

    @Test(groups = {TestSuite.SMOKE})
    public void smoke() {
        PaymentViewExpected expected = PaymentViewExpected.getInstance();
        given();
        when();
        PaymentView actual = PaymentView.directNav();
        then(PaymentViewCalibrator.getInstance(expected, actual));
    }

    @Test(dependsOnMethods = "smoke", dataProvider = "directNavScenarios")
    public void directNav(CartDefinition cartDefinition) {
        int shippingCost = TekEggPayment.getInstance().getShippingCost();
        cartDefinition.withTotal(cartDefinition.getTotal() - shippingCost);
        Cart cart = CartProvider.getInstance().get(cartDefinition);
        given(cart);
        PaymentViewExpected expected = PaymentViewExpected.getInstance(cart);
        when();
        PaymentView actual = PaymentView.directNav(cart);
        then(PaymentViewCalibrator.getInstance(expected, actual));
    }

    @Test(groups = {TestSuite.RELEASE}, dependsOnMethods = "smoke")
    public void release_directNav() {
        int shippingCost = TekEggPayment.getInstance().getShippingCost();
        Integer amount = 50 - shippingCost;
        CartCalibratable cartDefinition = CartDefinition.getInstance().withTotal(amount);
        Cart cart = CartProvider.getInstance().get(cartDefinition);
        given(cart);
        PaymentViewExpected expected = PaymentViewExpected.getInstance(cart);
        when();
        PaymentView actual = PaymentView.directNav(cart);
        then(PaymentViewCalibrator.getInstance(expected, actual));
    }

    @Test(groups = {}, dependsOnMethods = "smoke", dataProvider = "submitInvalidScenarios")
    public void submitValidationError(TekEggPaymentDefinition paymentDefinition) {
        TekEggPayment payment = TekEggPayment.getInstance(paymentDefinition);
        given(payment);
        PaymentViewExpected expected = PaymentViewExpected.getInstance(payment);
        when();
        PaymentView actual = PaymentView.directNav().submitInvalid(payment);
        then(PaymentViewCalibrator.getInstance(expected, actual));
    }

    @Test(dependsOnMethods = "smoke", dataProvider = "cartScenarios")
    public void fromCart(int quantityIncrement) {
        CartDefinition cartDefinition = CartDefinition.getInstance().withItem(ItemDefinition.getInstance());
        Cart cart = CartProvider.getInstance().get(cartDefinition);
        int currentQuantity = cart.getAnyItem().getQuantity();
        int newQuantity = currentQuantity + quantityIncrement;
        given(cart, newQuantity);
        ProductCalibratable product = cart.getItems().get(0).getProduct();
        CartExpected cartExpected = CartExpected.getInstance(cart, product, newQuantity);
        PaymentViewExpected expected = PaymentViewExpected.getInstance(cartExpected);
        when();
        PaymentView actual = CartView.directNav(cart).updateQuantity(product, newQuantity).checkout();
        then(PaymentViewCalibrator.getInstance(expected, actual));
    }

    @Test(groups = {}, dependsOnMethods = "smoke")
    public void release_fromCart() {
        CartDefinition cartDefinition = CartDefinition.getInstance().withItem(ItemDefinition.getInstance());
        Cart cart = CartProvider.getInstance().get(cartDefinition);
        ProductCalibratable product = cart.getItems().get(0).getProduct();
        int newQuantity = cart.getItems().get(0).getQuantity() + 1;
        given(cart, product, newQuantity);
        CartExpected cartExpected = CartExpected.getInstance(cart, product, newQuantity);
        PaymentViewExpected expected = PaymentViewExpected.getInstance(cartExpected);
        when();
        PaymentView actual = CartView.directNav(cart).updateQuantity(product, newQuantity).checkout();
        then(PaymentViewCalibrator.getInstance(expected, actual));
    }
}
