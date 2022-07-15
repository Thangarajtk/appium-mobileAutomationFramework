package com.tests;

import base.BaseTest;
import com.automate.constants.StringConstants;
import com.automate.customannotations.FrameworkAnnotation;
import com.automate.driver.manager.DriverManager;
import com.automate.entity.TestData;
import com.automate.enums.CategoryType;
import com.automate.enums.ConfigJson;
import com.automate.pages.GoogleSearchPage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.automate.utils.configloader.JsonUtils.getConfig;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GoogleTest extends BaseTest {

    @FrameworkAnnotation(author = "User-1", category = {CategoryType.REGRESSION, CategoryType.SANITY, CategoryType.SMOKE})
    @Test(description = "Google search")
    public void googleSearch(TestData data) {
        DriverManager.getDriver().get(getConfig(ConfigJson.URL));
        String searchResultsPageTitle = new GoogleSearchPage()
                .performSearch(data.getSearchData().getSearchText())
                .getSearchResultsPageTitle();

        Assert.assertEquals(searchResultsPageTitle, StringConstants.SEARCH_RESULTS_PAGE_TITLE);
    }
}
