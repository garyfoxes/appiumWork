package scenarios;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sofascore.AndroidSetup;
import sofascore.enums.Tournaments;
import sofascore.pages.HomePage;

public class AppiumTest extends AndroidSetup {

    @BeforeClass
    public void setUp() throws Exception {
        prepareAndroidForAppium();
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
        service.stop();
    }

    /*   @AfterMethod
       public void afterMethod(){
        driver.closeApp();
           driver.launchApp();

       }
   */
    @Test
    public void test() throws InterruptedException {
        HomePage page = new HomePage(driver).selectTournament(Tournaments.INTERNATIONAL);
        Assert.assertTrue(page.isEventDisplayedForTournament(), "Event Is Not Displayed For Tournament");
        page.scrollDownToLastTournament(Tournaments.RUSSIA).selectTournament(Tournaments.RUSSIA);
        Assert.assertTrue(page.isEventDisplayedForTournament(), "Event Is Not Displayed For Tournament");
    }

}