package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigReader;
import utils.Driver;
import utils.ReusableMethods;



public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(name = "emailAndPhone")
    private WebElement emailBox;

    @FindBy(xpath = "//*[@class='login-form__button login-form__button--bg-blue']")
    private WebElement continueButton;

    @FindBy(name = "password")
    private WebElement passwordBox;

    @FindBy(xpath = "//*[@class='login-form__button login-form__button--bg-blue']")
    private WebElement loginButton;



    public LoginPage loginAsMember(String email,String password){  // uye bilgileriyle giris yapma methodu

        emailBox.sendKeys(ConfigReader.getProperty(email)); //config properties'den gecerli mail adresi alinip gonderilir
        continueButton.click();
        passwordBox.sendKeys(ConfigReader.getProperty(password));  //config properties'den gecerli password alinip gonderilir
        loginButton.click();
        ReusableMethods.wait(4);
        Driver.getDriver().navigate().to(ConfigReader.getProperty("lcwUrl")); // bu asamada loadingde kaldigi icin anasayfaya yonlendirdim


        return this;
    }





}
