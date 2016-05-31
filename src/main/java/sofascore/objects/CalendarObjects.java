package sofascore.objects;

import org.openqa.selenium.By;

import static sofascore.objects.HomePageObjects.app_package_name;

/**
 * Created by gfox on 30/05/2016.
 */
public class CalendarObjects {

    public static By imgCalendar() {
        return By.xpath("//*[@resource-id = '" + app_package_name + "calendar_icon']");
    }

    public static By divCalendar() {
        return By.xpath("//*[@resource-id = '" + app_package_name + "calendar']");
    }
}
