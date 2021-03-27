package components;

import static io.restassured.RestAssured.given;

import org.apache.log4j.Logger;

import config.Spec;
import general.BasicLogger;
import io.restassured.response.Response;
import models.User;

public class UserComponent {
	
	final static Logger log = Logger.getLogger(BasicLogger.class);


	// Create new Specification for each component and set the path "users"
	public static Spec spec = new Spec("users");

	public static Response getUser() {
		Response allUsersResponse = given()
				.spec(Spec.requestSpec)
				.get();
		allUsersResponse
				.then()
				.statusCode(200);
		return allUsersResponse;
	}

	public static Response getUser(String username) {
		Response usersResponse = given()
				.spec(Spec.requestSpec)
				.param("username", username)
				.get();
		usersResponse
				.then()
				.statusCode(200);

		log.info(usersResponse.getBody().asString());
		return usersResponse;
	}

	public static User[] getUserAsUserObj(String username) {
		User[] users = given()
				.spec(Spec.requestSpec)
				.param("username", username)
				.when()
				.get()
				.then()
				.statusCode(200)
				.extract().as(User[].class);
		return users;
	}

}