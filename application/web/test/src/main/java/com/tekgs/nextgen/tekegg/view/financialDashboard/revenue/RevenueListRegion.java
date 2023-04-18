package com.tekgs.nextgen.tekegg.view.financialDashboard.revenue;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiRegion;

import java.util.ArrayList;
import java.util.List;

public class RevenueListRegion extends UiRegion implements RevenueListRegionCalibratable {

    private static final String DESCRIPTION = "'Revenue List' region";
    private static final String LOCATOR_TYPE = UiLocatorType.ID;
    private static final String LOCATOR_VALUE = "revenue-list";


    protected RevenueListRegion(UiElement parentElement) {
        super(UiElement.getInstance(DESCRIPTION, LOCATOR_TYPE, LOCATOR_VALUE, parentElement));

    }

    public static RevenueListRegion getInstance(UiElement parentElement) {
        return new RevenueListRegion(parentElement);
    }

    @Override
    public List<RevenueRegionCalibratable> getRevenueList() {
        List<RevenueRegionCalibratable> revenues = new ArrayList<>();
        List<UiElement> revenueElementList = UiElement.getList("'Revenue' region", UiLocatorType.CLASS, "revenue-region", this.getElement());
        revenueElementList.forEach(revenueElement -> {
            revenues.add(RevenueRegion.getInstance(revenueElement));
        });
        return revenues;
    }
}
