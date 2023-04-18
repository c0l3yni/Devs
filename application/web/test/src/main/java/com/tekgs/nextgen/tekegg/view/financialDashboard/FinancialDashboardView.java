package com.tekgs.nextgen.tekegg.view.financialDashboard;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiView;
import com.tekgs.nextgen.tekegg.view.TekEggView;
import com.tekgs.nextgen.tekegg.view.financialDashboard.revenue.RevenueListRegion;

public class FinancialDashboardView extends TekEggView implements FinancialDashboardViewCalibratable {
    private static final String LOCATOR_VALUE = "financial-dashboard";
    private static final String LOCATOR_TYPE = UiLocatorType.ID;
    private static final String DESCRIPTION = "'Financial Dashboard' view";
    private static final String RELATIVE_URI = "financial-dashboard";

    public FinancialDashboardView() {
        super(RELATIVE_URI, UiElement.getInstance(DESCRIPTION, LOCATOR_TYPE, LOCATOR_VALUE));
    }

    public static FinancialDashboardView directNav() {
        new FinancialDashboardView().load();
        return UiView.expect(FinancialDashboardView.class);
    }

    @Override
    public RevenueListRegion inRevenueRegion() {
        return RevenueListRegion.getInstance(this.getElement());
    }

    @Override
    public String getRevenueListTitle() {
        String description = "'Revenue' list title";
        String locatorValue = "revenue-title";
        UiElement revenueListTitleElement = getUiElementById(description, locatorValue);
        return revenueListTitleElement.getText();
    }

    @Override
    protected boolean areKeyElementsLoaded() {
        return true;
    }
}
