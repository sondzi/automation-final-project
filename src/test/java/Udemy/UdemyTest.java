package Udemy;

import Udemy.UdemyHomePage;
import org.junit.Test;
import tests.BaseTest;

public class UdemyTest extends BaseTest {

    @Test
    public void udemySearchTest() throws InterruptedException {
        String term = "game dev";

        UdemyHomePage homePage = new UdemyHomePage(driver);
        homePage.searchViaSearchField(term);

        //visual confirmation
        Thread.sleep(3000);
    }

    @Test
    public void udemyHoverTest() throws InterruptedException {
        UdemyHomePage homePage = new UdemyHomePage(driver);
        homePage.searchViaHover();

        //visual confirmation
        Thread.sleep(3000);
    }
}
