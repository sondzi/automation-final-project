package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class SteamHomePage extends BaseHelper{
    @FindBy(id = "global_action_menu")
    WebElement actionsMenu;

    WebDriver driver;
    public SteamHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Go to signIn page
    private void navigateToSite(){
        driver.get("https://store.steampowered.com/");
    }

    private void clickOnLoginButton(){
        List<WebElement> globalActionMenu = actionsMenu.findElements(By.className("global_action_link"));
        WebElement loginButton = globalActionMenu.get(0);
        loginButton.click();
    }

    public void goToSigninPage(){
        navigateToSite();
        clickOnLoginButton();
    }

    @FindBy(id = "genre_tab")
    WebElement categories;

    @FindBy(id = "genre_flyout")
    WebElement hoverMenu;

    //go to ActionRPG page
    private void hoverOverCategories(){
        Actions hover = new Actions(driver);
        hover.moveToElement(categories).build().perform();
    }

    private void clickOnCategory(int indexCategory, int indexGenre){
        wdWait.until(ExpectedConditions.visibilityOf(hoverMenu));
        List<WebElement> allGenres = hoverMenu.findElements(By.className("popup_genre_expand_content"));
        WebElement rolePlaying = allGenres.get(indexCategory);
        List<WebElement> rpgList = rolePlaying.findElements(By.className("popup_menu_item"));
        WebElement actionRPG = rpgList.get(indexGenre);
        actionRPG.click();
    }

    public void pickCategory(int indexCategory, int indexGenre){
        navigateToSite();
        hoverOverCategories();
        clickOnCategory(indexCategory, indexGenre);
    }

    //search

    @FindBy(id = "store_nav_search_term")
    WebElement searchBar;

    @FindBy(id = "store_search_link")
    WebElement magnifyingGlassIcon;

    private void clickOnSearchBar(){
        searchBar.click();
    }

    private void enterSearchTerm(String term){
        searchBar.sendKeys(term);
    }

    private void clickOnMagnifyingGlassIcon(){
        js.executeScript("arguments[0].click();", magnifyingGlassIcon);
    }

    public void searchTerm(String term){
        navigateToSite();
        clickOnSearchBar();
        enterSearchTerm(term);
        clickOnMagnifyingGlassIcon();
    }

}
