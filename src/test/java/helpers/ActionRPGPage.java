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

        // wanted to do random, but have to make exceptions for free games, demos etc.
//        Random rand = new Random();
//        int randomIndex = rand.nextInt(recommended.size());
//        System.out.println("Random index for recommended specials: " + randomIndex);

        WebElement actionRPGGame = recommended.get(10);
        actionRPGGame.click();
    }
}
