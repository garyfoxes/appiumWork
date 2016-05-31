package sofascore.objects;

import org.openqa.selenium.By;

import static sofascore.objects.HomePageObjects.app_package_name;

/**
 * Created by gfox on 30/05/2016.
 */
public class EventPageObjects {

    public static By txtTeamNames() {
        return By.xpath("//*[@resource-id = '" + app_package_name + "name_text']");
    }

    public static By divLogoDetails() {
        return By.xpath("//*[@resource-id = '" + app_package_name + "details_logo_row']");
    }


    public static By btnBack() {
        return By.xpath("//*[@content-desc = 'Navigate up']");
    }
}
