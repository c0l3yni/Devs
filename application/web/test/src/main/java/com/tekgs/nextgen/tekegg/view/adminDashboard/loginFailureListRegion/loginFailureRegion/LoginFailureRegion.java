package com.tekgs.nextgen.tekegg.view.adminDashboard.loginFailureListRegion.loginFailureRegion;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiRegion;

public class LoginFailureRegion extends UiRegion implements LoginFailureRegionCalibratable {
    public LoginFailureRegion(UiElement regionElement) {
        super(regionElement);
    }

    public static LoginFailureRegion getInstance(UiElement loginFailureElement) {
        return new LoginFailureRegion(loginFailureElement);
    }

    @Override
    public boolean equivalent(LoginFailureRegionCalibratable comparator) {
        boolean isEquivalent = comparator.getDate() == null || this.getDate().equals(comparator.getDate());
        isEquivalent &= comparator.getFailures() == null || this.getFailures().equals(comparator.getFailures());
        return isEquivalent;
    }

    @Override
    public String getDate() {
        return getDateElement().getText();
    }

    private UiElement getDateElement() {
        return UiElement.getInstance("'Login Failures' date", UiLocatorType.CLASS, "date-of-failures", this.getElement());
    }

    @Override
    public String getFailures() {
        return getFailuresElement().getText();
    }

    private UiElement getFailuresElement() {
        return UiElement.getInstance("'Login Failures' count", UiLocatorType.CLASS, "login-failures-count", this.getElement());
    }
}
