package components;

import static io.restassured.RestAssured.given;

import config.Spec;
import io.restassured.response.Response;
import models.Post;

public class PostComponent {

	// Create new Specification for each component and set the path "posts"
	public static Spec spec = new Spec("posts");

	public static Response getPosts() {
		Response allPostsResponse = given()
				.spec(Spec.requestSpec)
				.get();
		allPostsResponse
				.then()
				.statusCode(200);
		return allPostsResponse;
	}

	public static Response getPosts(int userId) {
		Response allPostsResponse = given()
				.spec(Spec.requestSpec)
				.param("userId", userId)
				.get();
		allPostsResponse
				.then()
				.statusCode(200);
		return allPostsResponse;
	}

	public static Post[] getUserPostsAsPostObj(int userId) {
		Post[] userPostsResponse = given()
				.spec(Spec.requestSpec)
				.param("userId", userId)
				.when()
				.get()
				.then()
				.statusCode(200)
				.extract().as(Post[].class);
		return userPostsResponse;
	}
}
