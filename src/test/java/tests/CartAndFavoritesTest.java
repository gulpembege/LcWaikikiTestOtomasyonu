package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.HomePage;
import pages.LoginPage;
import base.BaseTest;
import utils.ConfigReader;
import utils.Driver;
import utils.ReusableMethods;


public class CartAndFavoritesTest extends BaseTest {

    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    CategoryPage categoryPage = new CategoryPage();



    @BeforeClass
    public void login(){

        homePage.navigateLoginPage(); // homepagedeki method ile login page e yonlendirir
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), ConfigReader.getProperty("expectedLoginUrl"));  // login sayfasinin acildiigni dogrular
        loginPage.loginAsMember("validEmail","validPassword");   // gecerli uye bilgileriyle uye girisi yapar
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(),ConfigReader.getProperty("lcwUrl"));  // anasayfanin acildigini dogrular

    }

    @Test
    public void verifyCartAndFavorites(){

        homePage.chooseCategory("ÇOCUK & BEBEK").                // ÇOCUK & BEBEK kategorisine hover yapar
                 chooseSubCategory("Kız çocuk").              // alt kategorilerden Kız çocuk(6-14 YAŞ) secer
                 chooseCategoryProduct("Mont ve Kaban");   // acilan kategorilerden "Mont ve Kaban" secer
        categoryPage.setFilter("Beden","5-6 Yaş").
                     setFilter("Beden","6 Yaş").           // beden filtresini "5-6" "6" ve "6-7" ve renk filtresini "BEJ" olarak secer
                     setFilter("Beden","6-7 Yaş").
                     setFilter("Renk","BEJ").
                     sortOptions();                                         // urunleri "En cok satanlar" seklinde siralar



    }



}
