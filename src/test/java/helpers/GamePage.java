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

    @FindBy(className = "game_purchase_price")
    WebElement price;

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

    private void ageCheck(){
        boolean hasAgeCheck = driver.findElements(By.className("agegate_birthday_selector")).size() != 0;
        if(hasAgeCheck){
            AgeCheck ageCheck = new AgeCheck(driver);
            ageCheck.selectDOBAndGoToCart();
        }
    }

    public Game getGameInfo(){
        gameName = ChangeStrings.removeSpecialCharactersAndEditions(name.getText());

        boolean isDiscounted = driver.findElements(By.className("game_purchase_price")).size() == 0;
        if(isDiscounted){
            originalPrice = origPrice.getText().replace("€", "").trim();
            discountPrice = discPrice.getText().replace("€", "").trim();
            discountPercentage = discountPerc.getText().replace("%", "").replace("-", "").trim();
        } else {
            originalPrice = price.getText().replace("€", "").trim();
        }

        tags = popularTags.getText();

        Game game = new Game();
        game.name = gameName;
        game.price = originalPrice;
        game.discountPrice = discountPrice;
        game.discountPercentage = discountPercentage;
        game.tags = tags;

        return game;
    }

    public void addToCart() {
        ageCheck();
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("game_background_glow")));
        Game game = this.getGameInfo();
        addedGames.add(game);
        addToCartButton.click();
    }

    @FindBy(id = "add_to_wishlist_area")
    WebElement addToWishListButton;

    @FindBy(id = "wishlist_link")
    WebElement wishlistLink;

    private void addToWishlist(){
        ageCheck();
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("game_background_glow")));

        Game game = this.getGameInfo();
        addedGames.add(game);

        addToWishListButton.click();
    }

    private void clickOnWishlistLink(){
        wishlistLink.click();
    }

    public void goToWishlist(){
        addToWishlist();
        clickOnWishlistLink();
    }
}
