package com.apiautomation.baseTest;

import org.testng.annotations.BeforeClass;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import java.util.concurrent.CopyOnWriteArrayList;


public class Base {
protected static String authToken;
public static CopyOnWriteArrayList<String> createdBookIds = new CopyOnWriteArrayList<>();

    
    public static void setAuthToken(String token) {
        authToken = token;
    }
	  @BeforeClass
	    public void setup() {
		    RestAssured.filters(new AllureRestAssured());
	        RestAssured.baseURI = "http://127.0.0.1:8000";
	        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	    }
	}


