package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SteamSignInPage extends BaseHelper {

    @FindBy(id = "input_username")
    WebElement steamNameField;

    @FindBy(id = "input_password")
    WebElement passwordField;

    @FindBy(className = "login_btn")
    WebElement signInButton;

    WebDriver driver;
    public SteamSignInPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void enterSteamName(String steamName){
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("loginbox_content")));
        steamNameField.sendKeys(steamName);

    }

    private void enterPassword(String password){
        passwordField.sendKeys(password);
    }

    private void clickOnSignInButton(){
        signInButton.click();
    }

    public void signIntoSteam(String steamName, String password){
        enterSteamName(steamName);
        enterPassword(password);
        clickOnSignInButton();
    }
}
