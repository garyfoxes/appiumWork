package sofascore.pages;

import org.openqa.selenium.WebDriver;
import sofascore.objects.CalendarObjects;

/**
 * Created by gfox on 30/05/2016.
 */
public class CalendarComponent extends BasePage {

    public CalendarComponent(WebDriver driver) {
        super(driver);
    }

    public boolean isCalendarDisplayed() {
        return driver.findElements(CalendarObjects.imgCalendar()).size() != 0;
    }
}
