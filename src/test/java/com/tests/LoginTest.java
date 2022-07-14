package com.tests;

import base.BaseTest;
import com.automate.constants.StringConstants;
import com.automate.customannotations.FrameworkAnnotation;
import com.automate.enums.CategoryType;
import com.automate.pages.LoginPage;
import com.automate.entity.TestData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LoginTest extends BaseTest {

    LoginPage loginPage;

    @BeforeMethod
    public void initialize() {
        loginPage = new LoginPage();
    }

    @FrameworkAnnotation(author = "User-1", category = {CategoryType.REGRESSION, CategoryType.SMOKE})
    @Test(description = "Incorrect Username and Password combination")
    public void invalidLogin(TestData data) {
        loginPage.setUsername(data.getLoginData().getLoginUsername())
                .setPassword(data.getLoginData().getLoginPassword())
                .tapOnLogin();
        String invalidLoginErrorMessage = loginPage.getErrorText();

        Assert.assertEquals(invalidLoginErrorMessage, StringConstants.INVALID_LOGIN_ERROR_MESSAGE, "Assertion for Invalid login error");
    }

    @FrameworkAnnotation(author = "User-2", category = {CategoryType.REGRESSION, CategoryType.SANITY})
    @Test(description = "Correct Username and Password combination")
    public void validLogin(TestData data) {
        String productPageTitle = loginPage.setUsername(data.getLoginData().getLoginUsername())
                .setPassword(data.getLoginData().getLoginPassword())
                .tapOnLogin()
                .getProductPageTitle();

        Assert.assertEquals(productPageTitle, StringConstants.PRODUCT_PAGE_TITLE, "Assertion for valid login");
    }
}
