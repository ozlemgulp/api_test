package tests;

import org.testng.annotations.Test;

import components.PostComponent;

public class PostsTests {

	@Test
	public void verifyThaTGetPostsResponceIsSuccess() {
		PostComponent.getPosts().then().assertThat().statusCode(200);
	}

}
