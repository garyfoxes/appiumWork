package sofascore.pages;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import sofascore.enums.Position;
import sofascore.enums.Sports;
import sofascore.enums.Tournaments;
import sofascore.objects.CalendarObjects;
import sofascore.objects.HomePageObjects;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver){
        super(driver);
        waitForVisibilityOf(HomePageObjects.tabGames());

    }

    public HomePage selectTournament(Tournaments tournament) throws InterruptedException {
        Thread.sleep(2000);
        if (!isTournamentDisplayed(tournament)) {
            scrollDownToLastTournament(tournament);
        }
        driver.findElement(HomePageObjects.lnktournamentType(tournament.getTournament())).click();
        return this;
    }

    public HomePage selectSportFromDropdown(Sports sport) {
        waitForVisibilityOf(HomePageObjects.dropDownListOfSports(sport.getSport()));
        driver.findElement(HomePageObjects.dropDownListOfSports(sport.getSport())).click();
        return this;
    }

    public boolean isEventDisplayedForTournament() {
        return driver.findElements(HomePageObjects.eventTiles()).size() != 0;
    }

    public boolean isTournamentDisplayed(Tournaments tournaments) {
        return driver.findElements(HomePageObjects.lnktournamentType(tournaments.getTournament())).size() != 0;
    }


    public HomePage scrollDownToLastTournament(Tournaments tournament) {
        while (driver.findElements(HomePageObjects.lnktournamentType(tournament.getTournament())).size() == 0) {
            Dimension d = driver.manage().window().getSize();
            ((MobileDriver) driver).swipe(d.getWidth() / 2, d.getHeight() / 2, d.getWidth() / 2, (d.getHeight() / 2) - 400, 0);
        }
        return this;
    }

    public EventPage selectEvent() {
        MobileElement element = (MobileElement) driver.findElements(HomePageObjects.eventTiles()).get(0);
        element.click();
        return new EventPage(driver);
    }

    public HomePage swipeToNextDay() {
        swipeHorizontalWithMultipleFingers(Position.LEFT);
        return this;
    }

    public HomePage swipeToPreviousDay() {
        swipeHorizontalWithMultipleFingers(Position.RIGHT);
        return this;
    }

    public CalendarComponent selectCalendar() {
        waitForVisibilityOf(CalendarObjects.imgCalendar());
        driver.findElement(CalendarObjects.imgCalendar()).click();
        return new CalendarComponent(driver);
    }
}
