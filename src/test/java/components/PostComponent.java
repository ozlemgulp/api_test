package components;

import static io.restassured.RestAssured.given;

import config.Spec;
import io.restassured.response.Response;

public class PostComponent {

	// Create new Specification for each component and set the path "posts"
	public static Spec spec = new Spec("posts");

	public static Response getPosts(int userId) {
		Response allPostsResponse = given().spec(Spec.requestSpec).param("userId", userId).get();

		return allPostsResponse;
	}

	public static Response getPosts() {
		Response allPostsResponse = given().spec(Spec.requestSpec).get();

		return allPostsResponse;
	}

}
