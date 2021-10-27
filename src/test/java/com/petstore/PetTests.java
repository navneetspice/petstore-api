package com.petstore;

import org.apache.commons.lang3.RandomStringUtils;
import com.petstore.processors.JsonProcessor;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import static com.petstore.Constants.PET_ENDPOINT;

/**
 * Created by navneet on 27/10/2021.
 */

public class PetTests extends BaseTest{
    private static final Logger LOGGER = Logger.getLogger(PetTests.class);

    @Test (priority = 0)
    public void testAddPetUI(){
        LOGGER.info("*************** Test testAddPetUI is Started **************");
        page.launchUIPage();
        page.openAPISection();
        page.clickTryOutBtn();
        pet.setId(System.currentTimeMillis());
        LOGGER.info("Pet Id:  "+ pet.getId());
        pet.setName(RandomStringUtils.randomAlphabetic(5));
        String jsonString = "{\"id\": \"" +pet.getId()+ "\",\"category\": {\"id\": 1,\"name\": \" "+pet.getName()+" \"},\"name\": \"Simba\",\"photoUrls\": [\"\"],\"tags\": [{\"id\": 0,\"name\": \"\"}],\"status\": \"available\"}";
        page.enterJsonData(jsonString);
        page.clickExecuteBtn();
        String expectedCodeString = page.getStatusCode();
        LOGGER.info("post API status Code: "+expectedCodeString);
        page.tearDown();
        Assert.assertTrue(expectedCodeString.contains("200"));
        LOGGER.info("***************** Test testAddPetUI is Ended ******************");

    }

    @Test(priority = 1, dependsOnMethods = {"testAddPetUI"})
    public void getPetAddedByUI() throws InterruptedException {
        LOGGER.info("********** Test getPetAddedByUI is Started **********");
        LOGGER.info("Pet Id  to be retrieved:  "+ pet.getId());
        RestAssured.basePath = PET_ENDPOINT+"/"+pet.getId();
        LOGGER.info("&&&&&&& "+ RestAssured.basePath);
        response =  requestHandlers.getCall();
        LOGGER.info("get API status Code first iteration: "+response.getStatusCode());
        int counter=0;
        while (response.getStatusCode() ==404){
            response =  requestHandlers.getCall();
            LOGGER.info("get API status Code "+counter+" iteration: "+response.getStatusCode());
            counter++;
            if (counter==5) break;
        }
        Assert.assertTrue(response.getStatusCode()==200);
        LOGGER.info("***************** Test getPetAddedByUI is Ended ***************");
    }

    @Test(priority = 2, dependsOnMethods = {"getPetAddedByUI"})
    public void deletePetAddedByUI() throws InterruptedException {
        LOGGER.info("************ Test deletePetAddedByUI is Started *****************");
        LOGGER.info("Pet Id  to be deleted:  "+ pet.getId());
        RestAssured.basePath = PET_ENDPOINT+"/"+pet.getId();
        LOGGER.info("&&&&&&& "+ RestAssured.basePath);
        response =  requestHandlers.deleteCall();
        LOGGER.info("delete API status Code First Iteration: "+response.getStatusCode());
        int counter=0;
        while (response.getStatusCode() ==404){
            response =  requestHandlers.deleteCall();
            LOGGER.info("delete API status Code "+counter+" iteration: "+response.getStatusCode());
            counter++;
            if (counter==5) break;
        }
        Assert.assertTrue(response.getStatusCode()==200);
        LOGGER.info("*********** Test deletePetAddedByUI is Ended ****************");
    }


    @Test (priority = 3)
    public void addNewPetFromJsonFile() throws FileNotFoundException {
        LOGGER.info("************ Test addNewPetFromJsonFile is Started ***********");
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\petadditionByJson.json");
        response =  requestHandlers.postCall(fileInputStream);
        LOGGER.info("Get API status Code: "+response.getStatusCode());
        Assert.assertTrue(response.getStatusCode() ==200);
        LOGGER.info("************* Test addNewPetFromJsonFile is Ended ******************");
    }


    @Test(priority = 4, dependsOnMethods = { "addNewPetFromJsonFile" })
    public void deletePetTest() {
        LOGGER.info("Test deletePetTest is Started");
        RestAssured.basePath = PET_ENDPOINT+"/2307198026062023";
        response =  requestHandlers.deleteCall();
        LOGGER.info("deletePetTest API status Code: "+response.getStatusCode());
        int counter=0;
        while (response.getStatusCode() ==404){
            response =  requestHandlers.deleteCall();
            LOGGER.info("delete API status Code "+counter+" iteration: "+response.getStatusCode());
            counter++;
            if (counter==5) break;
        }
        Assert.assertTrue(response.getStatusCode()==200);
        LOGGER.info("*********** Test deletePetTest is Ended **********");
    }

    @Test(priority = 5, dependsOnMethods = {"deletePetAddedByUI"})
    public void testToBeFailed() {
        LOGGER.info("******** Test testToBeFailed is Started *************");
        /*
         *  the targeted record is already deleted, and the current tests is dependent on the same
         * hence this test  would always fail
         * */
        RestAssured.basePath = PET_ENDPOINT+"/"+pet.getId();
        response =  requestHandlers.deleteCall();
        Assert.assertTrue(response.getStatusCode()==200, "This Test is expected to be fail");
        LOGGER.info("Test testToBeFailed is Ended");
    }


//
//    @Test
//    public void addNewPet() {
//        LOGGER.info("Test AddNewPet is started");
//        pet.setId(System.currentTimeMillis());
//        LOGGER.info("&&&&&&& "+ pet.getId());
//        pet.setName(RandomStringUtils.randomAlphabetic(5));
//        response =  requestHandlers.postCall("{\"id\": \"" +pet.getId()+ "\",\"category\": {\"id\": 1,\"name\": \" "+pet.getName()+" \"},\"name\": \"NeeruKadog\",\"photoUrls\": [\"\"],\"tags\": [{\"id\": 0,\"name\": \"\"}],\"status\": \"available\"}");
//        Assert.assertTrue(response.getStatusCode() ==200);
//        LOGGER.info("Test AddNewPet is Ended");
//    }
//
//
//    @Test
//    public void addNewPetFromJson() throws FileNotFoundException {
//        LOGGER.info("Test addNewPetFromJson is started");
//        pet.setId(System.currentTimeMillis());
//        LOGGER.info("&&&&&&& "+ pet.getId());
//        String jsonFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\petaddition.json";
//        String jsonBody = new JsonProcessor().addIDPropertyToJson(jsonFilePath,"id",pet.getId());
//        response =  requestHandlers.postCall(jsonBody);
//        Assert.assertTrue(response.getStatusCode() ==200);
//        LOGGER.info("Test addNewPetFromJson is Ended");
//    }

}
