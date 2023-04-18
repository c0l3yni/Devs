package com.tekgs.nextgen.tekegg.view.landing;

import com.tekgs.nextgen.tekegg.view.error.ErrorView;
import org.softwareonpurpose.gauntlet.GauntletTest;
import org.testng.annotations.Test;

@Test(groups = {GauntletTest.Application.TEKEGG, GauntletTest.View.LANDING})

public class LandingViewTests extends GauntletTest {
    @Test(groups = {TestSuite.SMOKE})
    public void smoke(){
        LandingViewExpected expected = LandingViewExpected.getInstance();
        given();
        when();
        LandingView actual = LandingView.directNav();
        then(LandingViewCalibrator.getInstance(expected, actual));
    }

    @Test(groups = {TestSuite.ACCEPTANCE, TestSuite.RELEASE}, dependsOnMethods = "smoke")
    public void fromError(){
        addRequirements("257-error-take-user-back-to-homepage");
        LandingViewExpected expected = LandingViewExpected.getInstance();
        LandingView actual = ErrorView.directNav().returnHome();
        then(LandingViewCalibrator.getInstance(expected, actual));
    }
}
