package com.tekgs.nextgen.tekegg.view.financialDashboard.revenue;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiRegion;

public class RevenueRegion extends UiRegion implements RevenueRegionCalibratable {

    private RevenueRegion(UiElement revenueElement) {
        super(revenueElement);
    }

    public static RevenueRegion getInstance(UiElement revenueElement) {
        return new RevenueRegion(revenueElement);
    }


    private UiElement getMonthElement(){
        return UiElement.getInstance("'Revenue' month", UiLocatorType.CLASS, "revenue-month", this.getElement());
    }
    @Override
    public String getMonth() {
        return getMonthElement().getText();
    }

    private UiElement getRevenueElement() {
        return UiElement.getInstance("'Revenue' amount", UiLocatorType.CLASS, "revenue-amount", this.getElement());
    }

    @Override
    public String getNetRevenue() {
        return getRevenueElement().getText();
    }

    @Override
    public boolean equivalent(RevenueRegionCalibratable comparator) {
        return comparator.getMonth() == null || this.getMonth().equals(comparator.getMonth());
    }
}
