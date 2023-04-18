package com.tekgs.nextgen.tekegg.view.error;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiView;
import com.tekgs.nextgen.tekegg.view.TekEggView;
import com.tekgs.nextgen.tekegg.view.landing.LandingView;

public class ErrorView extends TekEggView implements ErrorViewCalibratable {
    private static final String LOCATOR_VALUE = "error";
    private static final String LOCATOR_TYPE = UiLocatorType.ID;
    private static final String DESCRIPTION = "'Error' view";
    private static final String RELATIVE_URI = "error";

    public ErrorView() {
        super(RELATIVE_URI, UiElement.getInstance(DESCRIPTION, LOCATOR_TYPE, LOCATOR_VALUE));
    }

    public static ErrorView directNav() {
        new ErrorView().load();
        return UiView.expect(ErrorView.class);
    }

    public static ErrorView directNav(String url) {
        new ErrorView().load(url);
        return UiView.expect(ErrorView.class);
    }

    @Override
    public String getErrorMessage() {
        String description = " 'Error' message";
        String locatorValue = "error-message";
        UiElement errorMessageElement = getUiElementById(description, locatorValue);
        return errorMessageElement.getText();
    }


    @Override
    protected boolean areKeyElementsLoaded() {
        return true;
    }

    public LandingView returnHome() {
        getReturnHomeElement().click();
        return UiView.expect(LandingView.class);
    }

    private UiElement getReturnHomeElement() {
        String description = "'Return home' button";
        String locatorValue = "return-home-button";
        return this.getUiElementById(description, locatorValue);
    }
}
