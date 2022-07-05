package Tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
//import io.restassured.RestAssured;
import io.restassured.response.Response;

public class test_001 {
	@Test
	public void first_test() {
		
//		Response response = RestAssured.get("https://reqres.in/api/users?page=2");
		Response response = get("https://reqres.in/api/users?page=2");
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asString());
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test
	public void second_test() {
		baseURI = "https://reqres.in/api";
		given().get("/users?page=2").then().statusCode(200);
		//for given url the status code should be 200
		
		//To exactly match the reponse body
		given().get("/users?page=2").then().body("data[1].id", equalTo(8));
		
		given().get("/users?page=2").then().body("data.first_name", hasItems("George", "Rachel"));
		
		//To get the whole response on console
		given().get("/users?page=2").then().body("data[1].id", equalTo(8)).log().all();
	}
	
	@Test
	public void post_request() {
		//first approach
		Map <String, Object> map = new HashMap<String, Object> ();
		map.put("name", "kiran");
		map.put("job", "QA");
		
		System.out.println(map);
		
		JSONObject request = new JSONObject(map);
		System.out.println(request.toJSONString());
		
		//second approach
		JSONObject request1 = new JSONObject(map);
		request1.put("name", "Dipak");
		request1.put("job", "AE");
		
		System.out.println(request1.toJSONString());
		
		baseURI = "https://reqres.in/api";
		given().
			contentType(ContentType.JSON). // I am sending the request in json format
		accept(ContentType.JSON).			//and I need response in json as well
			body(request.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201);
	}
}
