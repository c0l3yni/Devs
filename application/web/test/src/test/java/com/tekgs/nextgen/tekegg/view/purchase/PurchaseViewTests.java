package com.tekgs.nextgen.tekegg.view.purchase;

import com.tekgs.nextgen.tekegg.data.cart.Cart;
import com.tekgs.nextgen.tekegg.data.cart.CartCalibratable;
import com.tekgs.nextgen.tekegg.data.cart.CartDefinition;
import com.tekgs.nextgen.tekegg.data.cart.CartProvider;
import com.tekgs.nextgen.tekegg.data.financial.payment.TekEggPayment;
import com.tekgs.nextgen.tekegg.data.financial.payment.TekEggPaymentDefinition;
import com.tekgs.nextgen.tekegg.view.payment.PaymentView;
import org.softwareonpurpose.gauntlet.GauntletTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = {GauntletTest.Application.TEKEGG, GauntletTest.View.PURCHASE})
public class PurchaseViewTests extends GauntletTest {
    @DataProvider
    public static Object[][] scenarios() {
        int fortyNineCents = 49;
        int fiftyCentsPaymentServiceLowerLimit = 50;
        int oneMillionDollars = 100000000;
        int negativeValue = -100;
        int oneLessThanOneMillion = 99999999;
        String validCurrency = "usd";
        String validSource = "tok_visa";
        return new Object[][]{
                {TekEggPaymentDefinition.getInstance().withAmount(fortyNineCents).withCurrency(validCurrency).withSource(validSource)},
                {TekEggPaymentDefinition.getInstance().withAmount(fiftyCentsPaymentServiceLowerLimit).withCurrency(validCurrency).withSource(validSource)},
                {TekEggPaymentDefinition.getInstance().withAmount(oneLessThanOneMillion).withCurrency(validCurrency).withSource(validSource)},
                {TekEggPaymentDefinition.getInstance().withAmount(oneMillionDollars).withCurrency(validCurrency).withSource(validSource)},
                {TekEggPaymentDefinition.getInstance().withAmount(negativeValue).withCurrency(validCurrency).withSource(validSource)}
        };
    }

    @Test(groups = {TestSuite.SMOKE})
    public void smoke() {
        TekEggPaymentDefinition tekEggPaymentDefinition = TekEggPaymentDefinition.getInstance().withAmount(50).withCurrency("usd").withSource("tok_amex");
        TekEggPayment payment = TekEggPayment.getInstance(tekEggPaymentDefinition);
        Integer amount = payment.getAmount() - payment.getShippingCost();
        Cart cart = CartProvider.getInstance().get(CartDefinition.getInstance().withTotal(amount));
        given(payment);
        PurchaseViewExpected expected = PurchaseViewExpected.getInstance(payment);
        when();
        PurchaseView actual = PaymentView.directNav(cart).submit(payment);
        then(PurchaseViewCalibrator.getInstance(expected, actual));
    }

    @Test(dependsOnMethods = "smoke", dataProvider = "scenarios")
    public void fromPayment(TekEggPaymentDefinition tekEggPaymentDefinition) {
        TekEggPayment payment = TekEggPayment.getInstance(tekEggPaymentDefinition);
        Integer amount = payment.getAmount() - payment.getShippingCost();
        Cart cart = CartProvider.getInstance().get(CartDefinition.getInstance().withTotal(amount));
        given(cart);
        PurchaseViewExpected expected = PurchaseViewExpected.getInstance(payment);
        when();
        PurchaseView actual = PaymentView.directNav(cart).submit(payment);
        then(PurchaseViewCalibrator.getInstance(expected, actual));
    }
    @Test(groups = {TestSuite.RELEASE}, dependsOnMethods = "smoke")
    public void release_fromPayment() {
        CartDefinition cartDefinition = CartDefinition.getInstance().withPurchasableAmount(true);
        Cart cart = CartProvider.getInstance().get(cartDefinition);
        TekEggPaymentDefinition tekEggPaymentDefinition =
                TekEggPaymentDefinition.getInstance()
                        .withAmount(cart.getTotal())
                        .withCurrency(TekEggPayment.Currency.USD)
                        .withSource(TekEggPayment.Source.AMEX);
        TekEggPayment payment = TekEggPayment.getInstance(tekEggPaymentDefinition);
        given(cart,payment);
        PurchaseViewExpected expected = PurchaseViewExpected.getInstance(payment);
        when();
        PurchaseView actual = PaymentView.directNav(cart).submit(payment);
        then(PurchaseViewCalibrator.getInstance(expected, actual));
    }
}
