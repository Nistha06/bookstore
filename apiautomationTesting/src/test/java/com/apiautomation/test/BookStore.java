package com.apiautomation.test;

import com.apiautomation.baseTest.Base;
import com.apiautomation.pojo.Book;

import freemarker.log.Logger;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
@Epic("RestAssured Book ")
@Feature("CRUD and Method Chaining")



public class BookStore extends Base {
	public Logger logger;
	
    private Book bookstore = new Book();
    

    @Test(priority = 1, dataProvider = "bookData", dependsOnMethods = "com.apiautomation.test.SignupRequest.loginSuccess")
    @Description("Create a new books via API")
    public void createBook(String name, String author, int id, String publishedYear, String bookSummary)
    {
	
        bookstore.setId(id);
        bookstore.setName(name);
        bookstore.setAuthor(author);
        bookstore.setPublishedYear(publishedYear); 
        bookstore.setBookSummary(bookSummary);      
        
        Response response = given()
            .header("Authorization", "Bearer " + Base.authToken)
            .contentType("application/json")
            .body(bookstore)
        .when()
            .post("/books/")
        .then()
            .statusCode(200)
            .body("id", equalTo(id))
            .body("name", equalTo(name))
            .body("author", equalTo(author))
            .log().all()
            .extract().response();


        String createdId = response.jsonPath().getString("id");
        System.out.println("Created Book ID: " + createdId);
        Base.createdBookIds.clear();
        Base.createdBookIds.add(createdId);
        System.out.println("Book created successfully with ID: "+createdId);
    }

    @DataProvider(name = "bookData")
    public Object[][] bookDataProvider() {
        return new Object[][] {
            {"Nistha123", "RDSharma", (int) (System.currentTimeMillis() % 1000), "1990", "Mathematics Learning"},
         };
    }
    @Test(priority=2, dependsOnMethods = "createBook")
    @Description("Get all book via API")
    public void getAllBooks()
    {
    	 given()
    	            .header("Authorization", "Bearer " + Base.authToken)
    	            .contentType("application/json")
    	            .body(bookstore)
    	        .when()
    	            .get("/books/")
    	        .then()
    	            .statusCode(200)
    	            .body("$", not(empty()))         
    	            .body("[0].id", notNullValue())  
    	            .log().all();
    	 System.out.println("Booking retrieved successfully!");
    }
    @Test(priority = 3, dependsOnMethods = "createBook")
    @Description("Get Book  By Book ID via API")
    public void getBookById() {
    	  String id = Base.createdBookIds.get(0);
            given()
                .header("Authorization", "Bearer " + Base.authToken)
                .pathParam("id", id)
            .when()
                .get("/books/{id}")
            .then()
                .statusCode(200)
                .body("id", equalTo(Integer.parseInt(id))) 
                .log().body();
            System.out.println("All books retrieved successfully");
            System.out.println("Book with ID " + id + " retrieved successfully.");
        }
    @Test(priority = 4, dependsOnMethods = "createBook")
    @Description("Update Book via API")
    public void updateBook() {
    	String id = Base.createdBookIds.get(0);
            Book updatedBook = new Book();
            updatedBook.setName("ASD");
            updatedBook.setAuthor("PP");
            updatedBook.setPublishedYear("2024");
            updatedBook.setBookSummary("Updated Summary...");
            updatedBook.setId(Integer.parseInt(id));

            given()
                .header("Authorization", "Bearer " + Base.authToken)
                .contentType("application/json")
                .pathParam("id", id)
                .body(updatedBook)
            .when()
                .put("/books/{id}")
            .then()
                .statusCode(200)
                .body("name", equalTo("ASD"))
                .log().all();
            System.out.println("Book with ID " + id + " updated successfully!");
            System.out.println("Books are updating successvfully!!");
            
            
    }
        @Test(priority = 5, dependsOnMethods = "updateBook")
        @Description("Delete book via API")
        public void deleteBook() {
            String id = Base.createdBookIds.get(0); 
                given()
                    .header("Authorization", "Bearer " + Base.authToken)
                    .pathParam("id", id)
                .when()
                    .delete("/books/{id}")
                .then()
                    .statusCode(200);
                
                System.out.println("Books are deleted successfully!");
                System.out.println("Book with ID " + id + " deleted successfully!");
                
                given()
                     .header("Authorization", "Bearer " + Base.authToken)
                     .pathParam("id", id)
                .when()
                     .get("/books/{id}")
                .then()
                      .statusCode(404);    
                System.out.println("Verified book deletion for ID " + id);
                      
                Base.createdBookIds.clear();
        }
}
    

