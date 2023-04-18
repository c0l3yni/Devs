package com.tekgs.nextgen.tekegg.view.error;

public class ErrorViewExpected implements ErrorViewCalibratable {
    private final String errorMessage;

    public ErrorViewExpected(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static ErrorViewExpected getInstance(String errorMessage) {
        return new ErrorViewExpected(errorMessage);
    }
    public static ErrorViewExpected getInstance() {
        return new ErrorViewExpected("404 Page Not Found");
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
