package Tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class TestOnLocalAPIs_003 {
	
	@Test
	public void getRequest() {
		
		baseURI = "http://localhost:3000";
		given().get("/users").then().statusCode(200).log().all();
	}
	
	@Test
	public void postRequest() {
		JSONObject request = new JSONObject();
		request.put("firstname", "Dipak");
		request.put("lastname", "Patil");
		request.put("subjectid", 1);
		
		baseURI = "http://localhost:3000";
		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201);
	}

	@Test
	public void putRequest() {
		JSONObject request = new JSONObject();
		request.put("firstname", "John");
		request.put("lastname", "Sena");
		request.put("subjectid", 2);
		
		baseURI = "http://localhost:3000";
		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			put("/users/102").
		then().
			statusCode(200);
	}
	
	@Test
	public void patchRequest() {
		JSONObject request = new JSONObject();
		request.put("lastname", "Peter");
		
		baseURI = "http://localhost:3000";
		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			patch("/users/102").
		then().
			statusCode(200);
	}
	
	@Test
	public void deleteRequest() {
		baseURI = "http://localhost:3000";
		
		given().when().delete("/users/103").
		then().
			statusCode(200);
	}
}
