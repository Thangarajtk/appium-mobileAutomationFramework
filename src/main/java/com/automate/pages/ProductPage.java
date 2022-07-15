package com.automate.pages;

import com.automate.enums.WaitStrategy;
import com.automate.pages.screen.ScreenActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public final class ProductPage extends ScreenActions {

	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")
	private static MobileElement productPageTitle;

	public String getProductPageTitle() {
		return getText(productPageTitle, WaitStrategy.VISIBLE);
	}
}
