package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class SearchPage extends BaseHelper {
    WebDriver driver;
    public SearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "search_results_filtered_warning_persistent")
    WebElement message;

    @FindBy(id = "TagFilter_Container")
    WebElement allTags;

    @FindBy(className = "see_all_expander")
    WebElement seeAll;

    public String numberBeforeFilterText;
    public String numberNextToFilter;
    public String numberAfterFilterText;

    public void clickOnFilter(){
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("search_results_filtered_warning_persistent")));

        String messageText = message.getText();
        String[] partsBeforeFilter = messageText.split(" ");
        String numberBeforeText = partsBeforeFilter[0];
        numberBeforeFilterText = numberBeforeText;

        seeAll.click();
        wdWait.until(ExpectedConditions.visibilityOf(allTags));

        List<WebElement> allTagsList = allTags.findElements(By.className("tab_filter_control_row"));
        WebElement difficultTag = allTagsList.get(8);
        String[] difficultAndNumber = difficultTag.getText().split(" ");
        String numberNextToFilterDifficult = difficultAndNumber[1];
        numberNextToFilter = numberNextToFilterDifficult;

        WebElement checkboxDifficult = difficultTag.findElement(By.className("tab_filter_control_checkbox"));
        checkboxDifficult.click();

        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.id("search_results_filtered_warning_persistent"), messageText));
        WebElement newTextElement = driver.findElement(By.id("search_results_filtered_warning_persistent"));
        String newText = newTextElement.getText();
        String[] partsAfterFilter = newText.split(" ");
        String numberAfterText = partsAfterFilter[0];
        numberAfterFilterText = numberAfterText;
    }
}
