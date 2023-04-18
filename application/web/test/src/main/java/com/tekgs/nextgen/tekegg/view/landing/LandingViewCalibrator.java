package com.tekgs.nextgen.tekegg.view.landing;

import com.softwareonpurpose.calibrator4test.Calibrator;

public class LandingViewCalibrator extends Calibrator {
    private static final String DESCRIPTION = "'Landing' view";
    private final LandingViewExpected expected;
    private final LandingView actual;

    private LandingViewCalibrator(LandingViewExpected expected, LandingView actual) {
        super(DESCRIPTION, expected, actual);
        this.expected = expected;
        this.actual = actual;
    }

    public static LandingViewCalibrator getInstance(LandingViewExpected expected, LandingView actual){
        return new LandingViewCalibrator(expected,actual);
    }

    @Override
    protected void executeVerifications() {
        verify("'landing' title" , this.expected.getTitle(), this.actual.getTitle());

    }
}
