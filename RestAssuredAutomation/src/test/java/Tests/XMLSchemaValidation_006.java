package Tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;

public class XMLSchemaValidation_006 {
	
	@Test
	public void SchemaValidation() throws IOException {
		
		File file = new File("./SoapRequest/add.xml");
		if(file.exists())
			System.out.println("File Exist___");

		FileInputStream fs = new FileInputStream(file);
		String requestBody = IOUtils.toString(fs, "UTF-8");
		
		RestAssured.baseURI = "http://www.dneonline.com/";

		given().
			contentType("text/xml").
			accept(ContentType.XML).
			body(requestBody).
			when().
				post("/calculator.asmx").
			then().
				statusCode(200).
				log().all().
			and().
				body("//*:AddResult.text()", equalTo("7")).
			// validation of XML schema
			and().
				assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("calculator.xsd"));
	}
}
