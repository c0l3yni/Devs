package com.tekgs.nextgen.tekegg.view.adminDashboard;

import com.tekgs.nextgen.tekegg.view.adminDashboard.loginFailureListRegion.LoginFailureListRegionCalibratable;

public interface AdminDashboardViewCalibratable {

    String getLoginFailureListTitle();

    LoginFailureListRegionCalibratable inLoginFailureRegion();
}
