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
	public void verifyThatUserNameExist() {
		String searchedUserName = "Delphine";
		// GetUser Responce as User Model as a Array, Username may not be unique
		User[] users = UserComponent.getUserAsUserObj(searchedUserName);
		if (users.length > 0) {
			log.info("userId for the  " + searchedUserName + " = " + users[0].getId());
			// Assert that searched username string equals to the username on the response
			Assert.assertEquals(searchedUserName, users[0].getUsername());
		} else
			Assert.fail("No user found with username = " + searchedUserName);

	}

}
