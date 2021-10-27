package com.petstore.controllers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.log4testng.Logger;
import java.io.FileInputStream;
import static io.restassured.RestAssured.given;

/**
 * Created by navneet on 27/10/2021.
 */

public class RequestHandlers {
    private static final Logger LOGGER = Logger.getLogger(RequestHandlers.class);

    public Response postCall(String bodyInput){
        LOGGER.info("Executing the Post Call");
        Response response = null;
        try {
            response = given().contentType(ContentType.JSON).body(bodyInput).post();

        }catch (Exception e){
            LOGGER.error(" some error" +e);
        }
        return response;
    }

    public Response postCall(FileInputStream file){
        LOGGER.info("Executing the Post Call");
        Response response = null;
        try {
            response = given().contentType(ContentType.JSON).body(file).post();

        }catch (Exception e){
            LOGGER.error(" some error" +e);
        }
        return response;
    }

    public Response deleteCall(){
        LOGGER.info("Executing the Delete Call");
        Response response = null;
        try {
            response = given().contentType(ContentType.JSON).delete();

        }catch (Exception e){
            LOGGER.error(" some error" +e);
        }
        return response;
    }

    public Response getCall(){
        LOGGER.info("Executing the get Call");
        Response response = null;
        try {
            response = given().contentType(ContentType.JSON).get();

        }catch (Exception e){
            LOGGER.error(" some error" +e);
        }
        return response;
    }
}
