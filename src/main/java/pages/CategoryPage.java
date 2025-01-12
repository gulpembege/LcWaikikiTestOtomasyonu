package pages;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Driver;

import java.util.List;

public class CategoryPage extends BasePage {


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





    @Step("Ilk sıradaki {0} .ürünün üzerine tıklar")
    public CategoryPage productSelect(int productNo) { // karsimiza gelen urunlerden istenilen urune tiklamaya yarayan method

        productList.get(productNo - 1).click();
        return this;

    }


    @Step("{0} filtre kategorisinden {1} secer")
    public CategoryPage setFilter(String filterCategory, String filter) { // filtre kategorisinin adini alip ona gore sayfayi oraya scroll edip , verilen filtreyi secme methodu


        for (WebElement filters : filterCategoryList) {

            try {
                String filterName = filters.getText();

                if (!filterName.equalsIgnoreCase("RENK") && filterName.equalsIgnoreCase(filterCategory)) {

                    scrollToElement(Driver.getDriver(), filters);
                    wait(1);

                    for (WebElement option : filterOptionList) {
                        String optionName = option.getText();
                        if (optionName.equalsIgnoreCase(filter)) {
                            option.click();
                            wait(1);
                            break;
                        }

                    }

                } else if (filterName.equalsIgnoreCase("RENK")) {

                    scrollToElement(Driver.getDriver(), filters);
                    wait(1);

                    for (WebElement colorOption : colorFilterOptionList) {
                        String colorOptionName = colorOption.getText();
                        if (colorOptionName.equalsIgnoreCase(filter)) {
                            colorOption.click();
                            wait(1);
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

    @Step("Urunleri {0} seklinde siralar")
    public CategoryPage sortOptions(String sortOption) {  //  urunleri istenilen sekilde siralama methodu

        wait(2);
        scrollToTop(Driver.getDriver());
        sortButton.click();

        for (WebElement option :sortDropdownMenu){
            String optionName = option.getText();
            if (optionName.equalsIgnoreCase(sortOption)){
                option.click();
                wait(1);
            }

        }



        return this;
    }
}

