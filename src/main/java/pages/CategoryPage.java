package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;
import utils.ReusableMethods;

import java.time.Duration;
import java.util.List;

public class CategoryPage {

    public CategoryPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//div[@class='product-grid']//div[@class='product-card product-card--one-of-4']")
    private List<WebElement> productList;

    @FindBy(xpath = "//*[@class='collapsible-filter-container__header']")
    private List<WebElement> filterCategoryList;

    @FindBy(xpath = "//*[@class='filter-option__text']")
    private List<WebElement> filterOptionList;

    @FindBy(xpath = "//*[@class='color-filter-option__text']")
    private List<WebElement> colorFilterOptionList;




    public CategoryPage productSelect(int productNo) { // karsimiza gelen urunlerden istenilen urune tiklamaya yarayan method

        productList.get(productNo - 1);
        return this;
    }


    public CategoryPage setFilter(String filterCategory, String filter) { // filtre kategorisinin adini alip ona gore sayfayi oraya scroll edip , verilen filtreyi secme methodu


        for (WebElement filters : filterCategoryList) {

            String filterName = filters.getText();

            if (!filterName.equalsIgnoreCase("RENK") && filterName.equalsIgnoreCase(filterCategory)) {

                ReusableMethods.scrollToElement(Driver.getDriver(), filters);
                ReusableMethods.wait(1);

                for (WebElement option : filterOptionList) {
                    String optionName = option.getText();
                    if (optionName.equalsIgnoreCase(filter)) {
                        option.click();
                        ReusableMethods.wait(1);
                        break;
                    }

                }

             filterName = filters.getText();

            } else if (filterName.equalsIgnoreCase("RENK")){

                ReusableMethods.scrollToElement(Driver.getDriver(), filters);
                ReusableMethods.wait(1);

                for (WebElement colorOption : colorFilterOptionList) {
                    String colorOptionName = colorOption.getText();
                    if (colorOptionName.equalsIgnoreCase(filter)) {
                        colorOption.click();
                        ReusableMethods.wait(1);
                        break;
                    }

                }

            }

        }
        return this;
    }

    public CategoryPage sortOptions(){  //  urunleri istenilen sekilde siralama methodu

        ReusableMethods.wait(2);
        ReusableMethods.scrollToTop(Driver.getDriver());
        ReusableMethods.wait(10);



        return this;
    }
}
