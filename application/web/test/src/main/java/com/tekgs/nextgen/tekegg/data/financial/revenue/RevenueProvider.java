package com.tekgs.nextgen.tekegg.data.financial.revenue;


import java.util.List;

public class RevenueProvider {

    public static RevenueProvider getInstance() {
        return new RevenueProvider();
    }

    public List<Revenue> get() {
        return RevenueRepository.getInstance().query();
    }
}
