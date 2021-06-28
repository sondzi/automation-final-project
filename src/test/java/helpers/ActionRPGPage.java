package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ActionRPGPage extends BaseHelper {

    @FindBy(className = "contenthub_specials_ctn")
    WebElement recommendedSpecials;

    WebDriver driver;
    public ActionRPGPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void chooseFromRecommended(){
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("contenthub_specials_ctn")));
        List<WebElement> recommended = recommendedSpecials.findElements(By.className("contenthub_specials_grid_cell"));
        System.out.println("Number of recommended: " + recommended.size());

        WebElement actionRPGGame = recommended.get(10);
        actionRPGGame.click();
    }
}
