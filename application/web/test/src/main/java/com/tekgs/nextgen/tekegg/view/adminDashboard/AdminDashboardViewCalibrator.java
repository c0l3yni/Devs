package com.tekgs.nextgen.tekegg.view.adminDashboard;

import com.softwareonpurpose.calibrator4test.Calibrator;
import com.tekgs.nextgen.tekegg.view.adminDashboard.loginFailureListRegion.loginFailureRegion.LoginFailureRegionCalibratable;
import com.tekgs.nextgen.tekegg.view.adminDashboard.loginFailureListRegion.loginFailureRegion.LoginFailureRegionCalibrator;

import java.util.List;

public class AdminDashboardViewCalibrator extends Calibrator {

    public static final String DESCRIPTION = "'Admin Dashboard' view";
    private final AdminDashboardViewExpected expected;
    private final AdminDashboardView actual;

    private AdminDashboardViewCalibrator(AdminDashboardViewExpected expected, AdminDashboardView actual) {
        super(DESCRIPTION, expected, actual);
        this.expected = expected;
        this.actual = actual;
        List<LoginFailureRegionCalibratable> expectedLoginFailureList = expected.inLoginFailureRegion().getLoginFailureList();
        List<LoginFailureRegionCalibratable> actualLoginFailureList = actual.inLoginFailureRegion().getLoginFailureList();

        addCalibrationsExpected(expectedLoginFailureList, actualLoginFailureList);
        addCalibrationsUnexpected(actualLoginFailureList);
    }

    public static AdminDashboardViewCalibrator getInstance(AdminDashboardViewExpected expected, AdminDashboardView actual) {
        return new AdminDashboardViewCalibrator(expected, actual);
    }

    private void addCalibrationsUnexpected(List<LoginFailureRegionCalibratable> actualLoginFailureList) {
        actualLoginFailureList.forEach(loginFailureUnexpected -> {
            addChildCalibrator(LoginFailureRegionCalibrator.getInstance(null, loginFailureUnexpected));
        });
    }

    private void addCalibrationsExpected(List<LoginFailureRegionCalibratable> expectedLoginFailureList, List<LoginFailureRegionCalibratable> actualLoginFailureList) {
        expectedLoginFailureList.forEach(expectedLoginFailure -> {
            LoginFailureRegionCalibratable loginFailureFound = addCalibrationFound(actualLoginFailureList, expectedLoginFailure);
            if (loginFailureFound == null) {
                addChildCalibrator(LoginFailureRegionCalibrator.getInstance(expectedLoginFailure, null));
            } else {
                actualLoginFailureList.remove(loginFailureFound);
            }
        });
    }

    private LoginFailureRegionCalibratable addCalibrationFound(List<LoginFailureRegionCalibratable> actualLoginFailureList, LoginFailureRegionCalibratable expectedLoginFailure) {
        LoginFailureRegionCalibratable loginFailureFound = actualLoginFailureList.stream()
                .filter(actualLoginFailure -> actualLoginFailure.equivalent(expectedLoginFailure))
                .findFirst()
                .orElse(null);
        if (loginFailureFound != null) {
            addChildCalibrator(LoginFailureRegionCalibrator.getInstance(expectedLoginFailure, loginFailureFound));
        }
        return loginFailureFound;
    }

    @Override
    protected void executeVerifications() {
        verify("'Login Failure' title", expected.getLoginFailureListTitle(), actual.getLoginFailureListTitle());
    }
}
