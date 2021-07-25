package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class CartPage extends BaseHelper {

    WebDriver driver;
    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "cart_item_list")
    WebElement cartItems;

    @FindBy(className = "remove_ctn")
    WebElement removeAllItems;

    @FindBy(className = "recommended_grid")
    WebElement recommendedGames;

    @FindBy(className = "newmodal")
    WebElement areYouSureRemoveAll;

    public List<WebElement> cartGames;


    private void addFromRecommended(){
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("checkout_content_box")));

        List<WebElement> recommended = recommendedGames.findElements(By.tagName("a"));
        Random rand = new Random();
        int randomIndex = rand.nextInt(recommended.size());
        WebElement randomRecommended = recommended.get(randomIndex);
        randomRecommended.click();
        recommended.remove(randomIndex);


    }


    private void removeOneItem(){
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("checkout_content_box")));
        cartGames = cartItems.findElements(By.className("cart_item"));
        WebElement secondItem = cartGames.get(1);
        WebElement secondItemPriceAndRemove = secondItem.findElement(By.className("cart_item_price"));
        WebElement removeSecondButton = secondItemPriceAndRemove.findElement(By.className("remove_link"));
        removeSecondButton.click();
    }

    private void removeAllItems(){
        wdWait.until(ExpectedConditions.visibilityOf(cartItems));
        removeAllItems.click();
        wdWait.until(ExpectedConditions.visibilityOf(areYouSureRemoveAll));
        WebElement buttonsBanner = areYouSureRemoveAll.findElement(By.className("newmodal_buttons"));
        List<WebElement> yesNoButtons = buttonsBanner.findElements(By.className("btn_green_steamui"));
        WebElement yesRemoveAllButton = yesNoButtons.get(0);
        yesRemoveAllButton.click();
    }

    public void addCartItems(){
        for(int i = 0; i < 2; i++) {
            addFromRecommended();
            GamePage gamePage = new GamePage(driver);
            gamePage.addToCart();
            wdWait.until(ExpectedConditions.visibilityOf(cartItems));
            cartGames = cartItems.findElements(By.className("cart_item"));
        }
    }

    public void removeCartItems(){
//        removeOneItem();
        removeAllItems();
    }
}
