package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.util.List;

public class HomePage extends BasePage {


    @FindBy(xpath = "(//span[@class='dropdown-label'])[1]")
    private WebElement loginDropdown;

    @FindBy(xpath = "//a[@class='cart-action__btn cart-action__btn--bg-blue']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[@class='menu-header-item__title']")
    private List<WebElement> categoryList;

    @FindBy(css = "ul.zones-lists-items li.zone-item a.zone-item__anchor")
    private List<WebElement> categoryProductsList;

    @FindBy(id="cookieseal-banner-accept")
    private WebElement cookieAccept;




    public HomePage navigateLoginPage() { // login sayfasina yonlendirme methodu


        if (cookieAccept.isDisplayed()){ //  cookieler kabul edilir
            cookieAccept.click();
        }

        actions.moveToElement(loginDropdown).perform();
        wait(2);
        actions.click(loginButton).perform();
        return this;

    }


    public HomePage chooseCategory(String categoryName) { // istenilen kategoriyi secip uzerine hover eden method


        for (WebElement category : categoryList) {

            String nameOfCategory = category.getText();

            if (nameOfCategory.equalsIgnoreCase(categoryName)) {
                actions.moveToElement(category).perform();
                wait(2);
                break;

            }
        }
        return this;

    }




    public HomePage chooseSubCategory(String subCategoryName) {
        subCategoryName = subCategoryName.toUpperCase();

        WebElement subCategory = Driver.getDriver().findElement(By.xpath("//span[normalize-space()='" + subCategoryName + "']"));
        actions.moveToElement(subCategory).perform();
        wait(2);

        return this;


    }


    public HomePage chooseCategoryProduct(String categoryProductName) {
        wait(2);
        for (WebElement categoryProduct : categoryProductsList) {

            String nameOfCategory = categoryProduct.getText();
            if (nameOfCategory.equalsIgnoreCase(categoryProductName)) {
                categoryProduct.click();
                break;
            }


        }
        return this;
    }
}



