package com.tekgs.nextgen.tekegg.view.financialDashboard;

import com.tekgs.nextgen.tekegg.data.financial.revenue.Revenue;
import com.tekgs.nextgen.tekegg.data.financial.revenue.RevenueProvider;
import org.softwareonpurpose.gauntlet.GauntletTest;
import org.testng.annotations.Test;

import java.util.List;

@Test(groups = {GauntletTest.Application.TEKEGG, GauntletTest.View.FINANCIAL_DASHBOARD})
public class FinancialDashboardViewTests extends GauntletTest {
    @Test(groups = {TestSuite.SMOKE})
    public void smoke() {
        List<Revenue> revenueList = RevenueProvider.getInstance().get();
        given();
        FinancialDashboardViewExpected expected = FinancialDashboardViewExpected.getInstance(revenueList);
        when();
        FinancialDashboardView actual = FinancialDashboardView.directNav();
        then(FinancialDashboardViewCalibrator.getInstance(expected, actual));
    }

}

