package sofascore;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by gfox on 24/05/2016.
 */
public class AndroidSetup {

    protected AndroidDriver driver;
    protected AppiumDriverLocalService service;
    protected DesiredCapabilities capabilities;
    protected boolean firstLaunch = false;

    protected void prepareAndroidForAppium() throws MalformedURLException {

        //starts the appium server
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.usingAnyFreePort();
        service = AppiumDriverLocalService.buildService(serviceBuilder);
        service.start();
        //Use '/' for MAC '\\' For Windows
        File appDir = new File("src/main/resources");
        File app = new File(appDir, "sofascore.apk");
        capabilities = new DesiredCapabilities();
        //capabilities.setCapability("device", "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        //capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
        //IOS/Android/FireFoxOS
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        //path to apk
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 500);
        //if you are connecting to a specific device(emulator or physical) if 2 devices are connected and this is not specified then the first available device will be run
        capabilities.setCapability(MobileCapabilityType.UDID, "0f576837");
        driver = new AndroidDriver(new URL("http://127.0.0.1:" + service.getUrl().getPort() + "/wd/hub"), capabilities);
        firstLaunch = true;
    }

    /**
     * Other useful methods to use in appium
     * See https://developer.android.com/reference/android/support/test/uiautomator/UiSelector.html
     *
     * @return
     */
    public AndroidDriver otherFunctionalityToUse() throws IOException {
        //sets the package name(Can be used if you can't install the apk directly)
        capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "com.sofascore.results");
        //sets the activity(each page can have a different activity.MainActivity is normally the homepage)
        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.sofascore.results.activity.MainActivity");

        MobileElement elementByAndroidUIAutomator = (MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Preference\")");
        driver.findElementsByAndroidUIAutomator("new UiSelector().clickable(true)");
        driver.pressKeyCode(AndroidKeyCode.HOME);
        //returns whether the context is Native,Hybrid or Web app
        driver.getContext();
        //tap on element
        elementByAndroidUIAutomator.tap(1, 2);
        //Long press on an element
        TouchAction touchAction = new TouchAction(driver);
        touchAction.longPress(elementByAndroidUIAutomator, 2).release();
        touchAction.perform();

        //switches to frame
        driver.switchTo().frame(0);
        //if you are in a frame within a frame you can default back to the parent frame(Not the main web page)
        driver.switchTo().parentFrame();
        //switches to main page
        driver.switchTo().defaultContent();

        //take screenshot
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
// Now you can do whatever you need to do with it, for example copy somewhere
        FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot.png"));

        return driver;
    }
}
