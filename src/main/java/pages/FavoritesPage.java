package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Driver;

public class FavoritesPage extends BasePage {

    @FindBy(xpath = "//*[@class='product-card__title']")
    private WebElement productTitle;

    public String productName;


    public FavoritesPage favoritesInfo(){
        scrollToElement(Driver.getDriver(),productTitle);
        productName=productTitle.getText();
        return this;
    }

    public String getProductName() {
        return productName;
    }
}
