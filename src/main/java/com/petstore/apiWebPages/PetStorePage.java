package com.petstore.apiWebPages;

import com.petstore.controllers.RequestHandlers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

import java.time.Duration;
import static com.petstore.Constants.*;
import static com.petstore.objectRepo.ObjectRepository.*;

public class PetStorePage {
    private static final Logger LOGGER = Logger.getLogger(RequestHandlers.class);
    public static WebDriver driver;
    By OpenAPISectionBtn = By.xpath(OPEN_API_SECTION);
    By tryItOutBtn = By.xpath(TRY_IT_OUT_BTN);
    By executeBtn = By.xpath(EXECUTE_BTN);
    By editTextBox = By.xpath(EDIT_TEXT_BOX);
    By statusCode = By.xpath(STATUS_CODE);

    public PetStorePage(WebDriver driver) {
        this.driver = driver;
    }

    public void launchUIPage(){
        LOGGER.info("Launching the UI");
        driver.get(BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

    }
    public void openAPISection(){
        LOGGER.info("Clicking the Arrow button to open the space");
        driver.findElement(OpenAPISectionBtn).click();
    }
    public void clickTryOutBtn(){
        LOGGER.info("Clicking the 'Try it Out' button to open the space");
        driver.findElement(tryItOutBtn).click();
    }
    public void enterJsonData(String jsonInput){
        LOGGER.info("Inputing the Json string");
        driver.findElement(editTextBox).clear();
        driver.findElement(editTextBox).sendKeys(jsonInput);
    }
    public void clickExecuteBtn(){
        LOGGER.info("Clicking the execute button");
        driver.findElement(executeBtn).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    public String getStatusCode(){
        LOGGER.info("Capturing the status code");
        return driver.findElement(statusCode).getText();
    }

    public void tearDown(){
        LOGGER.info("Quiting the driver");
        driver.quit();
    }
}
