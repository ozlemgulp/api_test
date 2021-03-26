package components;

import static io.restassured.RestAssured.given;

import config.Spec;
import io.restassured.response.Response;

public class UserComponent {

	public static Spec spec = new Spec("users");

	public static Response getAllUsers() {
		Response allUsersResponse = given().spec(spec.requestSpec).get();
		return allUsersResponse;
	}

	public static Response getUser(String username) {
		Response allUsersResponse = given().spec(spec.requestSpec).param("username", username).get();
		System.out.println(allUsersResponse.getBody().asString());
		return allUsersResponse;
	}

}
