package tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import components.CommentComponent;
import components.PostComponent;
import components.UserComponent;
import general.BasicLogger;
import models.Comment;
import models.Post;
import models.User;
import utils.EmailVerifier;

public class CommandsTests {
	final static Logger log = Logger.getLogger(BasicLogger.class);

	public static User[] users;
	public static Post[] posts;
	public static Comment[] comments;

	@Test
	public void verifyEmailsOnComments() {
		

		String username = "Delphine";
		users = UserComponent.getUserAsUserObj(username);
		// Get posts for each user
		for (int i = 0; i < users.length; i++) {
			posts = PostComponent.getUserPostsAsPostObj(users[i].getId());
			// Get comments for each post
			for (int j = 0; j < posts.length; j++) {
				comments = CommentComponent.getCommentsOnPostAsCommentObj(posts[j].getId());
				for (int k = 0; k < comments.length; k++) {
					// Verify emailFormat for each comment
					Assert.assertTrue(EmailVerifier.isEmailAdressValid(comments[k].getEmail()));
					log.info(comments[k].getEmail());
				}
			}
		}

	}

}
