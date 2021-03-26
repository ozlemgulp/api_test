package components;

import static io.restassured.RestAssured.given;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import config.Spec;
import io.restassured.response.Response;
import models.User;

public class UserComponent {

	//Create new Specification for each component and set the path "users"
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

	public static  User[] convertResponceToUserObj(Response response) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonResponce = gson.toJson(response);
		User[] user = new Gson().fromJson(jsonResponce, User[].class);
		return user;

	}



}