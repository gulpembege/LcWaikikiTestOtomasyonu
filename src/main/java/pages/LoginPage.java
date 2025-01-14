package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigReader;
import utils.Driver;


public class LoginPage extends BasePage{


    @FindBy(name = "emailAndPhone")
    private WebElement emailBox;

    @FindBy(xpath = "//*[@class='login-form__button login-form__button--bg-blue']")
    private WebElement continueButton;

    @FindBy(name = "password")
    private WebElement passwordBox;

    @FindBy(xpath = "//*[@class='login-form__button login-form__button--bg-blue']")
    private WebElement loginButton;




    public LoginPage loginAsMember(String email,String password){

        emailBox.sendKeys(ConfigReader.getProperty(email));
        continueButton.click();
        passwordBox.sendKeys(ConfigReader.getProperty(password));
        loginButton.click();
        wait(3);
        Driver.getDriver().navigate().to(ConfigReader.getProperty("lcwUrl")); // bu asamada loadingde kaldigi icin anasayfaya yonlendirdim


        return this;
    }





}
