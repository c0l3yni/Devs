package com.tekgs.nextgen.tekegg.data.admin;

public class LoginFailure {
    private final String date;
    private final int failures;

    public LoginFailure(String date, int failures) {
        this.date = date;
        this.failures = failures;
    }

    public static LoginFailure getInstance(String date, int failures){
        return new LoginFailure(date,failures);
    }

    public String getDate() {
        return this.date;
    }

    public Integer getFailures() {
        return this.failures;
    }
}
