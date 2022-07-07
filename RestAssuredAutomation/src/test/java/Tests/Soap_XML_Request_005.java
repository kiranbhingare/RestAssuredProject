package Tests;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.equalTo;
public class Soap_XML_Request_005 {
	
	@Test
	public void soapXMLValidation() throws IOException {
		
		// Calling the file in code
		// ./  is if the file is available in your project
		File file = new File("./SoapRequest/add.xml");
		// Using the called file
		if(file.exists())
			System.out.println("File Exist___");

		FileInputStream fs = new FileInputStream(file);
		// For encoding
		String requestBody = IOUtils.toString(fs, "UTF-8");
		
		RestAssured.baseURI = "http://www.dneonline.com/";
		// If static import the same then no need to write like this. 
	
		given().
			contentType("text/xml").
			accept(ContentType.XML).
			body(requestBody).
			when().
				post("/calculator.asmx").
			then().
				statusCode(200).
				log().all().
		//  Validate SOAP XML Response Body 
			and().
				body("//*:AddResult.text()", equalTo("7"));
	}
}
