package com.tekgs.nextgen.tekegg.view.adminDashboard.loginFailureListRegion.loginFailureRegion;

public interface LoginFailureRegionCalibratable {
    boolean equivalent(LoginFailureRegionCalibratable expectedLoginFailure);

    String getDate();

    String getFailures();
}
