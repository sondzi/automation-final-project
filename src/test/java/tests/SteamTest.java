package tests;

import helpers.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
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
        List<WebElement> dropdownOptions = popupMenu.findElements(By.className("popup_menu_item"));
        WebElement logout = dropdownOptions.get(2);
        Assert.assertTrue("Doesn't contain logout", logout.getText().contains("Logout"));

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
        homePage.pickCategory(2, 0);

        ActionRPGPage actionRPGPage = new ActionRPGPage(driver);
        actionRPGPage.chooseFromRecommended();

        GamePage gamePage = new GamePage(driver);
        gamePage.addToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.addCartItems();

        //List size is the same
        Assert.assertEquals("Lists aren't the same size", gamePage.addedGames.size(), cartPage.cartGames.size());
        Collections.reverse(cartPage.cartGames);
        double sumTotal = 0;
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

            // Are the games same
            Assert.assertEquals("Game names aren't the same", gamePageGameName, cartGameName);

            WebElement cartItemPriceElement = driver.findElements(By.className("cart_item_price")).get(i);
            List<WebElement> allPrices = cartItemPriceElement.findElements(By.tagName("div"));
            // Checking if there are two prices displayed, original and discounted, by default it's not discounted
            int index = 0;
            if(allPrices.size() > 1){
                index = 1;
            }

            WebElement discountedPrice = allPrices.get(index);
            String cartItemPriceText = discountedPrice.getText().replace("€", "").replace(",", ".");
            double cartItemPrice = Double.parseDouble(cartItemPriceText);
            System.out.println("Current cart item price: " + cartItemPrice);
            sumTotal += cartItemPrice;
            System.out.println("Sum total: " + sumTotal);
        }
        String estimatedTotalText = driver.findElement(By.id("cart_estimated_total")).getText().replace("€", "").replace(",", ".");
        double estimatedTotal = Double.parseDouble(estimatedTotalText);
        System.out.println("Double estimated total: " + estimatedTotal);

        // Totals are the same
        Assert.assertEquals(estimatedTotal, sumTotal, 0.1);

        cartPage.removeCartItems();
        wdWait.until(ExpectedConditions.textToBe(By.id("cart_estimated_total"), "0,--€"));
        String estimatedTotalTextZero = driver.findElement(By.id("cart_estimated_total")).getText().replace("€", "").replace(",", ".").replace("-", "");
        double estimatedTotalZero = Double.parseDouble(estimatedTotalTextZero);

        // Empty cart price is 0
        Assert.assertEquals(0, estimatedTotalZero, 0);

        Thread.sleep(3000);
    }

    @Test
    public void steamSearchAndFilterTest() throws InterruptedException {
        SteamHomePage homePage = new SteamHomePage(driver);
        SearchPage searchPage = new SearchPage(driver);
        String term = "dark souls";

        homePage.searchTerm(term);
        searchPage.clickOnFilter();

        int numberBeforeFilter = Integer.parseInt(searchPage.numberBeforeFilterText);
        int numberAfterFilter = Integer.parseInt(searchPage.numberAfterFilterText);
        int numberNextToFilter = Integer.parseInt(searchPage.numberNextToFilter);

        Assert.assertTrue("Number of games before filter isn't bigger", numberBeforeFilter > numberAfterFilter);
        Assert.assertEquals(numberAfterFilter, numberNextToFilter);

        //visual confirmation
        Thread.sleep(3000);
    }

    @Test
    public void steamWishlistTest() throws InterruptedException {
        SteamHomePage homePage = new SteamHomePage(driver);
        homePage.goToSigninPage();
        SteamSignInPage signInPage = new SteamSignInPage(driver);
        String steamName = "NiskiMaltezanin";
        String password = "u'4qTd;8Wr.";
        signInPage.signIntoSteam(steamName, password);

        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("home_cluster_ctn")));

        homePage.pickCategory(1, 3);

        MetroidvaniaPage metroidvaniaPage = new MetroidvaniaPage(driver);
        metroidvaniaPage.navigateToGamePage();

        GamePage gamePage = new GamePage(driver);
        gamePage.goToWishlist();

        WishlistPage wishlistPage = new WishlistPage(driver);
        wishlistPage.checkAndRemoveFromWishlist();
        Assert.assertEquals(gamePage.gameName, wishlistPage.wishlistItemName);
        int wishlistSize = Integer.parseInt(wishlistPage.wishlistItemCount);
        Assert.assertEquals(wishlistSize, wishlistPage.wishlistItems.size());

        Assert.assertEquals(wishlistPage.nothingToSeeHereText, "Oops, there's nothing to show here".toLowerCase());

        Thread.sleep(3000);
    }

}
