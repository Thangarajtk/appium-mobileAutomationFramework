package com.automate.pages;

import com.automate.pages.screen.ScreenActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public final class SettingsPage extends ScreenActions {

    @AndroidFindBy(accessibility = "test-LOGOUT")
    private static MobileElement logOutButton;

    public LoginPage pressLogOutButton() {
        click(logOutButton, "Logout");
        return new LoginPage();
    }
}
