package com.tekgs.nextgen.tekegg.view.products;


import com.tekgs.nextgen.tekegg.data.cart.Cart;
import com.tekgs.nextgen.tekegg.data.cart.CartCalibratable;
import com.tekgs.nextgen.tekegg.data.cart.CartDefinition;
import com.tekgs.nextgen.tekegg.data.cart.CartProvider;
import com.tekgs.nextgen.tekegg.data.cart.item.ItemDefinition;
import com.tekgs.nextgen.tekegg.data.product.Product;
import com.tekgs.nextgen.tekegg.data.product.ProductProvider;
import org.softwareonpurpose.gauntlet.GauntletTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Test(groups = {GauntletTest.Application.TEKEGG, GauntletTest.View.PRODUCT})
public class ProductsViewTests extends GauntletTest {
    @DataProvider
    public static Object[][] scenarios(){
        List<Product> products = ProductProvider.getInstance().get();
        List<ItemDefinition> allItems = new ArrayList<>();
        products.forEach(product -> allItems.add(ItemDefinition.getInstance().withProduct(product)));
        return new Object[][]{
                {CartDefinition.getInstance().withNoItems()},
                {CartDefinition.getInstance().withItems(allItems)}

        };
    }
    @Test(groups = {TestSuite.SMOKE})
    public void smoke() {
        ProductsViewExpected expected = ProductsViewExpected.getInstance();
        given(expected);
        when();
        ProductsView actual = ProductsView.directNav();
        then(ProductsViewCalibrator.getInstance(expected, actual));
    }

    @Test(groups = {TestSuite.ACCEPTANCE}, dependsOnMethods = "smoke")
    public void directNav(){
        addRequirements("127-products-add-to-cart-button-disabled-for-items-in-cart ");
        CartCalibratable cartDefinition= CartDefinition.getInstance();
        Cart cart = CartProvider.getInstance().get(cartDefinition);
        ProductsViewExpected expected = ProductsViewExpected.getInstance(cart);
        ProductsView actual = ProductsView.directNav(cart);
        then(ProductsViewCalibrator.getInstance(expected, actual));
    }
    @Test( dependsOnMethods = "smoke", dataProvider = "scenarios")
    public void release_directNav(CartDefinition cartDefinition){
        addRequirements("127-products-add-to-cart-button-disabled-for-items-in-cart ");
        Cart cart = CartProvider.getInstance().get(cartDefinition);
        ProductsViewExpected expected = ProductsViewExpected.getInstance(cart);
        ProductsView actual = ProductsView.directNav(cart);
        then(ProductsViewCalibrator.getInstance(expected, actual));
    }

}
