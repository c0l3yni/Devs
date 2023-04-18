package com.tekgs.nextgen.tekegg.view.adminDashboard.loginFailureListRegion.loginFailureRegion;

import com.tekgs.nextgen.tekegg.data.admin.LoginFailure;

public class LoginFailureRegionExpected implements LoginFailureRegionCalibratable {
    private final LoginFailure loginFailure;

    public LoginFailureRegionExpected(LoginFailure loginFailure) {
        this.loginFailure = loginFailure;
    }

    public static LoginFailureRegionExpected getInstance(LoginFailure loginFailure) {
        return new LoginFailureRegionExpected(loginFailure);
    }

    @Override
    public boolean equivalent(LoginFailureRegionCalibratable expectedLoginFailure) {
        return false;
    }

    @Override
    public String getDate() {
        return this.loginFailure.getDate();
    }

    @Override
    public String getFailures() {
        return String.valueOf(this.loginFailure.getFailures());
    }
}
