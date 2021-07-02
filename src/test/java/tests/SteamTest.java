package tests;

import helpers.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Collections;
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
//        System.out.println("nadam se logout: "+ logout.getText());
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

    @Test
    public void addAndRemoveFromCartTest() throws InterruptedException {
        SteamHomePage homePage = new SteamHomePage(driver);
        homePage.pickCategory();

        ActionRPGPage actionRPGPage = new ActionRPGPage(driver);
        actionRPGPage.chooseFromRecommended();

        GamePage gamePage = new GamePage(driver);
        gamePage.addToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.addCartItems();

        System.out.println("addedGames list size: " + gamePage.addedGames.size());
        System.out.println("cart games size: " + cartPage.cartGames.size());
    //think about assert!

        //List sizes is the same
        Assert.assertEquals("Lists aren't the same size", gamePage.addedGames.size(), cartPage.cartGames.size());
        Collections.reverse(cartPage.cartGames);
        for(int i = 0; i < cartPage.cartGames.size(); i++){
            WebElement currentCartGame = cartPage.cartGames.get(i);
            WebElement cartItemDesc = currentCartGame.findElement(By.className("cart_item_desc"));
            WebElement cartItemDescName = cartItemDesc.findElement(By.tagName("a"));
            String cartGameName = cartItemDescName.getText();

            cartGameName = ChangeStrings.removeSpecialCharactersAndEditions(cartGameName);

            Game gamePageCurrentGame = gamePage.addedGames.get(i);
            String gamePageGameName = gamePageCurrentGame.name;
            System.out.println("Game page current game name: " + gamePageGameName);
            System.out.println("Cart game name index of: " + i + "\n" + "Cart game name: " + cartGameName);

            Assert.assertEquals("GamePage game doesn't contain CartPage game name", gamePageGameName, cartGameName);


           //proveriti da li su imena ista
        }

        cartPage.removeCartItems();
        //remove all assert
        String estimatedTotalText = driver.findElement(By.id("cart_estimated_total")).getText().replace("€", "").replace("-", "").replace(",", "").trim();
        int estimatedTotal = Integer.parseInt(estimatedTotalText);

        Assert.assertEquals(0, estimatedTotal);

        Thread.sleep(3000);
    }
}
