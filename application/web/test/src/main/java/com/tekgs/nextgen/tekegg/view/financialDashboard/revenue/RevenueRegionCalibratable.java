package com.tekgs.nextgen.tekegg.view.financialDashboard.revenue;

public interface RevenueRegionCalibratable {

    String getMonth();

    String getNetRevenue();

    boolean equivalent(RevenueRegionCalibratable comparator);
}
