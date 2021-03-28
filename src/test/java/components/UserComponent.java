package components;

import static io.restassured.RestAssured.given;

import org.apache.log4j.Logger;

import general.BasicLogger;
import general.Spec;
import io.restassured.response.Response;
import models.User;

public class UserComponent {
	
	final static Logger log = Logger.getLogger(BasicLogger.class);


	public static Spec spec = new Spec();

	public static Response getUser() {
		Response allUsersResponse = given()
				.spec(Spec.requestSpec)
				.basePath("users")
				.get();
		allUsersResponse
				.then()
				.statusCode(200);
		return allUsersResponse;
	}

	public static Response getUser(String username) {
		Response usersResponse = given()
				.spec(Spec.requestSpec)
				.basePath("users")
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
				.basePath("users")
				.param("username", username)
				.when()
				.get()
				.then()
				.statusCode(200)
				.extract().as(User[].class);
		return users;
	}
	
	public static int getServerResponseCode() {
		int serverResponse = given()
				.spec(Spec.requestSpec)
				.get()
				.getStatusCode();
		return serverResponse;
	}

}