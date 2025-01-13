package pages;

import io.qameta.allure.Step;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {


    @FindBy(xpath = "//*[@class='rd-cart-item-code']")
    private WebElement productTitle;

    @FindBy(xpath = "//*[@class='item-quantity-input ignored']")
    private WebElement quantity;

    @FindBy(xpath = "//*[@class='rd-cart-item-color']")
    private WebElement color;

    @FindBy(xpath = "(//*[@class='total-grand-box-amount'])[1]")
    private WebElement price;

    @FindBy(xpath = "//*[@class='fa fa-heart-o']")
    private WebElement addFavoriteButton;

    @FindBy(xpath = "(//*[@class='dropdown-label'])[2]")
    private WebElement favoritesMenu;



    public String productName;
    public String productColor;
    public String productQuantity;
    public String productPrice;


    public CartPage cartInfos(){

        productName=productTitle.getText();
        productQuantity=quantity.getAttribute("value");
        productColor=color.getText();
        productPrice=price.getText();

        return this;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductColor() {
        return productColor.replace("Renk: ","");
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public CartPage navigateToFavorites(){
        favoritesMenu.click();
        return this;
    }

    public CartPage addToFavorites(){

        addFavoriteButton.click();
        wait(2);
        return this;
    }
}
