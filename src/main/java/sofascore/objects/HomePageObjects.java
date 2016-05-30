package sofascore.objects;

import org.openqa.selenium.By;

/**
 * Created by gfox on 27/05/2016.
 */
public class HomePageObjects {

    public static String app_package_name = "com.sofascore.results:id/";

    public static By tabGames() {
        return By.xpath("//*[@resource-id = '" + app_package_name + "psts_tab_title' and @text='Games']");
    }

    public static By lnktournamentType(String tournamentType) {
        return By.xpath("//*[@resource-id = '" + app_package_name + "team_name' and @text='" + tournamentType + "']");
    }

    public static By eventTiles() {
        return By.xpath("//*[@resource-id = '" + app_package_name + "teamLL']");
    }

    public static By dropDownListOfSports(String sport) {
        return By.xpath("//*[@resource-id = '" + app_package_name + "tvTitle' and @text='" + sport + "']");
    }
}
