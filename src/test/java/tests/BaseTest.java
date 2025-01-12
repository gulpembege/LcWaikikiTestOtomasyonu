package tests;

import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.*;
import utils.ConfigReader;
import utils.Driver;


public class BaseTest {

    // testimizi calistirmadan once ve calistirdiktan sonra calisacak methodlari hazirladigimiz class


    @BeforeTest
    public void setUp(){
        Driver.getDriver().get(ConfigReader.getProperty("lcwUrl"));
    }

    @AfterTest
    public void tearDown(){
        Driver.quitDriver();
    }

}
