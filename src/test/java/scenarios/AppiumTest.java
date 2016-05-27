package scenarios;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sofascore.AndroidSetup;
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

    @Test
    public void test() throws InterruptedException {
        HomePage page = new HomePage(driver).selectSportFromDropdown().selectTournament();
        Assert.assertTrue(page.isEventDisplayedForTournament(), "Event Is Not Displayed For Tournament");
        page.scrollDownToLastTournament();
    }
}