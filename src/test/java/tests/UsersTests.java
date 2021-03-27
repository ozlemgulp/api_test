package tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import components.UserComponent;
import general.BasicLogger;
import models.User;

public class UsersTests {

	final static Logger log = Logger.getLogger(BasicLogger.class);

	@Test
	public void verifyThanGetAllUsersResponceIsSuccess() {
		UserComponent.getAllUsers().then().assertThat().statusCode(200);
	}

	@Test
	public void getUser() {
		String searchedUserName = "Delphine";
		//GetUser Responce as User Model 
		User[] users = UserComponent.getUser(searchedUserName).getBody().as(User[].class);
		if (users.length > 0) {
			log.info("userId for the  " + searchedUserName + " = " + users[0].getId());
			// Assert that searched username string equals to the username on the responce
			Assert.assertEquals(searchedUserName, users[0].getUsername());
		} else
			Assert.fail("No user found with username = " + searchedUserName);

	}

}
