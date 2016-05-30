package sofascore.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import sofascore.enums.Sports;
import sofascore.enums.Tournaments;
import sofascore.objects.HomePageObjects;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage selectTournament(Tournaments tournament) {
        waitForVisibilityOf(HomePageObjects.lnktournamentType(tournament.getTournament()));
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

    public HomePage scrollDownToLastTournament(Tournaments tournament) {
        while (driver.findElements(HomePageObjects.lnktournamentType(tournament.getTournament())).size() == 0) {
            Dimension d = driver.manage().window().getSize();
            ((AndroidDriver) driver).swipe(d.getWidth() / 2, d.getHeight() / 2, d.getWidth() / 2, (d.getHeight() / 2) - 400, 0);
        }
        return this;
    }
}
