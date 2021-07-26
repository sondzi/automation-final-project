package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class AgeCheck extends BaseHelper {
    @FindBy(className = "btns")
    WebElement viewPageCancelButtons;

    WebDriver driver;
    public AgeCheck(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void selectDay(){
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("app_agegate")));
        Select drpDay = new Select(driver.findElement(By.id("ageDay")));
        drpDay.selectByValue("27");
    }

    private void selectMonth(){
        Select drpMonth = new Select(driver.findElement(By.id("ageMonth")));
        drpMonth.selectByValue("December");
    }

    private void selectYear(){
        Select drpYear = new Select(driver.findElement(By.id("ageYear")));
        drpYear.selectByValue("1994");
    }

    private void clickViewPage(){
        List<WebElement> viewPageCancel = viewPageCancelButtons.findElements(By.tagName("a"));
        WebElement viewPageButton = viewPageCancel.get(0);
        viewPageButton.click();
    }

    public void selectDOBAndGoToCart(){
        selectDay();
        selectMonth();
        selectYear();
        clickViewPage();
    }
}
