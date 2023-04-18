package com.tekgs.nextgen.tekegg.view.financialDashboard.revenue;

import com.tekgs.nextgen.tekegg.data.financial.revenue.Revenue;

import java.util.ArrayList;
import java.util.List;

public class RevenueListRegionExpected implements RevenueListRegionCalibratable {

    private final List<Revenue> revenueList = new ArrayList<>();

    public RevenueListRegionExpected(List<Revenue> revenues) {
        this.revenueList.addAll(revenues);
    }

    public static RevenueListRegionExpected getInstance(List<Revenue> revenueList) {
        return new RevenueListRegionExpected(revenueList);
    }

    @Override
    public List<RevenueRegionCalibratable> getRevenueList() {
        List<RevenueRegionCalibratable> revenueRegions = new ArrayList<>();
        this.revenueList.forEach(revenue -> revenueRegions.add(RevenueRegionExpected.getInstance(revenue)));
        return revenueRegions;
    }

}
