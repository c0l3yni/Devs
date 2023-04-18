package com.tekgs.nextgen.tekegg.view;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiView;
import com.softwareonpurpose.uinavigator.web.WebUiHost;
import org.softwareonpurpose.gauntlet.Environment;

public abstract class TekEggView extends UiView {
    private static final String DOMAIN = Environment.getInstance().getDomainUrl();

    protected TekEggView(String relativeUri, UiElement viewElement) {
        super(String.format("%s/%s", DOMAIN, relativeUri), viewElement);
    }

    @Override
    protected boolean confirmElementStates() {
        long start = System.currentTimeMillis();
        boolean confirmed = WebUiHost.getInstance().getAddress().contains(this.getAddress());
        confirmed &= this.getElement().waitUntilVisible();
        confirmed &= areKeyElementsLoaded();
        confirmed &= System.currentTimeMillis() - start < 3000;
        return confirmed;
    }

    protected abstract boolean areKeyElementsLoaded();

    protected UiElement getUiElementById(String description, String locatorValue) {
        return UiElement.getInstance(description, UiLocatorType.ID, locatorValue, this.getElement());
    }
}
