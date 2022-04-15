package com.stepDefinition;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Steps {
	
	@Test(priority = 1 , groups = "GET Request")
	@Given("I am on Swagger portal to get all video games")
	public void i_am_on_Swagger_portal_to_get_all_video_games() {
		System.err.println("\n====================== GET REQUEST ===========================\n");
		Response res =
				
				given().
					contentType("application/json").
					accept(ContentType.JSON).
				when().
					get("http://localhost:3000/Customers").
				then().
					statusCode(200).
					log().all().
					extract().response();
					
					int statusCodes = res.statusCode();
					Assert.assertEquals(statusCodes, 200);
					System.err.println("Status Code is '"+statusCodes+"' and Passed");
					System.out.println("\n================================================================");
	}
	@Test (priority = 2, groups = "POST Request")
	@When("I added new videoGame in database")
	public void i_added_new_videoGame_in_database() {
		System.err.println("\n====================== POST REQUEST ===========================\n");
					HashMap data = new HashMap();
					data.put ("id", "109");  
					data.put ("name", "Jurasic-Park-1");  
					data.put ("releaseDate", "2019-09-20T08:55:58.210Z");  
					data.put ("reviewScore", "9");  
					data.put ("category", "Adventure");  
					data.put ("rating", "Metro");
					
				Response response =
				given().
					contentType("application/json").
					accept(ContentType.JSON).
					body(data).
				when().
					post("http://localhost:8080/app/videogames").
				then().
					statusCode(200).
					log().all().
					extract().response();
					
					String jsonString = response.asPrettyString();
					Assert.assertEquals(jsonString.contains("Record Added Successfully"), true);
					System.err.println("Varification has passed!!!");
					System.out.println("\n==============================================================");
		
	}
	@Test(priority =3 , groups = "GET Request")
	@Then("I verify with response message")
	public void i_verify_with_response_message() {
		System.err.println("\n====================== GET REQUEST ===========================\n");			
		Response res =
				
				given().
					contentType("application/json").
					accept(ContentType.JSON).
				when().
					get("http://localhost:8080/app/videogames?id=109").
				then().
					statusCode(200).
					log().all().
					extract().response();
					
					int statusCodes = res.statusCode();
					Assert.assertEquals(statusCodes, 200);
					System.err.println("Status Code is '"+statusCodes+"' and Passed");
					System.out.println("\n==============================================================");
	}
	@Test(priority = 4, groups = "PUT Request")
	@Then("I update a record on specific ID")
	public void i_update_a_record_on_specific_ID() {
		System.err.println("\n====================== PUT REQUEST ===========================\n");
					HashMap data = new HashMap();
					data.put ("id", "109");  
					data.put ("name", "Casino-Royal");  
					data.put ("releaseDate", "2019-09-20T08:55:58.210Z");  
					data.put ("reviewScore", "6");  
					data.put ("category", "Adventure");  
					data.put ("rating", "Metro");
		
				given().
					contentType("application/json").
					contentType(ContentType.JSON).
					body(data).
				when().
					put("http://localhost:8080/app/videogames/109").
				then().
					body("videoGame.name", equalTo("Casino-Royal")).
					body("videoGame.reviewScore", equalTo("6")).
					statusCode(200).
					log().all();
				System.out.println("\n==============================================================");
	}
	@Test(priority = 5, groups = "GET Request")
	@Then("verify it has updated with a response message")
	public void verify_it_has_updated_with_a_response_message() {
		System.err.println("\n====================== GET REQUEST ===========================\n");
			
		Response res =
				
				given().
					contentType("application/json").
					accept(ContentType.JSON).
				when().
					get("http://localhost:8080/app/videogames?id=109").
				then().
					statusCode(200).
					log().all().
					extract().response();
					
					int statusCodes = res.statusCode();
					Assert.assertEquals(statusCodes, 200);
					System.err.println("Status Code is '"+statusCodes+"' and Passed");
					System.out.println("\n==============================================================");
	}
	@Test(priority = 6, groups = "DELETE Request")
	@Then("I delete any record from database")
	public void i_delete_any_record_from_database() {
		System.err.println("\n====================== DELETE REQUEST ===========================\n");
		
		Response response =
				given().
				when().
						delete("http://localhost:8080/app/videogames/109").
				then().
					statusCode(200).
					log().all().
					extract().response();
					int code = response.statusCode();
					String jsonString = response.asPrettyString();
					Assert.assertEquals(jsonString.contains("Record Deleted Successfully"), true);
					Assert.assertEquals(code, 200);
					System.err.println("The Status Code : "+code);
					System.err.println("The Status Message is : "+jsonString);
					System.out.println("\n==============================================================");
	}
	@Test(priority = 7, groups = "GET Request", expectedExceptions = AssertionError.class)
	@Then("verify the record has deleted with response message")
	public void verify_the_record_has_deleted_with_response_message() throws PendingException{
		baseURI = "http://localhost:8080/app/videogames/109";
		System.err.println("\n====================== GET REQUEST ===========================\n");
		Response res =	
				given().
					contentType("application/json").
					accept(ContentType.JSON).
				when().
					get(baseURI).
				then().
					statusCode(200).
					log().all().
					extract().response();
					
					int statusCodes = res.statusCode();
					Assert.assertEquals(statusCodes, 200, "No Available Data available!!!");
					System.err.println("Status Code is '"+statusCodes+"' and Passed");
					
					if(baseURI.equals(null)) {
						System.err.println("Data has been deleted Successfully!!");						
					}
					System.out.println("\n==============================================================");
				//throws.PendingException();
	}
}
