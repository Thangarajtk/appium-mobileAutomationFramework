package com.automate.pages;

import com.automate.enums.WaitStrategy;
import com.automate.pages.screen.ScreenActions;
import com.automate.entity.LoginData;
import com.automate.utils.DecodeUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public final class LoginPage extends ScreenActions {

    @AndroidFindBy(accessibility = "test-Username")
    @iOSXCUITFindBy(accessibility = "test-Username")
    private static MobileElement txtFieldUsername;

    @AndroidFindBy(accessibility = "test-Password")
    @iOSXCUITFindBy(accessibility = "test-Password")
    private static MobileElement txtFieldPassword;

    @AndroidFindBy(accessibility = "test-LOGIN")
    @iOSXCUITFindBy(accessibility = "test-LOGIN")
    private static MobileElement btnLogin;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Error message']/android.widget.TextView")
    private static MobileElement errorMessage;

    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(txtFieldUsername);
    }

    public LoginPage setUsername(String username) {
        enter(txtFieldUsername, username, "Username");
        return this;
    }

    public LoginPage setPassword(String password) {
        enter(txtFieldPassword, password, "Password");
        return this;
    }

    public ProductPage tapOnLogin() {
        click(btnLogin, "Login");
        return new ProductPage();
    }

    public ProductPage login(LoginData loginData) {
        setUsername(loginData.getLoginUsername())
                .setPassword(loginData.getLoginPassword())
                .tapOnLogin();

        return new ProductPage();
    }

    public String getErrorText() {
        return getText(errorMessage, WaitStrategy.VISIBLE);
    }
}
