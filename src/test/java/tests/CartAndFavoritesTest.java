package tests;


import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import utils.ConfigReader;
import utils.Driver;

@Listeners(AllureTestNg.class)


public class CartAndFavoritesTest extends BaseTest {

    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    CategoryPage categoryPage = new CategoryPage();
    ProductPage productPage = new ProductPage();
    CartPage cartPage = new CartPage();



    @Test
    public void verifySuccessLogin() {

        homePage.navigateLoginPage(); // login page'e yonlendirir
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), ConfigReader.getProperty("expectedLoginUrl"),"Login sayfasi acilamadi!");  // login sayfasinin acildigni dogrular
        loginPage.loginAsMember("validEmail", "validPassword");   // gecerli uye bilgileriyle uye girisi yapar
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), ConfigReader.getProperty("lcwUrl"),"Anasayfa acilamadi!");  // anasayfanin acildigini dogrular

    }

    @Test(dependsOnMethods = {"verifySuccessLogin"})
    public void verifyProductAddedToCart(){

        homePage.chooseCategory("ÇOCUK & BEBEK").                // ÇOCUK & BEBEK kategorisinin ustune gelir
                chooseSubCategory("Kız çocuk").               // alt kategorilerden Kız çocuk(6-14 YAŞ) kategorisinin ustune gelir
                chooseCategoryProduct("Mont ve Kaban");    // acilan kategorilerden "Mont ve Kaban" secer
        categoryPage.setFilter("Beden", "5-6 Yaş").
                setFilter("Beden", "6 Yaş").                // beden filtresini "5-6" "6" ve "6-7" ve renk filtresini "bej" olarak secer
                setFilter("Beden", "6-7 Yaş").
                setFilter("Renk", "bej").
                sortOptions("En çok satanlar").                             // urunleri "En çok satanlar" seklinde siralar
                productSelect(4);                                  // ilk sıradaki 4.ürünün üzerine tıklar
        productPage.chooseSize().addToCart();                               // bedeni tükenmiş olmayan bir yaş grubu seçilir ve sepete eklenir

        Assert.assertTrue(productPage.isProductAddedNotificationDisplay(),"Urun sepete eklenemedi!"); // urunun sepete ekledngini dogrular

    }


    @Test(dependsOnMethods = {"verifyProductAddedToCart"})
    public void verifyCartDetails() {

       productPage.navigateToCart();                                          // sepetim ekranina gider












    }


}
