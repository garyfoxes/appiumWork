package sofascore;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by gfox on 24/05/2016.
 */
public class AndroidSetup {

    protected AndroidDriver driver;
    protected AppiumDriverLocalService service;

    protected void prepareAndroidForAppium() throws MalformedURLException {

        //starts the appium server
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.usingAnyFreePort();
        service = AppiumDriverLocalService.buildService(serviceBuilder);
        service.start();
        File appDir = new File("src\\main\\resources");
        File app = new File(appDir, "sofascore.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability("device", "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        //IOS/Android/FireFoxOS
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        //path to apk
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 500);
        //if you are connecting to a specific device(emulator or physical) if 2 devices are connected and this is not specified then the first available device will be run
        capabilities.setCapability(MobileCapabilityType.UDID, "0f576837");
        driver = new AndroidDriver(new URL("http://127.0.0.1:" + service.getUrl().getPort() + "/wd/hub"), capabilities);
    }
}
