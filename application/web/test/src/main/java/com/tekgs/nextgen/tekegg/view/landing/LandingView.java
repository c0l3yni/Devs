package com.tekgs.nextgen.tekegg.view.landing;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiView;
import com.tekgs.nextgen.tekegg.view.TekEggView;

public class LandingView extends TekEggView implements LandingViewCalibratable {
    private static final String LOCATOR_VALUE = "landing";
    private static final String LOCATOR_TYPE = UiLocatorType.ID;
    private static final String DESCRIPTION = " 'Landing' view";
    private static final String RELATIVE_URI = "";


    public LandingView() {
        super(RELATIVE_URI, UiElement.getInstance(DESCRIPTION, LOCATOR_TYPE, LOCATOR_VALUE));
    }

    public static LandingView directNav() {
        new LandingView().load();
        return UiView.expect(LandingView.class);
    }

    @Override
    public String getTitle() {
        String description = "'Landing' title";
        String locatorValue = "landing-title";
        UiElement title = getUiElementById(description, locatorValue);
        return title.getText();
    }

    @Override
    protected boolean areKeyElementsLoaded() {
        return true;
    }
}
