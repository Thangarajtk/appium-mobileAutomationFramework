package com.automate.pages.screen;

import com.automate.driver.manager.DriverManager;
import com.automate.enums.MobileFindBy;
import com.automate.enums.WaitStrategy;
import com.automate.reports.ExtentReportLogStatus;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Ordering;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.PowerACState;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.automate.factories.WaitFactory.explicitlyWaitForElement;

public class ScreenActions {

    protected ScreenActions() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
    }

    private MobileElement getMobileElement(String mobileElement, MobileFindBy mobileFindBy) {
        switch (mobileFindBy) {
            case XPATH:
                return DriverManager.getDriver().findElementByXPath(mobileElement);
            case CSS:
                return DriverManager.getDriver().findElementByCssSelector(mobileElement);
            case ID:
                return DriverManager.getDriver().findElementById(mobileElement);
            case NAME:
                return DriverManager.getDriver().findElementByName(mobileElement);
            case ACCESSIBILITY_ID:
                return DriverManager.getDriver().findElementByAccessibilityId(mobileElement);
            case CLASS:
                return DriverManager.getDriver().findElementByClassName(mobileElement);
        }
        return null;
    }

    protected MobileElement getDynamicMobileElement(String mobileElement, MobileFindBy mobileFindBy) {
        if (mobileFindBy == MobileFindBy.XPATH) {
            return DriverManager.getDriver().findElement(By.xpath(mobileElement));
        } else if (mobileFindBy == MobileFindBy.CSS) {
            return DriverManager.getDriver().findElement(By.cssSelector(mobileElement));
        }
        return null;
    }

    protected void waitForPageLoad(int waitTime) {
        DriverManager.getDriver().manage().timeouts().pageLoadTimeout(waitTime, TimeUnit.SECONDS);
    }

    protected String getTextFromAttribute(WaitStrategy waitStrategy, MobileElement element) {
        return explicitlyWaitForElement(waitStrategy, element).getAttribute("text");
    }

    protected String getText(MobileElement element, WaitStrategy waitStrategy) {
        return explicitlyWaitForElement(waitStrategy, element).getText();
    }

    protected boolean isElementDisplayed(MobileElement element) {
        return element.isDisplayed();
    }

    protected void doClear(MobileElement element) {
        element.clear();
    }

    protected void getServerStatus() {
        DriverManager.getDriver().getStatus();
    }

    protected void setOrientation(ScreenOrientation screenOrientationType) {
        switch (screenOrientationType) {
            case LANDSCAPE:
                DriverManager.getDriver().rotate(ScreenOrientation.LANDSCAPE);
                ExtentReportLogStatus.logInfo("Device Orientation is set to Landscape");
                break;
            case PORTRAIT:
                DriverManager.getDriver().rotate(ScreenOrientation.PORTRAIT);
                ExtentReportLogStatus.logInfo("Device Orientation is set to Portrait");
                break;
            default:
                throw new IllegalStateException("Unexpected value in Screen Orientation: " + screenOrientationType);
        }
    }

    protected void backgroundApp() {
        DriverManager.getDriver().runAppInBackground(Duration.ofSeconds(10));
    }

    protected String getElementAttribute(MobileElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    protected boolean isElementSelected(MobileElement element) {
        return element.isSelected();
    }

    protected boolean isElementEnabled(MobileElement element) {
        return element.isEnabled();
    }

    protected WebElement getActiveElement() {
        return DriverManager.getDriver().switchTo().activeElement();
    }

    protected void moveMouseToElement(WebElement element, int xoffset, int yoffset) {
        new Actions(DriverManager.getDriver())
                .moveToElement(element, xoffset, yoffset)
                .perform();
        ExtentReportLogStatus.logInfo("Move to target element :" + element);
    }

    protected void doubleClickOnElement(WebElement element) {
        new Actions(DriverManager.getDriver())
                .moveToElement(element)
                .doubleClick()
                .perform();
        ExtentReportLogStatus.logInfo("Double click on element : " + element);
    }

    protected void performSingleTap(WebElement element) {
        new TouchActions(DriverManager.getDriver())
                .singleTap(element)
                .perform();
        ExtentReportLogStatus.logInfo("Single tap on element : " + element);
    }

    protected void performDoubleTap(WebElement element) {
        new TouchActions(DriverManager.getDriver())
                .doubleTap(element)
                .perform();
        ExtentReportLogStatus.logInfo("Double tap on element : " + element);
    }

    protected void performLongTap(WebElement element) {
        new TouchActions(DriverManager.getDriver())
                .longPress(element)
                .perform();
        ExtentReportLogStatus.logInfo("Long press on element : " + element);
    }

    protected void touchScreenScroll(WebElement element, int x, int y) {
        new TouchActions(DriverManager.getDriver())
                .scroll(element, x, y)
                .perform();
    }

    protected void hideKeyboard() {
        DriverManager.getDriver().hideKeyboard();
    }

    protected void scrollClickAndroid(String scrollableListId, String selectionText) {
        ((AndroidDriver<MobileElement>) DriverManager.getDriver()).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)."
                + "resourceId(\"" + scrollableListId + "\"))"
                + ".setAsHorizontalList().scrollIntoView(new UiSelector().text(\"" + selectionText + "\"))").click();
    }

    protected void click(MobileElement element, String elementName) {
        try {
            element.click();
            ExtentReportLogStatus.logInfo("Clicked on " + elementName);
        } catch (Exception e) {
            ExtentReportLogStatus.logFail("Exception occurred when clicking on - " + elementName, e);
        }
    }

    public void click(String element, MobileFindBy elementType, String elementName) {
        click(getMobileElement(element, elementType), elementName);
    }

    protected void enter(MobileElement element, String value, String elementName) {
        try {
            explicitlyWaitForElement(WaitStrategy.VISIBLE, element);
            doClear(element);
            element.sendKeys(value);
            ExtentReportLogStatus.logInfo("Entered value - <b>" + value + "</b> in the field " + elementName);
        } catch (Exception e) {
            ExtentReportLogStatus.logFail("Exception occurred while entering value in the field - " + elementName, e);
        }
    }

    protected void enterValueAndPressEnter(MobileElement element, String value, String elementName) {
        try {
            doClear(element);
            element.sendKeys(value, Keys.ENTER);
            ExtentReportLogStatus.logInfo("Entered value - <b>" + value + "</b> in the field " + elementName + " and pressed enter");
        } catch (Exception e) {
            ExtentReportLogStatus.logFail("Exception caught while entering value", e);
        }
    }

    protected void enter(String element, MobileFindBy elementType, String value, String elementName) {
        enter(getMobileElement(element, elementType), value, elementName);
    }

    public boolean isTextPresent(String containsText) {
        return DriverManager.getDriver().getPageSource().contains(containsText);
    }

    public void powerStateAndroid(String powerState) {
        switch (powerState) {
            case "ON":
                ((AndroidDriver<MobileElement>) DriverManager.getDriver()).setPowerAC(PowerACState.ON);
                break;
            case "OFF":
                ((AndroidDriver<MobileElement>) DriverManager.getDriver()).setPowerAC(PowerACState.OFF);
                break;
            default:
                ExtentReportLogStatus.warning("Voice state not available");
                break;
        }
    }

    /**
     * Swipe Down
     */
    public void swipeDown() {
        DriverManager.getDriver().executeScript("mobile:scroll",
                ImmutableMap.of("direction", "down"));
        ExtentReportLogStatus.logInfo("Swipe Down");
    }

    /**
     * Swipe Up
     */
    public void swipeUP() {
        DriverManager.getDriver().executeScript("mobile:scroll", ImmutableMap.of("direction", "up"));
        ExtentReportLogStatus.logInfo("Swipe Up");
    }

    /**
     * Accept Alert
     */
    public void acceptAlert() {
        DriverManager.getDriver().executeScript("mobile:acceptAlert");
        ExtentReportLogStatus.logInfo("Accept Alert");
    }

    /**
     * Dismiss Alert
     */
    public void dismissAlert() {
        DriverManager.getDriver().executeScript("mobile:dismissAlert");
        ExtentReportLogStatus.logInfo("Dismiss Alert");
    }

    /**
     * Long press key
     *
     * @param element element
     */
    public void longPress(MobileElement element) {
        try {
            new TouchAction<>(DriverManager.getDriver())
                    .longPress(ElementOption.element(element))
                    .perform();
        } catch (Exception e) {
            ExtentReportLogStatus.logFail("Exception caught while performing long press on the Mobile Element", e);
        }
    }

    /**
     * Scroll to specific location
     *
     */
    public void scrollToLocation() {
        try {
            HashMap<String, Double> scrollElement = new HashMap<>();
            scrollElement.put("startX", 0.50);
            scrollElement.put("startY", 0.95);
            scrollElement.put("endX", 0.50);
            scrollElement.put("endY", 0.01);
            scrollElement.put("duration", 3.0);
            DriverManager.getDriver().executeScript("mobile: swipe", scrollElement);
        } catch (Exception e) {
            ExtentReportLogStatus.logFail("Exception caught when scrolling to specific location", e);
        }
    }

    public boolean checkListIsSorted(List<String> listToSort) {
        if (!listToSort.isEmpty()) {
            try {
                if (Ordering.natural().isOrdered(listToSort)) {
                    ExtentReportLogStatus.logPass("List is sorted");
                    return true;
                } else {
                    ExtentReportLogStatus.logInfo("List is not sorted");
                    return false;
                }
            } catch (Exception e) {
                ExtentReportLogStatus.logFail("Exception caught when checking if list is sorted", e);
            }
        } else {
            ExtentReportLogStatus.warning("List is empty");
        }
        return false;
    }

    /**
     * Touch Actions
     *
     * @param a1   axis 1
     * @param b1   axis 2
     * @param a2   axis 3
     * @param b2   axis 4
     * @param time time
     */
    @SuppressWarnings("rawtypes")
    private void touchActions(int a1, int b1, int a2, int b2, int time) {
        TouchAction touchAction = new TouchAction(DriverManager.getDriver());
        touchAction.press(PointOption.point(a1, b1)).
                waitAction(WaitOptions.waitOptions(Duration.ofMillis(time))).
                moveTo(PointOption.point(a2, b2)).release();
        touchAction.perform();
    }

    /**
     * Swipe with axix
     *
     * @param x    x axis
     * @param y    y axis
     * @param x1   x1 axis
     * @param y1   y1 axis
     * @param time timeInMilli
     */
    protected void swipeAxis(int x, int y, int x1, int y1, int count, int time) {
        for (int i = 0; i < count; i++) {
            touchActions(x, y, x1, y1, time);
        }
    }

    /**
     * tap to element for 250sec
     *
     * @param androidElement element
     */
    @SuppressWarnings("rawtypes")
    public void tapByElement(MobileElement androidElement) {
        new TouchAction(DriverManager.getDriver())
                .tap(TapOptions.tapOptions().withElement(ElementOption.element(androidElement)))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(250))).perform();
    }

    /**
     * Tap by coordinates
     *
     * @param x x
     * @param y y
     */
    @SuppressWarnings("rawtypes")
    public void tapByCoordinates(int x, int y) {
        new TouchAction(DriverManager.getDriver())
                .tap(PointOption.point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(250))).perform();
    }

    /**
     * Press by element
     *
     * @param element element
     * @param seconds time
     */
    @SuppressWarnings("rawtypes")
    public void pressByElement(MobileElement element, long seconds) {
        new TouchAction(DriverManager.getDriver())
                .press(ElementOption.element(element))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(seconds)))
                .release()
                .perform();
    }

    /**
     * LongPress by element
     *
     * @param element element
     * @param seconds time
     */
    @SuppressWarnings("rawtypes")
    public void longPressByElement(MobileElement element, long seconds) {
        new TouchAction(DriverManager.getDriver())
                .longPress(ElementOption.element(element))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(seconds)))
                .release()
                .perform();
    }

    /**
     * Press by co-ordinates
     *
     * @param x       x
     * @param y       y
     * @param seconds time
     */
    @SuppressWarnings("rawtypes")
    public void pressByCoordinates(int x, int y, long seconds) {
        new TouchAction(DriverManager.getDriver())
                .press(PointOption.point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(seconds)))
                .release()
                .perform();
    }

    /**
     * Horizontal swipe by percentage
     *
     * @param startPercentage  start
     * @param endPercentage    end
     * @param anchorPercentage anchor
     */
    @SuppressWarnings("rawtypes")
    public void horizontalSwipeByPercentage(double startPercentage, double endPercentage, double anchorPercentage) {
        Dimension size = DriverManager.getDriver().manage().window().getSize();
        int anchor = (int) (size.height * anchorPercentage);
        int startPoint = (int) (size.width * startPercentage);
        int endPoint = (int) (size.width * endPercentage);
        new TouchAction(DriverManager.getDriver())
                .press(PointOption.point(startPoint, anchor))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(endPoint, anchor))
                .release().perform();
    }

    /**
     * Vertical swipe by percentage
     *
     * @param startPercentage  start
     * @param endPercentage    end
     * @param anchorPercentage anchor
     */
    @SuppressWarnings("rawtypes")
    public void verticalSwipeByPercentages(double startPercentage, double endPercentage, double anchorPercentage) {
        Dimension size = DriverManager.getDriver().manage().window().getSize();
        int anchor = (int) (size.width * anchorPercentage);
        int startPoint = (int) (size.height * startPercentage);
        int endPoint = (int) (size.height * endPercentage);

        new TouchAction(DriverManager.getDriver())
                .press(PointOption.point(anchor, startPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(anchor, endPoint))
                .release().perform();
    }

    /**
     * Swipe by elements
     *
     * @param startElement start
     * @param endElement   end
     */
    @SuppressWarnings("rawtypes")
    public void swipeByElements(MobileElement startElement, MobileElement endElement) {
        int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
        int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);

        int endX = endElement.getLocation().getX() + (endElement.getSize().getWidth() / 2);
        int endY = endElement.getLocation().getY() + (endElement.getSize().getHeight() / 2);

        new TouchAction(DriverManager.getDriver())
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(endX, endY))
                .release().perform();
    }

    /**
     * Multi touch by element
     *
     * @param androidElement element
     */
    @SuppressWarnings("rawtypes")
    public void multiTouchByElement(MobileElement androidElement) {
        TouchAction press = new TouchAction(DriverManager.getDriver())
                .press(ElementOption.element(androidElement))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .release();

        new MultiTouchAction(DriverManager.getDriver())
                .add(press)
                .perform();
    }

    /**
     * Swipe touch (UP,DOWN,LEFT,RIGHT)
     *
     * @param direction direction
     * @param count     count
     */
    protected void swipe(String direction, int count, int time) {
        Dimension size = DriverManager.getDriver().manage().window().getSize();
        try {
            switch (direction) {
                case "left":
                case "LEFT":
                    for (int i = 0; i < count; i++) {
                        int startx = (int) (size.width * 0.8);
                        int endx = (int) (size.width * 0.20);
                        int starty = size.height / 2;
                        touchActions(startx, starty, endx, starty, time);
                    }
                    break;
                case "right":
                case "RIGHT":
                    for (int j = 0; j < count; j++) {
                        int endx = (int) (size.width * 0.8);
                        int startx = (int) (size.width * 0.20);
                        int starty = size.height / 2;
                        touchActions(startx, starty, endx, starty, time);
                    }
                    break;
                case "up":
                case "UP":
                    for (int j = 0; j < count; j++) {
                        int starty = (int) (size.height * 0.80);
                        int endy = (int) (size.height * 0.20);
                        int startx = size.width / 2;
                        touchActions(startx, starty, startx, endy, time);
                    }
                    break;
                case "down":
                case "DOWN":
                    for (int j = 0; j < count; j++) {
                        int starty = (int) (size.height * 0.80);
                        int endy = (int) (size.height * 0.20);
                        int startx = size.width / 2;
                        touchActions(startx, endy, startx, starty, time);
                    }
                    break;
                default:
                    ExtentReportLogStatus.logInfo("Direction not found");
                    break;
            }
        } catch (Exception e) {
            ExtentReportLogStatus.logFail("Exception caught while performing Swipe", e);
        }
    }

    protected void closeApp() {
        ((InteractsWithApps) DriverManager.getDriver()).closeApp();
    }

    protected void launchApp() {
        ((InteractsWithApps) DriverManager.getDriver()).launchApp();
    }
}
