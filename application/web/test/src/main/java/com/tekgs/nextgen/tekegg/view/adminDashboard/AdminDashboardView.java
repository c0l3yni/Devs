package com.tekgs.nextgen.tekegg.view.adminDashboard;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiView;
import com.tekgs.nextgen.tekegg.view.TekEggView;
import com.tekgs.nextgen.tekegg.view.adminDashboard.loginFailureListRegion.LoginFailureListRegion;
import com.tekgs.nextgen.tekegg.view.adminDashboard.loginFailureListRegion.LoginFailureListRegionCalibratable;

public class AdminDashboardView extends TekEggView implements AdminDashboardViewCalibratable {
    private static final String LOCATOR_VALUE = "admin-dashboard";
    private static final String LOCATOR_TYPE = UiLocatorType.ID;
    private static final String DESCRIPTION = "'Admin Dashboard' view";
    private static final String RELATIVE_URI = "admin-dashboard";

    public AdminDashboardView() {
        super(RELATIVE_URI, UiElement.getInstance(DESCRIPTION, LOCATOR_TYPE, LOCATOR_VALUE));
    }

    public static AdminDashboardView directNav() {
        new AdminDashboardView().load();
        return UiView.expect(AdminDashboardView.class);
    }

    public static AdminDashboardView directNav(String failureLog) {
        new AdminDashboardView().load(String.format("?failure_log=%s", failureLog));
        return UiView.expect(AdminDashboardView.class);
    }

    @Override
    public String getLoginFailureListTitle() {
        String description = "'Login Failures' list title";
        String locatorValue = "login-failures";
        return getUiElementById(description, locatorValue).getText();
    }

    @Override
    public LoginFailureListRegionCalibratable inLoginFailureRegion() {
        return LoginFailureListRegion.getInstance(this.getElement());
    }

    @Override
    protected boolean areKeyElementsLoaded() {
        return true;
    }
}
