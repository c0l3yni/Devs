package com.tekgs.nextgen.tekegg.view.adminDashboard;

import com.tekgs.nextgen.tekegg.view.adminDashboard.loginFailureListRegion.LoginFailureListRegionExpected;

import java.util.ArrayList;
import java.util.List;

public class AdminDashboardViewExpected implements AdminDashboardViewCalibratable {
    private final List<String> loginFailureEntryList = new ArrayList<>();

    private AdminDashboardViewExpected(List<String> loginFailureEntryList) {
        this.loginFailureEntryList.addAll(loginFailureEntryList);
    }

    public static AdminDashboardViewExpected getInstance(List<String> loginFailureEntryList) {
        return new AdminDashboardViewExpected(loginFailureEntryList);
    }

    @Override
    public String getLoginFailureListTitle() {
        return "Login Failures";
    }

    @Override
    public LoginFailureListRegionExpected inLoginFailureRegion() {
        return LoginFailureListRegionExpected.getInstance(this.loginFailureEntryList);
    }
}
