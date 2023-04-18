package com.tekgs.nextgen.tekegg.view.financialDashboard;

import com.tekgs.nextgen.tekegg.data.financial.revenue.Revenue;
import com.tekgs.nextgen.tekegg.view.financialDashboard.revenue.RevenueListRegionExpected;

import java.util.ArrayList;
import java.util.List;

public class FinancialDashboardViewExpected implements FinancialDashboardViewCalibratable {

    List<Revenue> revenueList = new ArrayList<>();
    
    public FinancialDashboardViewExpected(List<Revenue> revenueList) {
      this.revenueList.addAll(revenueList);
    }
    
    public static FinancialDashboardViewExpected getInstance() {
        return new FinancialDashboardViewExpected(null);
    }
    
    public static FinancialDashboardViewExpected getInstance(List<Revenue> revenueList) {
        return new FinancialDashboardViewExpected(revenueList);
    }

    @Override
    public RevenueListRegionExpected inRevenueRegion() {
        return RevenueListRegionExpected.getInstance(this.revenueList);
    }

    @Override
    public String getRevenueListTitle() {
        return "Revenue";
    }
}
