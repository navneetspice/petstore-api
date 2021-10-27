package com.petstore.webDriverFactory;

import com.petstore.controllers.RequestHandlers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.log4testng.Logger;
/**
 * Created by navneet on 27/10/2021.
 */
public class WebDriverFactory {
    private static final Logger LOGGER = Logger.getLogger(WebDriverFactory.class);
    private WebDriver driver;

    public WebDriver getWebDriver(String browserType){

        /* this is a block to intialize the browser
        * add the browserType for other browser in if else block
        * */
        if(browserType.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\main\\resources\\seleinumDrivers\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            LOGGER.info("Launched the Chrome Browser");
        }
        return driver;
    }

}
