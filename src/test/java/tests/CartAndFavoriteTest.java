package tests;


import org.testng.Assert;

import org.testng.annotations.Test;
import pages.*;
import utils.ConfigReader;
import utils.Driver;


public class CartAndFavoriteTest extends BaseTest {

    HomePage homePage= new HomePage();
    LoginPage loginPage= new LoginPage();
    CategoryPage categoryPage = new CategoryPage();
    ProductPage productPage= new ProductPage();
    CartPage cartPage= new CartPage();
    FavoritesPage favoritesPage = new FavoritesPage();

    @Test
    public void verifySuccessLogin() {

        homePage.navigateLoginPage(); // login page'e yonlendirir
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), ConfigReader.getProperty("expectedLoginUrl"), "Login sayfasi acilamadi!");  // login sayfasinin acildigni dogrular
        loginPage.loginAsMember("validEmail", "validPassword");   // gecerli uye bilgileriyle uye girisi yapar
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), ConfigReader.getProperty("lcwUrl"), "Anasayfa acilamadi!");  // anasayfanin acildigini dogrular

    }

    @Test(dependsOnMethods = {"verifySuccessLogin"})
    public void verifyProductAddedToCart() {

        homePage.chooseCategory("ÇOCUK & BEBEK").                // ÇOCUK & BEBEK kategorisinin ustune gelir
                chooseSubCategory("Kız çocuk").               // alt kategorilerden Kız çocuk(6-14 YAŞ) kategorisinin ustune gelir
                chooseCategoryProduct("Mont ve Kaban");    // acilan kategorilerden "Mont ve Kaban" secer
        categoryPage.setFilter("Beden", "5-6 Yaş").
                setFilter("Beden", "6 Yaş").                // beden filtresini "5-6" "6" ve "6-7" ve renk filtresini "bej" olarak secer
                setFilter("Beden", "6-7 Yaş").
                setFilter("Renk", "bej").
                sortOptions("En çok satanlar").                             // urunleri "En çok satanlar" seklinde siralar
                productSelect(4);                                  // ilk sıradaki 4.ürünün üzerine tıklar
        productPage.chooseSize().                                           // bedeni tükenmiş olmayan bir yaş grubu secer
                    addToCart();                                            // sepete ekler

        Assert.assertTrue(productPage.isProductAddedNotificationDisplay(), "Urun sepete eklenemedi!"); // urunun sepete ekledngini dogrular

    }


    @Test(dependsOnMethods = {"verifyProductAddedToCart"})
    public void verifyCartDetails() {
        productPage.productInfos().                             // sepete gitmeden once urun bilgilerini kaydeder
                     navigateToCart();                          // sepetim ekranina gider
        cartPage.cartInfos();                                   // sepet bilgilerini kaydeder

        Assert.assertTrue(productPage.getProductName().contains(cartPage.getProductName()));  //sepetim ekraninda secilen urunun "adi","rengi","adet" alanlarini dogrular
        Assert.assertTrue(productPage.getProductColor().contains(cartPage.getProductColor()));
        Assert.assertTrue(productPage.getProductQuantity().contains(cartPage.getProductQuantity()));
        Assert.assertTrue(productPage.getProductPrice().contains(cartPage.getProductPrice())); // urunun tutari ile "odeme adimina gec" alaninda yazan tutarin eslestigini dogrular

    }


    @Test(dependsOnMethods = "verifyCartDetails")
    public void verifyFavoriteDetails(){

        cartPage.addToFavorites().                         // urunu favorilere ekler
                 navigateToFavorites();                    // favorilerim sayfasina gider
        favoritesPage.favoritesInfo();                     // favorilerdeki urun bilgisini kaydeder

        Assert.assertTrue(productPage.getProductName().contains(favoritesPage.getProductName())); // favorilere eklenen urunun listelendigini dogrular

    }


}
