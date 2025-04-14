package com.apiautomation.test;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import com.apiautomation.pojo.LoginRequest;
import com.apiautomation.pojo.SignUp;
import endPointUrls.EndPointUrls;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import com.apiautomation.baseTest.Base;
import static org.hamcrest.Matchers.equalTo;
@Epic("RestAssured SignUp and Login")
@Feature("SignUp and Login API")
public class SignupRequest extends Base{
	@Description("Test to create a new user via API")
	@Test(priority = 1)
	public void signUpSuccess() {
		
	    SignUp newUser = new SignUp();
	    System.out.println("User should be able to signup successfully");
	    System.out.println("======================================================================================");
	    newUser.setId((int) System.currentTimeMillis());
	    newUser.setEmail("test" + System.currentTimeMillis() + "@gmail.com");
	    newUser.setPassword("SecurePass123!");

	        given()
	            .contentType(ContentType.JSON)
	            .body(newUser)
	            .log().all()
	        .when()
	            .post(EndPointUrls.SIGNUP) 
	        .then()
	            .log().all()
	            .statusCode(200)
	            .extract().response();
	        
	        System.out.println("User is created Successfully!");
	        System.out.println("================================================================");
	}
	 @Test(priority=2)
	 @Description("Login User via API")
	 public void loginSuccess()
	 {
		 LoginRequest login=new LoginRequest();
		 System.out.println("User should be able to login successfully");
		    System.out.println("======================================================================================");
		   
		 login.setEmail(EndPointUrls.USERNAME);
		 login.setPassword(EndPointUrls.PASSWORD);
		 authToken = given()
		            .accept("application/json")
		            .contentType("application/json")
		            .body(login)
		 .when()
		     .post(EndPointUrls.LOGIN)
		 .then()
		     .statusCode(200)
		     .log().all()
	         .extract()
             .path("access_token"); 
	        Base.setAuthToken(authToken);
	        System.out.println("User is able to login successfully");
		    System.out.println("======================================================================================");
		   
	    }

	    @Test(priority=3)
	    @Description("Login user with invalid credentials via API")
	    public void loginFailure()
	    {
	    	 LoginRequest login=new LoginRequest();
	    	 System.out.println("While entering the invalid credential, an error should be thrown");
	 	    System.out.println("======================================================================================");
	 	   
			 login.setEmail(EndPointUrls.LOGIN);
			 login.setPassword(EndPointUrls.PASSWORD);
			 given()
			      .accept("application/json")
			      .contentType("application/json")
			      .body(login)
			 .when()
			     .post(EndPointUrls.LOGIN)
			  .then()
			  .statusCode(400) 
		        .body("detail", equalTo("Incorrect email or password")) 
		        .log().all();
			   System.out.println("An error message is display, Incorrect email or password");
			    System.out.println("======================================================================================");
			   
	    }
		
	    @Test(priority=4)
	    @Description("Test to create a new user with existing details via API")
	    public void signupFailure()
	    {
	    	SignUp newUser=new SignUp();
	    	 System.out.println("User should be able to signup successfully, if enter unique email and password");
	 	    System.out.println("======================================================================================");
	 	   
			newUser.setId(10);
			newUser.setEmail(EndPointUrls.USERNAME);
			newUser.setPassword(EndPointUrls.PASSWORD);
			given()
			 .accept("application/json")
			 .contentType("application/json")
			 .body(newUser)
			
			 .when()
			 .post(EndPointUrls.SIGNUP) 
			.then()
			 .statusCode(400)
			 .body("detail", equalTo("Email already registered"))
			 .log().all();
			
			   System.out.println("User is received an error message, Email already registered");
			    System.out.println("======================================================================================");
			   
	    	
	    }
	 }
    
	 
		





