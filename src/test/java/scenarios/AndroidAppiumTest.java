package scenarios;

import io.appium.java_client.MobileElement;
import org.testng.annotations.*;
import sofascore.DriverSetup;

import java.net.MalformedURLException;

public class AndroidAppiumTest extends DriverSetup {

    private String applicationName;
    private String browserName;


    @Factory(dataProvider = "parallelDp")
    public AndroidAppiumTest(String applicationName, String browserName) {
        this.applicationName = applicationName;
        this.browserName = browserName;
    }

    @DataProvider(name = "parallelDp", parallel = true)
    public static Object[][] parallelDp() {
        return new Object[][]{
                {"Samsung S4", "Samsung S4"},
                {"Emulator 554", "Chrome"}
        };
    }

    @Parameters("myName")
    @BeforeClass()
    public void setUp(String myName) throws Exception {
        System.out.println("MY NAME IS " + myName);
    }

    @BeforeMethod
    public void beforeMethod() {
      /*  driver.unlockDevice();
        if (!firstLaunch) {
            driver.launchApp();
        }*/
        System.out.println("IN BEFORE METHOD");
    }

    @AfterClass
    public void tearDown() throws Exception {
        System.out.println("IN AFTER CLASS");
        driver.closeApp();
        driver.pressKeyCode(187);
        //Need to find element to kill app
        MobileElement dropBtn = (MobileElement) driver.findElementByXPath("//*[@resource-id='com.android.systemui:id/recents_RemoveAll_button']");
        dropBtn.click();
        driver.quit();
        //service.stop();
    }

    @AfterMethod()
    public void afterMethod() {
        System.out.println("IN AFTER METHOD");
        /*driver.closeApp();
        firstLaunch = false;*/
    }


    @Test(description = "Navigate To Event Page")
    public void test() throws InterruptedException, MalformedURLException {
        prepareAndroidForAppium(applicationName, browserName);
      /*  HomePage page = new HomePage(driver).selectTournament(Tournaments.INTERNATIONAL);
        Assert.assertTrue(page.isEventDisplayedForTournament(), "Event Is Not Displayed For Tournament");
        page.scrollDownToLastTournament(Tournaments.RUSSIA).selectTournament(Tournaments.RUSSIA);
        Assert.assertTrue(page.isEventDisplayedForTournament(), "Event Is Not Displayed For Tournament");
        EventPage eventPage = page.selectEvent();
        Assert.assertTrue(eventPage.isEventDetailsPresent(), "Event Details Are Not Displayed");*/
    }

    /*@Test(priority = 1, description = "Select date From Calendar")
    public void swipeToNextDayAndSelectEvent() throws InterruptedException {
        HomePage homePage = new HomePage(driver).swipeToNextDay().selectTournament(Tournaments.INTERNATIONAL);
        EventPage eventPage = homePage.selectEvent();
        Assert.assertTrue(eventPage.isEventDetailsPresent(), "Event Details Are Not Displayed");
        homePage = eventPage.selectBackButton().swipeToPreviousDay();
        CalendarComponent calendarComponent = homePage.selectCalendar();
        Assert.assertTrue(calendarComponent.isCalendarDisplayed(), "Calendar Is Not Displayed");
    }*/
}