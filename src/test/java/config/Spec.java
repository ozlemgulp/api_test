package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Spec {
	public static RequestSpecification requestSpec;
	public static ResponseSpecification successResponseSpec;

	public Spec(String path) {
		Spec.requestSpec = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com/")
				.addHeader("Content-Type", "application/json")
				.setBasePath(path)
	            .addFilter(new ResponseLoggingFilter())//log request and response for better debugging. You can also only log if a requests fails.
	            .addFilter(new RequestLoggingFilter())
				.build();
		;
	}
}
