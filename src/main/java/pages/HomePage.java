package pages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;
import utils.ReusableMethods;

import java.util.List;

public class HomePage {

    public HomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    Actions actions = new Actions(Driver.getDriver());

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
        ReusableMethods.wait(2);
        actions.click(loginButton).perform();
        return this;

    }

    public HomePage chooseCategory(String categoryName) { // istenilen kategoriyi secip uzerine hover eden method

        for (WebElement category : categoryList) {

            String nameOfCategory = category.getText();

            if (nameOfCategory.equalsIgnoreCase(categoryName)) {
                actions.moveToElement(category).perform();
                ReusableMethods.wait(2);
                System.out.println("secilen kategori " + nameOfCategory);
                break;

            }
        }
        return this;
        // ReusableMethods.fullpageScreenshot(Driver.getDriver(), "categories");
    }

    public HomePage chooseSubCategory(String subCategoryName) { // istenilen alt kategoriyi secip uzerine hover eden method
        subCategoryName = subCategoryName.toUpperCase();

        WebElement subCategory = Driver.getDriver().findElement(By.xpath("//span[normalize-space()='" + subCategoryName + "']"));
        actions.moveToElement(subCategory).perform();
        ReusableMethods.wait(2);

        return this;
        //  ReusableMethods.webElementSS(subCategory);

    }

    public HomePage chooseCategoryProduct(String categoryProductName) { // Acilan dropdownmenuden istenilen urun kategorisine tiklayan methodu
        ReusableMethods.wait(2);
        for (WebElement categoryProduct : categoryProductsList) {

            String nameOfCategory = categoryProduct.getText();
            if (nameOfCategory.equalsIgnoreCase(categoryProductName)) {
                categoryProduct.click();
                System.out.println("secilern urun " + nameOfCategory);
                break;
            }


        }
        return this;
    }
}



