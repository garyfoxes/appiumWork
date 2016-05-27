package sofascore.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    By tabGames = By.xpath("//*[@resource-id = '" + app_package_name + "psts_tab_title' and @text='Games']");
    By lnkTournamentType = By.xpath("//*[@resource-id = '" + app_package_name + "team_name' and @text='International']");
    By listOfSports = By.xpath("//*[@resource-id = '" + app_package_name + "tvTitle' and @text='Football']");
    By eventTile = By.xpath("//*[@resource-id = '" + app_package_name + "teamLL']");
    By lnkLastTournamentType = By.xpath("//*[@resource-id = '" + app_package_name + "team_name' and @text='Zambia']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage selectTournament() {
        waitForVisibilityOf(lnkTournamentType);
        driver.findElement(lnkTournamentType).click();
        return this;
    }

    public HomePage selectSportFromDropdown() {
        waitForVisibilityOf(listOfSports);
        driver.findElement(listOfSports).click();
        return this;
    }

    public boolean isEventDisplayedForTournament() {
        return driver.findElements(eventTile).size() != 0;
    }

    public HomePage scrollDownToLastTournament() {
        while (driver.findElements(lnkLastTournamentType).size() == 0) {
            Dimension d = driver.manage().window().getSize();
            ((AndroidDriver) driver).swipe(d.getWidth() / 2, d.getHeight() / 2, d.getWidth() / 2, (d.getHeight() / 2) - 400, 0);
        }
        return this;
    }
}
