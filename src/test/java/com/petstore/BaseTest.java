package com.petstore;

import com.petstore.apiWebPages.PetStorePage;
import com.petstore.controllers.RequestHandlers;
import com.petstore.processors.Pet;
import com.petstore.webDriverFactory.WebDriverFactory;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.log4testng.Logger;
import static com.petstore.Constants.*;

/**
 * Created by navneet on 27/10/2021.
 */

public class BaseTest {
    private static final Logger LOGGER = Logger.getLogger(BaseTest.class);
    public static WebDriver driver;
    public PetStorePage page;
    public RequestHandlers requestHandlers;
    public Response response;
    public Pet pet;

    @BeforeTest
    public void beforeTest(){
        LOGGER.info("Before Test is Triggered");
        requestHandlers  = new RequestHandlers();
        /* BASE URL and ENDPOINT would be null if the parameters are not passed from command line*/
        if(BASE_URL==null && PET_ENDPOINT==null){
            LOGGER.info("No Arguments passed from command line. setting the default values");
            BASE_URL = "https://petstore.swagger.io";
            PET_ENDPOINT = "/v2/pet";
        }
        pet = new Pet();
        WebDriverFactory driverFactory = new WebDriverFactory();
        driver = driverFactory.getWebDriver("chrome");
        page = new PetStorePage(driver);
    }

    @BeforeMethod
    public void initRequestPayload(){
        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = PET_ENDPOINT;
    }

}
