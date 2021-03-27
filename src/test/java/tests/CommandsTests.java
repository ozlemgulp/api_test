package tests;

import static org.hamcrest.CoreMatchers.containsString;

import org.testng.annotations.Test;

import components.UserComponent;

public class CommandsTests {


	@Test
	public void verifyThanGetAllUsersResponceIsSuccess() {
		UserComponent.getAllUsers().then().assertThat().statusCode(200);
	}

	@Test
	public void getUser() {
		
		
		UserComponent.getUser("Delphine").then().assertThat().statusCode(200).and().body(containsString("username"));

	}


	

}
