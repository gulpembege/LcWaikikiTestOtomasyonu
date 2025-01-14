package pages;

import com.github.javafaker.Faker;
import dev.failsafe.internal.util.Assert;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
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

    @FindBy(css = "a[class='color-option active'] label[class='color-label']")
    private WebElement colorLabel;

    @FindBy(xpath = "//*[@class='current-price']")
    private WebElement price;

    @FindBy(xpath = "//*[@class='price-in-cart']")
    private WebElement discountPrice;


    public String productName;
    public String productColor;
    public String productQuantity;
    public String productPrice;



    public ProductPage chooseSize() {

        Faker faker = new Faker();
        int sizeOfList = availableSizeList.size();
        int randomSize = faker.number().numberBetween(0, sizeOfList);
        try {
            availableSizeList.get(randomSize).click();
        } catch (Exception e) {
            refresh();
            availableSizeList.get(randomSize).click();
        }
        scrollToElement(Driver.getDriver(),addToCartButton);
        return this;
    }


    public ProductPage addToCart() {

        addToCartButton.click();
        return this;
    }


    public boolean isProductAddedNotificationDisplay() {
        return cartNotification.isDisplayed();
    }


    public ProductPage navigateToCart(){
        cartButton.click();
        return this;
    }

    public ProductPage productInfos(){
        productName=productTitle.getText();
        productColor=colorLabel.getText();
        try {
            productPrice=price.getText();
        } catch (NoSuchElementException e){
            productPrice=discountPrice.getText();
        }

        productQuantity="1";
        return this;

    }

    public String getProductName() {
        return productName;
    }

    public String getProductColor() {
        return productColor;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public String getProductPrice() {
        return productPrice;
    }
}
