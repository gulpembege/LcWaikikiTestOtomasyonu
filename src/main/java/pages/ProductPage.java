package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;
import utils.ReusableMethods;

import java.util.List;

public class ProductPage {

    public ProductPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//h1[@class='product-title']")
    private WebElement productTitle;

    @FindBy(xpath = "//*[@class='option-box option-size-box option-size-box__stripped']")
    private List<WebElement> availableSizeList;



    public ProductPage chooseSize(){
        Faker faker = new Faker();
        int sizeOfList = availableSizeList.size();
        int randomSize = faker.number().numberBetween(0,sizeOfList);
        availableSizeList.get(randomSize).click();
        return this;
    }


}
