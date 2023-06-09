package com.tekgs.nextgen.tekegg.view.error;

import org.softwareonpurpose.gauntlet.GauntletTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


@Test(groups = {GauntletTest.Application.TEKEGG, GauntletTest.View.ERROR})
public class ErrorViewTests extends GauntletTest {

    @DataProvider
    public static Object[][] invalidUrlScenarios() {
        String invalidPayment = "payment?total_amount=50/bogus";
        String invalidUrl = "bogus";
        String invalidPurchase = "purchase/bogus/bogus";

        return new Object[][] {
            {invalidPayment},
            {invalidUrl},
            {invalidPurchase}
        };
    }

    @Test(groups={TestSuite.SMOKE})
    public void smoke(){
        ErrorViewExpected expected = ErrorViewExpected.getInstance();
        given();
        when();
        ErrorView actual = ErrorView.directNav();
        then(ErrorViewCalibrator.getInstance(expected, actual));
    }

    @Test(groups = {TestSuite.RELEASE}, dependsOnMethods = "smoke", dataProvider = "invalidUrlScenarios")
    public void directNavInvalidUrl(String invalidUrl){
        given(invalidUrl);
        ErrorViewExpected expected = ErrorViewExpected.getInstance();
        when();
        ErrorView actual = ErrorView.directNav(invalidUrl);
        then(ErrorViewCalibrator.getInstance(expected, actual));
    }

    @Test(groups= {TestSuite.RELEASE}, dependsOnMethods = "smoke")
    public void directNavUnhandledException(){
        String unhandledErrorMessage = "Oops, something went wrong on our end";
        String unhandledExceptionTestPath = "unhandled-exception-simulation";
        given(unhandledExceptionTestPath);
        ErrorViewExpected expected = ErrorViewExpected.getInstance(unhandledErrorMessage);
        when();
        ErrorView actual = ErrorView.directNav(unhandledExceptionTestPath);
        then(ErrorViewCalibrator.getInstance(expected, actual));
    }
}
