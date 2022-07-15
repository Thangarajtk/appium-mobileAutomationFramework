package com.automate.pages;

import com.automate.driver.manager.DriverManager;
import com.automate.pages.screen.ScreenActions;

public final class GoogleSearchResultPage extends ScreenActions {

	public String getSearchResultsPageTitle() {
		return DriverManager.getDriver().getTitle();
	}

}
