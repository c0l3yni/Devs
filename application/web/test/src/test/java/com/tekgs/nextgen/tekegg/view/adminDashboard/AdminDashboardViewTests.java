package com.tekgs.nextgen.tekegg.view.adminDashboard;

import com.tekgs.nextgen.tekegg.data.admin.LoginFailureProvider;
import org.softwareonpurpose.gauntlet.GauntletTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

@Test(groups = {GauntletTest.Application.TEKEGG, GauntletTest.View.ADMIN_DASHBOARD})
public class AdminDashboardViewTests extends GauntletTest {

    @DataProvider
    public static Object[][] scenarios() {
        return new Object[][]{
                {"withNoFailures"}, {"withLessThan28Days"}, {"withMoreThan28DaysWithGaps"}
        };
    }

    @Test(groups = {TestSuite.SMOKE, TestSuite.ACCEPTANCE})
    public void smoke() {
        addRequirements("153-Site-Admin-Dashboard-login-failure-report");
        List<String> loginFailureList = LoginFailureProvider.getInstance().get();
        given(loginFailureList);
        AdminDashboardViewExpected expected = AdminDashboardViewExpected.getInstance(loginFailureList);
        when();
        AdminDashboardView actual = AdminDashboardView.directNav();
        then(AdminDashboardViewCalibrator.getInstance(expected, actual));
    }

    @Test(dependsOnMethods = "smoke", dataProvider = "scenarios")
    public void directNav(String failureLog) {
        addRequirements("153-Site-Admin-Dashboard-login-failure-report");
        List<String> loginFailureList = LoginFailureProvider.getInstance().get(failureLog);
        given(loginFailureList);
        AdminDashboardViewExpected expected = AdminDashboardViewExpected.getInstance(loginFailureList);
        when();
        AdminDashboardView actual = AdminDashboardView.directNav(failureLog);
        then(AdminDashboardViewCalibrator.getInstance(expected, actual));
    }
}
