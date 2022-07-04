package Tests;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class put_patch_delete {
	
	@Test
	public void put_request() {
		Map <String, Object> map = new HashMap<String, Object> ();

		JSONObject request = new JSONObject(map);
		request.put("name", "Dipak");
		request.put("job", "AE");
		
		System.out.println(request.toJSONString());
		
		baseURI = "https://reqres.in/api";
		given().
			contentType(ContentType.JSON). // I am sending the request in json format
		accept(ContentType.JSON).			//and I need response in json as well
			body(request.toJSONString()).
		when().
			put("/users/2").
		then().
			statusCode(200);
	}
	
	@Test
	public void patch_request() {
		Map <String, Object> map = new HashMap<String, Object> ();

		JSONObject request = new JSONObject(map);
		request.put("name", "Dipak");
		request.put("job", "AE");
		
		System.out.println(request.toJSONString());
		
		baseURI = "https://reqres.in";
		given().
			contentType(ContentType.JSON). // I am sending the request in json format
		accept(ContentType.JSON).			//and I need response in json as well
			body(request.toJSONString()).
		when().
			patch("/api/users/2").
		then().
			statusCode(200);
	}
	
	@Test
	public void delete_request() {
		baseURI = "https://reqres.in/api";
		when().
			delete("/users/2").
		then().
			statusCode(204);
	}
}
