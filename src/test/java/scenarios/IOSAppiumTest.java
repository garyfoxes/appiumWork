package scenarios;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import sofascore.DriverSetup;

/**
 * Created by gfox on 03/06/2016.
 */
public class IOSAppiumTest extends DriverSetup {
    @BeforeClass
    public void setUp() throws Exception {
        prepareIOSForAppium();
        //setUpForSaucelabs();
    }

    @BeforeMethod
    public void beforeMethod() {
        if (!firstLaunch) {
            iosDriver.launchApp();
        }
    }

    @AfterClass
    public void tearDown() throws Exception {
        iosDriver.quit();
        service.stop();
    }

    @AfterMethod()
    public void afterMethod() {
        iosDriver.closeApp();
        firstLaunch = false;
    }


    @Test(description = "IOS Sample Code")
    public void test() throws InterruptedException {

        //iosDriver.scrollTo("Switches").click();
        ((MobileElement) iosDriver.findElements(By.className("UIASwitch")).get(0)).click();
        iosDriver.findElement(By.xpath("//UIAButton[@name='UICatalog']")).click();
        //iosDriver.scrollTo("Picker View").click();
        ((MobileElement) iosDriver.findElements(By.className("UIAPickerWheel")).get(0)).sendKeys("85");
        ((MobileElement) iosDriver.findElements(By.className("UIAPickerWheel")).get(1)).sendKeys("215");
        iosDriver.findElement(By.xpath("//UIAButton[@name='UICatalog']")).click();
        //iosDriver.scrollTo("Alert Views").click();
        iosDriver.findElement(By.xpath("//*[@name='Simple']")).click();
        iosDriver.switchTo().alert().accept();

        //rest api for interacting with jobs
     /*   SauceREST rest = new SauceREST("gfox","261ec9d3-5015-46a2-81c7-7960e448f868");
        rest.jobPassed(iosDriver.getSessionId().toString());*/

    }

}
