package tests;

import org.testng.annotations.Test;

import components.PostComponent;
import components.UserComponent;
import io.restassured.response.Response;
import models.User;

public class PostTests {

	@Test
	public void verifyThaTGetPostsResponceIsSuccess() {
		PostComponent.getPosts().then().assertThat().statusCode(200);
	}

	@Test
	public void getUserCommentsById() {
		// Get UserId from users responce and get User Posts
		Response response = UserComponent.getUser("Delphine");
		User[] user = UserComponent.convertResponceToUserObj(response);
		for (int i = 0; i < user.length - 1; i++) {
			PostComponent.getPosts(user[i].getId()).then().assertThat().statusCode(200);

		}

	}
}
