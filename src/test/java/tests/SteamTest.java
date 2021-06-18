package tests;

import helpers.SteamHomePage;
import helpers.SteamSignInPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SteamTest extends BaseTest {

    @Test
    public void steamLoginValidCredentialsTest() throws InterruptedException {

        SteamHomePage homePage = new SteamHomePage(driver);
        homePage.goToSigninPage();

        String steamName = "NiskiMaltezanin";
        String password = "u'4qTd;8Wr.";
        SteamSignInPage signInPage = new SteamSignInPage(driver);
        signInPage.signIntoSteam(steamName, password);

        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("account_pulldown")));
        WebElement accountDropdown = driver.findElement(By.id("account_pulldown"));
        System.out.println("Acc dropdown: " + accountDropdown.getText());
        Assert.assertEquals(accountDropdown.getText(), steamName);

        WebElement userAvatar = driver.findElement(By.className("user_avatar"));
        Assert.assertTrue("User avatar is not displayed", userAvatar.isDisplayed());

        accountDropdown.click();
        WebElement popupMenu = driver.findElement(By.id("account_dropdown"));
//        wdWait.until(ExpectedConditions.visibilityOf(popupMenu));
        List<WebElement> dropdownOptions = popupMenu.findElements(By.className("popup_menu_item"));
        WebElement logout = dropdownOptions.get(2);
        System.out.println("nadam se logout: "+ logout.getText());
        Assert.assertTrue("Doesn't contain logout", logout.getText().contains("Logout")); //mozda napisati jos jedan sa acc name


        // visual confirmation
        Thread.sleep(3000);
    }

    @Test
    public void steamLoginInvalidCredentialsTest() throws InterruptedException {
        SteamHomePage homePage = new SteamHomePage(driver);
        homePage.goToSigninPage();

        String steamName = "NiskiMaltezanin";
        String password = "SigurnoNijeOvo133";

        SteamSignInPage signInPage = new SteamSignInPage(driver);
        signInPage.signIntoSteam(steamName, password);

        WebElement errorMsg = driver.findElement(By.id("error_display"));
        wdWait.until(ExpectedConditions.visibilityOf(errorMsg));
        WebElement loginButton = driver.findElement(By.className("login_btn"));

        Assert.assertTrue("No error message", errorMsg.getText().contains("The account name or password that you have entered is incorrect."));
        Assert.assertTrue("No sign in button", loginButton.getText().contains("Sign In"));

        //visual confirmation
        Thread.sleep(3000);
    }
}
