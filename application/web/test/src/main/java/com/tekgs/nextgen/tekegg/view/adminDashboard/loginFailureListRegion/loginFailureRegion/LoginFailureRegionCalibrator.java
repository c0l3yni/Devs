package com.tekgs.nextgen.tekegg.view.adminDashboard.loginFailureListRegion.loginFailureRegion;

import com.softwareonpurpose.calibrator4test.Calibrator;

public class LoginFailureRegionCalibrator extends Calibrator {


    public static final String DESCRIPTION = "'Login Failure' region";
    private final LoginFailureRegionCalibratable expected;
    private final LoginFailureRegionCalibratable actual;

    protected LoginFailureRegionCalibrator(LoginFailureRegionCalibratable expected, LoginFailureRegionCalibratable actual) {
        super(DESCRIPTION, expected, actual);
        this.expected = expected;
        this.actual = actual;
    }

    public static LoginFailureRegionCalibrator getInstance(LoginFailureRegionCalibratable expected, LoginFailureRegionCalibratable actual) {
        return new LoginFailureRegionCalibrator(expected, actual);
    }

    @Override
    protected void executeVerifications() {
        verify("'Login Failures' date", expected.getDate(), actual.getDate());
        verify("'Login Failures' count", expected.getFailures(), actual.getFailures());
    }
}
