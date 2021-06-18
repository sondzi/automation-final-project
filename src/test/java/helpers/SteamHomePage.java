package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SteamHomePage extends BaseHelper{

    @FindBy(id = "global_action_menu")
    WebElement actionsMenu;

    WebDriver driver;
    public SteamHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void navigateToSite(){
        driver.get("https://store.steampowered.com/");
    }

    private void clickOnLoginButton(){
        List<WebElement> globalActionMenu = actionsMenu.findElements(By.className("global_action_link"));
//        System.out.println("Nav bar size: " + globalActionMenu.size());
//        System.out.println("Nav bar: " + globalActionMenu.get(0).getText());
//        System.out.println("Nav bar: " + globalActionMenu.get(1).getText());
        WebElement loginButton = globalActionMenu.get(0);
        loginButton.click();
    }

    public void goToSigninPage(){
        navigateToSite();
        clickOnLoginButton();
    }

    //While logged in

    public String activeUser;


}



//automation.practice@yahoo.com
// NiskiMaltezanin
// u'4qTd;8Wr.