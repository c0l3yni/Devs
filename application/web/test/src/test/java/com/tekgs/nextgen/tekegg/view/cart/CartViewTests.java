package com.tekgs.nextgen.tekegg.view.cart;

import com.tekgs.nextgen.tekegg.data.cart.Cart;
import com.tekgs.nextgen.tekegg.data.cart.CartDefinition;
import com.tekgs.nextgen.tekegg.data.cart.CartProvider;
import com.tekgs.nextgen.tekegg.data.product.ProductDefinition;
import org.softwareonpurpose.gauntlet.GauntletTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = {GauntletTest.Application.TEKEGG, GauntletTest.View.CART})
public class CartViewTests extends GauntletTest {
    
    @DataProvider
    public static Object[][] scenarios() {
        ProductDefinition normalProduct = ProductDefinition.getInstance().withDescription("product 1 description");
        ProductDefinition productNoDescription = ProductDefinition.getInstance().withDescription(null);
        ProductDefinition productWithTag = ProductDefinition.getInstance().withDescription("<h2>hack attempt<h2>");
        ProductDefinition productWithBackspace = ProductDefinition.getInstance().withDescription("(U+2408)");
        String wordOfLength256 = "kjhbafsbkjknbfjasbkjsfabkjasfbkjfasbkjhbkjhsafbkjhsfabkjhbkjsfakbjfsabkjsfabkjbkjfsabkjfsajbjk.fbkjsabjk.fkjasbfkjbasjbf.jkasjkfbjkas.jkbf.bjkasb.jkf.jkabsfbkj.asbfjkbasjkbfjksabjkbfjkasbfj.kbasj.kbfjkabsfjkbaskjkjf.ajksbafjkbbsajsbfkabasfkj.bsajkbafs.kbkj";
        ProductDefinition productDescriptionOneWord256 = ProductDefinition.getInstance().withDescription(wordOfLength256);
        ProductDefinition productWithSQL = ProductDefinition.getInstance().withDescription("SELECT * FROM Users WHERE UserId = 2");
        CartDefinition cartWithTotalLessThan50 = CartDefinition.getInstance().withTotal(49);
        CartDefinition cartWithTotalMoreThan90000000 = CartDefinition.getInstance().withTotal(900_000_01);
        return new Object[][]{
                {CartDefinition.getInstance().withProduct(normalProduct)}
                , {CartDefinition.getInstance().withProduct(productNoDescription)}
                , {CartDefinition.getInstance().withProduct(productWithTag)}
                , {CartDefinition.getInstance().withProduct(productWithBackspace)}
                , {CartDefinition.getInstance().withProduct(productDescriptionOneWord256)}
                , {CartDefinition.getInstance().withProduct(productWithSQL)}
                , {cartWithTotalLessThan50}
                , {cartWithTotalMoreThan90000000}
        };
    }
    
    @DataProvider
    public static Object[][] releaseScenarios() {
        ProductDefinition productWithThumbnailNull = ProductDefinition.getInstance().withThumbnail(null);
        CartDefinition cartWithTotalLessThan50 = CartDefinition.getInstance().withTotal(49);
        CartDefinition cartWithTotalMoreThan90000000 = CartDefinition.getInstance().withTotal(900_000_01);
        CartDefinition cartWithNoThumbnail = CartDefinition.getInstance().withProduct(productWithThumbnailNull);
        return new Object[][]{
                {cartWithTotalLessThan50},
                {cartWithTotalMoreThan90000000},
                {cartWithNoThumbnail}
        };
    }
    
    @Test(groups = {TestSuite.SMOKE})
    public void smoke() {
        CartViewExpected expected = CartViewExpected.getInstance();
        given();
        when();
        CartView actual = CartView.directNav();
        then(CartViewCalibrator.getInstance(expected, actual));
    }
    
    @Test(groups = {TestSuite.RELEASE, TestSuite.ACCEPTANCE}, dependsOnMethods = "smoke", dataProvider = "releaseScenarios")
    public void directNav(CartDefinition cartDefinition) {
        addRequirements("203-cart-total-amount-less-than-50", "204-cart-total-amount-greater-than-$900,000.00", "178-cart-item-thumbnail");
        Cart cart = CartProvider.getInstance().get(cartDefinition);
        given(cart);
        CartViewExpected expected = CartViewExpected.getInstance(cart);
        when();
        CartView actual = CartView.directNav(cart);
        then(CartViewCalibrator.getInstance(expected, actual));
    }
    
    @Test(dependsOnMethods = "smoke", dataProvider = "scenarios")
    public void regression_directNav(CartDefinition cartDefinition) {
        Cart cart = CartProvider.getInstance().get(cartDefinition);
        given(cart);
        CartViewExpected expected = CartViewExpected.getInstance(cart);
        when();
        CartView actual = CartView.directNav(cart);
        then(CartViewCalibrator.getInstance(expected, actual));
    }
    
    @Test(groups = {TestSuite.RELEASE}, dependsOnMethods = "smoke")
    public void release_directNav() {
        ProductDefinition product = ProductDefinition.getInstance().withDescription("product 1 description");
        CartDefinition cartDefinition = CartDefinition.getInstance().withProduct(product);
        Cart cart = CartProvider.getInstance().get(cartDefinition);
        given(cart);
        CartViewExpected expected = CartViewExpected.getInstance(cart);
        when();
        CartView actual = CartView.directNav(cart);
        then(CartViewCalibrator.getInstance(expected, actual));
    }
}
