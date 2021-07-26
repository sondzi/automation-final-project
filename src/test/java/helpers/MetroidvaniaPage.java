package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.Random;

public class MetroidvaniaPage extends BaseHelper {
    @FindBy(id = "tab_select_ConcurrentUsers")
    WebElement whatsPopularTab;

    @FindBy(id = "tab_content_ConcurrentUsers")
    WebElement whatsPopularGames;

    WebDriver driver;
    public MetroidvaniaPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void clickOnWhatsPopular(){
        wdWait.until(ExpectedConditions.textToBe(By.className("pageheader"),"Browsing Metroidvania"));
        whatsPopularTab.click();
    }

    private void clickOnRandomOption(){
        wdWait.until(ExpectedConditions.visibilityOf(whatsPopularGames));
        List<WebElement> popularGames = whatsPopularGames.findElements(By.tagName("a"));
        Random rand = new Random();
        int index = rand.nextInt(10);
        WebElement firstOption = popularGames.get(index);
        firstOption.click();
    }

    public void navigateToGamePage(){
        clickOnWhatsPopular();
        clickOnRandomOption();
    }
}
