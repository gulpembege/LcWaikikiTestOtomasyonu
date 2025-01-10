package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
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

    @FindBy(xpath = "//*[@class='dropdown-button__button']")
    private WebElement sortButton;

    @FindBy(xpath = "//*[@class='dropdown-button__option']")
    private List<WebElement> sortDropdownMenu;




    public CategoryPage productSelect(int productNo) { // karsimiza gelen urunlerden istenilen urune tiklamaya yarayan method

        productList.get(productNo - 1).click();
        return this;
    }


    public CategoryPage setFilter(String filterCategory, String filter) { // filtre kategorisinin adini alip ona gore sayfayi oraya scroll edip , verilen filtreyi secme methodu


        for (WebElement filters : filterCategoryList) {

            try {
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

                } else if (filterName.equalsIgnoreCase("RENK")) {

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

            } catch (
                    StaleElementReferenceException e) {  // stale element hatasi aldigim icin renk filtreleri categorisini tekrar tanimladim

                filterCategoryList = Driver.getDriver().findElements(By.xpath("//*[@class='collapsible-filter-container__header']"));

            }
        }
        return this;
    }

    public CategoryPage sortOptions(String sortOption) {  //  urunleri istenilen sekilde siralama methodu

        ReusableMethods.wait(2);
        ReusableMethods.scrollToTop(Driver.getDriver());
        sortButton.click();

        for (WebElement option :sortDropdownMenu){
            String optionName = option.getText();
            if (optionName.equalsIgnoreCase(sortOption)){
                option.click();
                ReusableMethods.wait(1);
            }

        }



        return this;
    }
}

