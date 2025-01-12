package pages;

import com.github.javafaker.Faker;
import dev.failsafe.internal.util.Assert;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.util.List;

public class ProductPage extends BasePage {


    @FindBy(xpath = "//h1[@class='product-title']")
    private WebElement productTitle;

    @FindBy(xpath = "//*[@class='option-box option-size-box option-size-box__stripped']")
    private List<WebElement> availableSizeList;

    @FindBy(xpath = "//*[@class='add-to-card']")
    private WebElement addToCartButton;

    @FindBy(xpath = "(//*[@class='drop-down-menu__wrapper'])[3]")
    private WebElement cartNotification;

    @FindBy(xpath = "(//*[@class='dropdown-label'])[3]")
    private WebElement cartButton;



    @Step("Bedeni tukenmis olmayan bir yas grubu secilir")
    public ProductPage chooseSize() {
        Faker faker = new Faker();
        int sizeOfList = availableSizeList.size();
        int randomSize = faker.number().numberBetween(0, sizeOfList);
        Driver.getDriver().navigate().refresh();
        availableSizeList.get(randomSize).click();
        return this;
    }

    @Step("Urun sepete eklenir")
    public ProductPage addToCart() {
        addToCartButton.click();
        return this;
    }

    @Step("Urunun sepete eklendigini dogrular")
    public boolean isProductAddedNotificationDisplay() {
        return cartNotification.isDisplayed();
    }

    @Step("Sepete gider")
    public ProductPage navigateToCart(){
        cartButton.click();
        return this;
    }

}
