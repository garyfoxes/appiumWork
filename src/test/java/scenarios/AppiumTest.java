package scenarios;

import org.testng.Assert;
import org.testng.annotations.*;
import sofascore.AndroidSetup;
import sofascore.enums.Tournaments;
import sofascore.pages.CalendarComponent;
import sofascore.pages.EventPage;
import sofascore.pages.HomePage;

import java.net.MalformedURLException;

public class AppiumTest extends AndroidSetup {

    @BeforeClass
    public void setUp() throws Exception {
        prepareAndroidForAppium();
    }

    @BeforeMethod
    public void beforeMethod() throws MalformedURLException {
        driver.launchApp();
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
        service.stop();
    }

    @AfterMethod()
    public void afterMethod() {
        driver.closeApp();
    }


    @Test(priority = 0, description = "Navigate To Event Page")
    public void test() throws InterruptedException {
        HomePage page = new HomePage(driver).selectTournament(Tournaments.INTERNATIONAL);
        Assert.assertTrue(page.isEventDisplayedForTournament(), "Event Is Not Displayed For Tournament");
        page.scrollDownToLastTournament(Tournaments.RUSSIA).selectTournament(Tournaments.RUSSIA);
        Assert.assertTrue(page.isEventDisplayedForTournament(), "Event Is Not Displayed For Tournament");
        EventPage eventPage = page.selectEvent();
        Assert.assertTrue(eventPage.isEventDetailsPresent(), "Event Details Are Not Displayed");
    }

    @Test(priority = 1, description = "Select date From Calendar")
    public void swipeToNextDayAndSelectEvent() {
        HomePage homePage = new HomePage(driver).swipeToNextDay().selectTournament(Tournaments.INTERNATIONAL);
        EventPage eventPage = homePage.selectEvent();
        Assert.assertTrue(eventPage.isEventDetailsPresent(), "Event Details Are Not Displayed");
        homePage = eventPage.selectBackButton().swipeToPreviousDay();
        CalendarComponent calendarComponent = homePage.selectCalendar();
        Assert.assertTrue(calendarComponent.isCalendarDisplayed(), "Calendar Is Not Displayed");
    }
}