package com.tekgs.nextgen.tekegg.view.financialDashboard.revenue;

import com.tekgs.nextgen.tekegg.data.financial.revenue.Revenue;
import com.tekgs.nextgen.tekegg.data.value.Cents;

public class RevenueRegionExpected implements RevenueRegionCalibratable {

    private final Revenue revenue;
    private final Cents cents;

    protected RevenueRegionExpected(Revenue revenue) {
        this.revenue = revenue;
        cents = Cents.getInstance(revenue.getNetRevenue());
    }

    public static RevenueRegionExpected getInstance(Revenue revenue) {
        return new RevenueRegionExpected(revenue);
    }

    @Override
    public String getMonth() {
        return this.revenue.getRevenueMonth();
    }

    @Override
    public String getNetRevenue() {
        return this.cents.inDollarFormat();
    }

    @Override
    public boolean equivalent(RevenueRegionCalibratable comparator) {
        return false;
    }
}
