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

    @FindBy(className = "agegate_text_container")
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
//        List<WebElement> viewPageCancel = viewPageCancelButtons.findElements(By.className("btnv6_blue_hoverfade"));
//        System.out.println("Buttons: " + viewPageCancel.size());
//        WebElement viewPageButton = viewPageCancel.get(0);
//        viewPageButton.click();
        WebElement viewPageButton = driver.findElement(By.xpath("/html/body/div[1]/div[7]/div[4]/div/div[2]/div/div[1]/div[3]/a[1]/span"));
        viewPageButton.click();
    }

    public void selectDOBAndGoToCart(){
        selectDay();
        selectMonth();
        selectYear();
        clickViewPage();
    }
}
