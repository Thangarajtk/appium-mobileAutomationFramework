package com.automate.utils.dataprovider;

import com.automate.constants.FrameworkConstants;
import com.automate.entity.LoginData;
import com.automate.entity.SearchData;
import com.automate.entity.TestData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataProviderUtils {

    private static List<Map<String, String>> list = new ArrayList<>();

    @DataProvider
    public static Object[][] getData(Method method) {
        LoginData loginData;
        SearchData searchData;
        TestData testData = null;
        String testName = method.getName();

        if (list.isEmpty())
            list = ExcelUtils.getTestDetails(FrameworkConstants.getTestDataSheet());

        List<Map<String, String>> smallList = new ArrayList<>(list);

        Predicate<Map<String, String>> isTestNameNotMatching = map -> !map.get("TestCaseName").equalsIgnoreCase(testName);

        smallList.removeIf(isTestNameNotMatching);

        for (Map<String, String> mapData : smallList) {
           
            loginData = LoginData.builder()
                    .setLoginUsername(mapData.get("username"))
                    .setLoginPassword(mapData.get("password"))
                    .build();

            searchData = SearchData.builder()
                    .setSearchText(mapData.get("searchTerm"))
                    .build();

            testData = TestData.builder()
                    .setLoginData(loginData)
                    .setSearchData(searchData)
                    .build();
        }
        return new Object[][]{
                {testData}
        };
    }
}

