package com.tekgs.nextgen.tekegg.view.adminDashboard.loginFailureListRegion;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiRegion;
import com.tekgs.nextgen.tekegg.view.adminDashboard.loginFailureListRegion.loginFailureRegion.LoginFailureRegion;
import com.tekgs.nextgen.tekegg.view.adminDashboard.loginFailureListRegion.loginFailureRegion.LoginFailureRegionCalibratable;

import java.util.ArrayList;
import java.util.List;

public class LoginFailureListRegion extends UiRegion implements LoginFailureListRegionCalibratable {

    private static final String DESCRIPTION = "'Login Failure' list region";
    private static final String LOCATOR_TYPE = UiLocatorType.ID;
    private static final String LOCATOR_VALUE = "login-failure-list";

    protected LoginFailureListRegion(UiElement parentElement) {
        super(UiElement.getInstance(DESCRIPTION, LOCATOR_TYPE, LOCATOR_VALUE, parentElement));
    }

    public static LoginFailureListRegionCalibratable getInstance(UiElement parentElement) {
        return new LoginFailureListRegion(parentElement);
    }

    @Override
    public List<LoginFailureRegionCalibratable> getLoginFailureList() {
        List<UiElement> loginFailureListElement = getLoginFailureListElement();
        return createLoginFailureList(loginFailureListElement);
    }

    private List<LoginFailureRegionCalibratable> createLoginFailureList(List<UiElement> loginFailureListElement) {
        List<LoginFailureRegionCalibratable> loginFailures = new ArrayList<>();
        loginFailureListElement.forEach(loginFailureElement -> {
            loginFailures.add(LoginFailureRegion.getInstance(loginFailureElement));
        });
        return loginFailures;
    }

    private List<UiElement> getLoginFailureListElement() {
        return UiElement.getList("'Login Failure' region", UiLocatorType.CLASS, "login-failure-region", this.getElement());
    }
}
