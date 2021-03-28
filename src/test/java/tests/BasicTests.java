package tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import components.CommentComponent;
import components.PostComponent;
import components.UserComponent;
import general.BasicLogger;
import models.Comment;
import models.Post;
import models.User;
import utils.EmailVerifier;

public class BasicTests {
	final static Logger log = Logger.getLogger(BasicLogger.class);

	public static User[] users;
	public static Post[] posts;
	public static Comment[] comments;

	@BeforeClass
	public void beforeClass() {
		// Chek if the Server is Up before Tests
		Assert.assertEquals(UserComponent.getServerResponseCode(), 200);
	}

	@Parameters({ "userName" }) // get the userName from testng.xml
	@Test
	public void verifyThatUserNameExist(String userName) {
		// GetUser Responce as User Model as a Array, because Username may not be unique
		User[] users = UserComponent.getUserAsUserObj(userName);
		if (users.length > 0) {
			log.info("userId for the userName " + userName + " = " + users[0].getId());
			// Assert that searched username string equals to the username in the response
			Assert.assertEquals(userName, users[0].getUsername());
		} else
			Assert.fail("No user found with username = " + userName);

	}

	@Parameters({ "userName" }) // get the userName from testng.xml
	@Test(dependsOnMethods = "verifyThatUserNameExist") // If UserName not exist do not run this test
	public void verifyPostsByUserId(String userName) {

		users = UserComponent.getUserAsUserObj(userName);
		if (users.length > 0) {
			for (int i = 0; i < users.length; i++) {
				log.info("UserName : " + userName + "UserId : " + users[i].getId());
				posts = PostComponent.getUserPostsAsPostObj(users[i].getId());
				if (posts.length > 0) {
					// Assert that user Id equals to post response userId
					Assert.assertEquals(posts[i].getUserId(), users[i].getId());

				} else {
					log.info("UserName : " + userName + " has no post");
					Assert.fail("UserName : " + userName + " has no post");

				}
			}
		} else {
			Assert.fail("There is no such a userName :" + userName);
			log.info("There is no such a userName : " + userName);

		}
	}

	@Parameters({ "userName" }) // get the userName from testng.xml
	@Test(dependsOnMethods = "verifyThatUserNameExist") // If UserName not exist do not run this test
	public void verifyEmailsOnComments(String userName) {
		users = UserComponent.getUserAsUserObj(userName);
		// Get posts for each user
		for (int i = 0; i < users.length; i++) {
			posts = PostComponent.getUserPostsAsPostObj(users[i].getId());
			// Get comments for each post
			for (int j = 0; j < posts.length; j++) {
				comments = CommentComponent.getCommentsOnPostAsCommentObj(posts[j].getId());
				if (comments.length > 0) {
					for (int k = 0; k < comments.length; k++) {
						// Verify email is not Null
						Assert.assertNotNull(comments[k].getEmail());
						// Verify emailFormat for each comment
						Assert.assertTrue(EmailVerifier.isEmailAdressValid(comments[k].getEmail()));
						// Verify that posts Id equals to the comments postId.
						Assert.assertEquals(posts[j].getId(), comments[k].getPostId());
						log.info(comments[k].getEmail());
					}
				} else {
					Assert.fail("There is no comment");
				}
			}

		}

	}

	@Parameters({ "userName", "maxAllowedResponseTime" }) // get the userName from testng.xml
	@Test(dependsOnMethods = "verifyThatUserNameExist") // If UserName not exist do not run this test
	public void verifyResponceTime(String userName, long maxAllowedResponseTime) {
		boolean result = true;
		users = UserComponent.getUserAsUserObj(userName);
		if (users.length > 0) {
			// Get posts for each user
			for (int i = 0; i < users.length; i++) {
				posts = PostComponent.getUserPostsAsPostObj(users[i].getId());
				// Get comments for each post
				for (int j = 0; j < posts.length; j++) {
					long responseTime = CommentComponent.getCommentsOnPost(posts[j].getId()).getTime();
					if (responseTime - maxAllowedResponseTime > 0) {
						result = false;
					}

				}

			}

		}
		Assert.assertTrue(result);
	}

}
