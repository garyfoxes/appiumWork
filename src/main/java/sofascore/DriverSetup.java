package sofascore;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by gfox on 24/05/2016.
 * <p>
 * // The following may have to be run before each test, problems with chrome through emulator (adb uninstall io.appium.settings; adb uninstall io.appium.unlock)
 * Works on 1.6 running the server through the code but the Appium.dmg is on 1.5.3 and has issues so run the command above to get it to work.
 * Check and see if 1.6 is available on the client.
 */
public class DriverSetup {

    protected AndroidDriver driver;
    protected WebDriver weDriver;
    protected IOSDriver iosDriver;
    protected AppiumDriverLocalService service;
    protected DesiredCapabilities capabilities;
    protected boolean firstLaunch = false;

    protected void prepareAndroidForAppium(String appName,String browserName) throws MalformedURLException {
        System.out.println("APP NAME " + appName + " BROWSER NAME " + browserName);
        //starts the appium server
       /* AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.usingAnyFreePort();
        service = AppiumDriverLocalService.buildService(serviceBuilder);
        service.start();*/
        //Use '/' for MAC '\\' For Windows
        File appDir = new File("src/main/resources");
        File app = new File(appDir, "sofascore.apk");
        capabilities = new DesiredCapabilities();
        //capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.1");
        //capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.MAC);
        //capabilities.setCapability("appPackage", "com.android.chrome");
        //capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        //capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
        //capabilities.setCapability("noReset", true);
        //capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");


        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 500);
        //IOS/Android/FireFoxOS
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        if (browserName.equalsIgnoreCase("Chrome")) {
            //capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
            capabilities.setCapability("applicationName", appName);
            //0f576837
            driver = new AndroidDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
            driver.get("http://redcafe.net");
        } else {
            //path to apk
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
            capabilities.setCapability("applicationName", appName);
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            //if you are connecting to a specific device(emulator or physical) if 2 devices are connected and this is not specified then the first available device will be run
            //udid is gotten from ruuning adb devices(emulator will be something like emulator-5554,device will be something like 0f576837)
            //capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
            capabilities.setCapability("appPackage", "com.sofascore.results"); // This is package name of your app (you can get it from apk info app)
            capabilities.setCapability("appActivity", "com.sofascore.results.activity.StartActivity");
            //driver = new AndroidDriver(new URL("http://127.0.0.1:" + service.getUrl().getPort() + "/wd/hub"), capabilities);
            driver = new AndroidDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
        }

        firstLaunch = true;
    }

    public void prepareIOSForAppium() throws MalformedURLException {
        File appDir = new File("src/main/resources");
        File app = new File(appDir, "myApp.zip");
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.usingAnyFreePort();
        service = AppiumDriverLocalService.buildService(serviceBuilder);
        service.start();
        capabilities = new DesiredCapabilities();
        //the setting below is if you want to run tests in safari browser on your emulator
        //capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.SAFARI);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.4");
        //this needs to match the device list in Xcode "Windows/devices"
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        iosDriver = new IOSDriver(new URL("http://127.0.0.1:" + service.getUrl().getPort() + "/wd/hub"), capabilities);
        iosDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        firstLaunch = true;
    }

    public void setUpForSaucelabs() throws MalformedURLException {
        capabilities = DesiredCapabilities.iphone();
        capabilities.setCapability("appiumVersion", "1.5.2");
        capabilities.setCapability("deviceName", "iPhone 6");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("platformVersion", "9.2");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("browserName", "");
        //the .app file needs to be zipped on sauce labs storage
        capabilities.setCapability("app", "sauce-storage:myApp.zip");
        iosDriver = new IOSDriver(new URL("http://gfox:261ec9d3-5015-46a2-81c7-7960e448f868@ondemand.saucelabs.com:80/wd/hub"), capabilities);
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
        //capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "com.sofascore.results");
        //sets the activity(each page can have a different activity.MainActivity is normally the homepage)
        //capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.sofascore.results.activity.MainActivity");


        //Using UI Selector
        MobileElement elementByAndroidUIAutomator = (MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Preference\")");
        driver.findElementsByAndroidUIAutomator("new UiSelector().clickable(true)");
        driver.pressKeyCode(AndroidKeyCode.HOME);


        //returns whether the context is Native,Hybrid or Web app
        driver.getContext();
        //Could return 2 contexts if you are working with a hybrid app
        //To enable WebView debugging, developers need to call the static method setWebContentsDebuggingEnabled on the WebView class
        driver.getContextHandles();


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
        File scrFile = driver.getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy somewhere
        FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot.png"));


        return driver;
    }
}
