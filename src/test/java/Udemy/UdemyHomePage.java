package Udemy;

import helpers.BaseHelper;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class UdemyHomePage extends BaseHelper {

    @FindBy(className = "udlite-text-input")
    WebElement searchField;

    @FindBy(className = "udlite-search-form-autocomplete-suggestion")
    WebElement dropdownOptions;

    WebDriver driver;
    public UdemyHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void navigateToSite(){
        driver.get("https://www.udemy.com/");
    }

    private void enterTerm(String term){
        searchField.sendKeys(term);
    }

    private void clickOnDropdownOption(){
//        wdWait.until(ExpectedConditions.attributeContains(By.className("udlite-search-form-autocomplete-suggestions"), "aria-expanded", "true"));
        wdWait.until(ExpectedConditions.visibilityOf(dropdownOptions));
//        WebElement options = dropdownOptions.findElement(By.tagName("li"));

        WebElement options = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[2]/form/ul/li"));
       List<WebElement> listOfOptions = options.findElements(By.tagName("a"));
       System.out.println("Options size: " + listOfOptions.size());

        WebElement selectedOption = listOfOptions.get(2);
        selectedOption.click();
    }

    public void searchViaSearchField(String term){
        navigateToSite();
        enterTerm(term);
        clickOnDropdownOption();
    }

    // second search

    @FindBy(className = "header--dropdown-button--1BviY")
    WebElement categoriesButton;

    @FindBy(className = "list-menu--section--BZ3j9")
    WebElement categoriesDropdown;

    @FindBy(tagName = "/html/body/div[1]/div[2]/div[3]/nav/div/div/div/div/div/div[2]/ul")
    WebElement developmentCategories;


    private void hoverOverCategories(){
        Actions hover = new Actions(driver);
        hover.moveToElement(categoriesButton).build().perform();
    }

    private void hoverOverField(){
        wdWait.until(ExpectedConditions.visibilityOf(categoriesDropdown));

        List<WebElement> allCategories = categoriesDropdown.findElements(By.tagName("li"));
        WebElement developmentCategory = allCategories.get(0);
        System.out.println("Main category: " + developmentCategory.getText());

        Actions hover = new Actions(driver);
        hover.moveToElement(developmentCategory).build().perform();
    }

    private void chooseField(){
        wdWait.until(ExpectedConditions.visibilityOf(developmentCategories));

        List<WebElement> devCategoriesList = developmentCategories.findElements(By.tagName("li"));
        WebElement gameDevelopment = devCategoriesList.get(4);
        System.out.println("Development category: " + gameDevelopment.getText());

        Actions hover = new Actions(driver);
        hover.moveToElement(gameDevelopment).build().perform();

    }

    public void searchViaHover(){
        navigateToSite();
        hoverOverCategories();
        hoverOverField();
        chooseField();
    }

}
