package tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import components.PostComponent;
import components.UserComponent;
import general.BasicLogger;
import models.Post;
import models.User;

public class PostsTests {

	final static Logger log = Logger.getLogger(BasicLogger.class);

	public static User[] users;
	public static Post[] posts;

	@Test
	public void verifyPostsByUserId() {

		String username = "Delphine";
		users = UserComponent.getUserAsUserObj(username);
		if (users.length > 0) {
			for (int i = 0; i < users.length; i++) {
				log.info("UserName : " + username + "UserId : " + users[i].getId());
				posts = PostComponent.getUserPostsAsPostObj(users[i].getId());
				if (posts.length > 0) {
					// Assert that user Id equals to post response userId
					Assert.assertEquals(posts[i].getUserId(), users[i].getId());

				} else {
					log.info("UserName :" + username + "has no post");
				}
			}
		} else {
			log.info("There is no such a userName :" + username);

		}
	}

}
