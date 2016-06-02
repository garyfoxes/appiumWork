package sofascore.pages;

import org.openqa.selenium.WebDriver;
import sofascore.objects.EventPageObjects;

/**
 * Created by gfox on 30/05/2016.
 */
public class EventPage extends BasePage {

    public EventPage(WebDriver driver) {
        super(driver);
    }

    public HomePage selectBackButton() throws InterruptedException {
        waitForVisibilityOf(EventPageObjects.btnBack());
        driver.findElement(EventPageObjects.btnBack()).click();
        return new HomePage(driver);
    }

    public boolean isEventDetailsPresent() {
        try {
            waitForVisibilityOf(EventPageObjects.txtTeamNames());
            waitForVisibilityOf(EventPageObjects.divLogoDetails());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
