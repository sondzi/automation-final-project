package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class WishlistPage extends BaseHelper {
    @FindBy(id = "wishlist_ctn")
    WebElement wishlistItem;

    @FindBy(className = "wishlist_row")
    WebElement addedItem;

    @FindBy(id = "wishlist_item_count_value")
    WebElement wishlistButton;

    @FindBy(className = "delete")
    WebElement removeButton;

    @FindBy(className = "newmodal")
    WebElement removePopUp;

    @FindBy(id = "nothing_to_see_here")
    WebElement nothingToSee;

    public String wishlistItemName;
    public String wishlistItemCount;
    public List<WebElement> wishlistItems;
    public String nothingToSeeHereText;

    WebDriver driver;
    public WishlistPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void checkFromWishlist(){
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("wishlist_ctn")));
        wdWait.until(ExpectedConditions.visibilityOf(addedItem));

        wishlistItems = wishlistItem.findElements(By.className("wishlist_row"));
        WebElement item = wishlistItems.get(0);
        WebElement itemNameField = item.findElement(By.className("content"));
        WebElement itemNameAndDetails = itemNameField.findElement(By.tagName("a"));
        String itemName = itemNameAndDetails.getText();
        wishlistItemName = ChangeStrings.removeSpecialCharactersAndEditions(itemName);
        wishlistItemCount = wishlistButton.getText();
    }

    private void removeFromWishList(){
        removeButton.click();
        wdWait.until(ExpectedConditions.visibilityOf(removePopUp));

        WebElement OKButton = removePopUp.findElement(By.className("btn_green_steamui"));
        OKButton.click();

        wdWait.until(ExpectedConditions.visibilityOf(nothingToSee));
        WebElement oopsNothingHere = nothingToSee.findElement(By.tagName("h2"));
        nothingToSeeHereText = oopsNothingHere.getText().toLowerCase();
    }

    public void checkAndRemoveFromWishlist(){
        checkFromWishlist();
        removeFromWishList();
    }
}
