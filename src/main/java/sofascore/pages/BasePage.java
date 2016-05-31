package sofascore.pages;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sofascore.enums.Position;

/**
 * Created by nishant on 13/09/14.
 */
public class BasePage {

    protected WebDriver driver;
    String app_package_name = "com.sofascore.results:id/";

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage() {
    }

    protected void waitForVisibilityOf(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForClickabilityOf(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void swipeHorizontalWithMultipleFingers(Position position) {
        int xcoordinates = 0;
        MultiTouchAction mta = new MultiTouchAction((MobileDriver) driver);
        Dimension d = driver.manage().window().getSize();
        switch (position) {
            case LEFT:
                xcoordinates = -70;
                break;
            case RIGHT:
                xcoordinates = 70;
                break;
        }
        TouchAction ta0 = new TouchAction((MobileDriver) driver).press(d.getWidth() / 2, d.getHeight() / 2).moveTo(xcoordinates, d.getHeight() / 2).release();
        TouchAction ta1 = new TouchAction((MobileDriver) driver).press(d.getWidth() / 2, d.getHeight() / 2).moveTo(xcoordinates, d.getHeight() / 2).release();
        mta.add(ta0).add(ta1).perform();
    }
}