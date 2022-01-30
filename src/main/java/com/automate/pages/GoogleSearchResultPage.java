package com.automate.pages;

import com.automate.driver.DriverManager;
import com.automate.pages.screen.ScreenActions;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class GoogleSearchResultPage extends ScreenActions {

	public String getSearchResultsPageTitle() {
		return DriverManager.getDriver().getTitle();
	}

}
