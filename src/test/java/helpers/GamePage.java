package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class GamePage extends BaseHelper {

    @FindBy(id = "appHubAppName")
    WebElement name;

    @FindBy(className = "discount_original_price")
    WebElement origPrice;

    @FindBy(className = "discount_final_price")
    WebElement discPrice;

    @FindBy(className = "discount_pct")
    WebElement discountPerc;

    @FindBy(className = "btn_addtocart")
    WebElement addToCartButton;

    @FindBy(className = "popular_tags")
    WebElement popularTags;

    public static List<Game> addedGames = new ArrayList<Game>();
    public String gameName;
    public String originalPrice;
    public String discountPrice;
    public String discountPercentage;
    public String tags;

    WebDriver driver;

    public GamePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addToCart() {

//        boolean hasAgeCheck = !driver.findElements(By.className("agegate_birthday_selector")).isEmpty();
        boolean hasAgeCheck = driver.findElements(By.className("agegate_birthday_selector")).size() != 0;

        if(hasAgeCheck){
            AgeCheck ageCheck = new AgeCheck(driver);
            ageCheck.selectDOBAndGoToCart();
        }
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("game_background_glow")));

//        gameName = name.getText().replace("™", "").replace("®", "").trim();
        gameName = ChangeStrings.removeSpecialCharactersAndEditions(name.getText());
        originalPrice = origPrice.getText().replace("€", "").trim();
        discountPrice = discPrice.getText().replace("€", "").trim();
        discountPercentage = discountPerc.getText().replace("%", "").replace("-", "").trim();
        tags = popularTags.getText();

        Game game = new Game();
        game.name = gameName;
        game.price = originalPrice;
        game.discountPrice = discountPrice;
        game.discountPercentage = discountPercentage;
        game.tags = tags;
        addedGames.add(game);
        Game addedGame = addedGames.get(0);
//        System.out.println("Game:" + "\n" + addedGame);

        addToCartButton.click();


    }
}
