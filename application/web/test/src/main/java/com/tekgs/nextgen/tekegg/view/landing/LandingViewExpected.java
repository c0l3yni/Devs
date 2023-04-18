package com.tekgs.nextgen.tekegg.view.landing;

public class LandingViewExpected implements LandingViewCalibratable {
    private final String title = "Landing Page";

    public static LandingViewExpected getInstance() {
        return new LandingViewExpected();
    }

    @Override
    public String getTitle() {
        return this.title;
    }
}
