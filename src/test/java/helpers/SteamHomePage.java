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

    private void clickOnCategory(){
        wdWait.until(ExpectedConditions.visibilityOf(hoverMenu));
        List<WebElement> allGenres = hoverMenu.findElements(By.className("popup_genre_expand_content"));
        WebElement rolePlaying = allGenres.get(2);
        List<WebElement> rpgList = rolePlaying.findElements(By.className("popup_menu_item"));
        WebElement actionRPG = rpgList.get(0);
//        System.out.println("action rpg: " +actionRPG.getText());
//        System.out.println("All genres: " + allGenres.size());
//        System.out.println("Third genre: " + rolePlaying.getText());
        actionRPG.click();
    }

    public void pickCategory(){
        navigateToSite();
        hoverOverCategories();
        clickOnCategory();
    }

}
