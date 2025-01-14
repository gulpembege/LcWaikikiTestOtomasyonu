package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class BasePage {

    public BasePage() {PageFactory.initElements(Driver.getDriver(),this);
    }

    Actions actions = new Actions(Driver.getDriver());

    public static void wait(int saniye) {

        try {
            Thread.sleep(saniye * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void refresh(){
        Driver.getDriver().navigate().refresh();
    }


    public static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public static void scrollToTop(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
    }

    public static void webElementSS(WebElement ssCekilecekElement) {

        // dinamik hale getirmek icin tarih muhru ekledim
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter tarihFormati = DateTimeFormatter.ofPattern("yy-MM-dd HH.mm");
        String tarihMuhru = ldt.format(tarihFormati);

        // tum sayfa screenshot ile istenen weblement ss arasindaki tek fark ,
        // 1.adimda tss objesi olusturmak yerine fotograf cekmek istedigimiz
        // webelementi locate etmemizdir

        // 1.adim : fotograf cekecegimiz webelementi lcoate edelim
        // 2.adim : resmi kaydedecegimiz file ' i olusturalim
        File webElementSS = new File("src/screenshots/" + tarihMuhru + ".png");
        // 3.adim : ss alip gecici dosyaya kaydedelim
        File geciciDosya = ssCekilecekElement.getScreenshotAs(OutputType.FILE);
        // 4.adim : gecici dosyayi asil dosyaya kopyalayalim
        try {
            FileUtils.copyFile(geciciDosya, webElementSS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void fullpageScreenshot(WebDriver driver, String raporIsmi) {
        // 1.adim TakeScreenshot objesi olusturalim
        TakesScreenshot tss = (TakesScreenshot) driver;

        // 2.adim cektigimiz screenshot'i kaydedecegimiz dosyayi olusturalim
        File tumSayfaScreenshot = new File("src/screenshots/" + raporIsmi + ".png");

        // 3.adim sayfa fotografini cekip gecici bir dosyaya yukleyelim
        File geciciDosya = tss.getScreenshotAs(OutputType.FILE);

        // 4.adim gecici dosyayi asil olusturdugumuz dosyaya kopyalayalim
        try {
            FileUtils.copyFile(geciciDosya, tumSayfaScreenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public static List<String> stringListesineDonustur(List<WebElement> webelementListesi) {

        List<String> istenenStringList = new ArrayList<>();

        for (WebElement eachElement : webelementListesi
        ) {
            istenenStringList.add(eachElement.getText());
        }

        return istenenStringList;
    }


}
