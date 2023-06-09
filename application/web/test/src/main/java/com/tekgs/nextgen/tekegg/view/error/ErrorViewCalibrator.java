package com.tekgs.nextgen.tekegg.view.error;

import com.softwareonpurpose.calibrator4test.Calibrator;

public class ErrorViewCalibrator extends Calibrator{

    private static final String DESCRIPTION = "'Error' view";
    private final ErrorViewExpected expected;
    private final ErrorView actual;
    protected ErrorViewCalibrator(ErrorViewExpected expected, ErrorView actual) {
        super(DESCRIPTION, expected, actual);
        this.expected = expected;
        this.actual = actual;
    }

    public static Calibrator getInstance(ErrorViewExpected expected, ErrorView actual) {
        return new ErrorViewCalibrator(expected, actual);
    }

    @Override
    protected void executeVerifications() {
        verify("'Error Page' message", this.expected.getErrorMessage(), this.actual.getErrorMessage());
    }
}
