package components;

import static io.restassured.RestAssured.given;

import general.Spec;
import io.restassured.response.Response;
import models.Post;

public class PostComponent {

	public static Spec spec = new Spec();

	public static Response getPosts() {
		Response allPostsResponse = given()
				.spec(Spec.requestSpec)
				.basePath("posts")
				.get();
		allPostsResponse
				.then()
				.statusCode(200);
		return allPostsResponse;
	}

	public static Response getPosts(int userId) {
		Response allPostsResponse = given()
				.spec(Spec.requestSpec)
				.basePath("posts")
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
				.basePath("posts")
				.param("userId", userId)
				.when()
				.get()
				.then()
				.statusCode(200)
				.extract().as(Post[].class);
		return userPostsResponse;
	}
}
