package com.qa.restassured;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;


public class GetData {
	Logger logger = Logger.getLogger(GetData.class);
	
	@Test(priority=1)
	public void testResponseCode() {
		
		Response resp = RestAssured.get("http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
		
		int code = resp.getStatusCode();
		
		System.out.println("Status code is "+code);
		
		Assert.assertEquals(code, 200);
		logger.info("Response code : "+code);
	}
	
	@Test(priority=2)
	public void testBody() {
		Response resp = RestAssured.get("http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
		
		String body = resp.asString();
		
		System.out.println("Body is : "+body);
	}
	@Test(priority=3)
	public void testBodyText() {
		
		given().
		when().get("http://ergast.com/api/f1/2017/circuits.json").
		then().
			assertThat().
			
			statusCode(200).
			
			body("MRData.CircuitTable.Circuits.circuitId", hasSize(20)).
			
			and().
			
			header("content-length", equalTo("4551"));
		
	}
	
}
