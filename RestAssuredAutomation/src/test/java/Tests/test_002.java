package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
// if static keyword is used in the import statement then no need to define the class name while using 
import io.restassured.response.Response;

public class test_002 {
		@Test
		public void first_test() {

			Response response = get("https://reqres.in/api/users?page=2");
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody().asString());
			
			Assert.assertEquals(response.getStatusCode(), 200);
		}
}
