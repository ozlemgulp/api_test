package components;


import static io.restassured.RestAssured.given;

import general.Spec;
import io.restassured.response.Response;
import models.Comment;

public class CommentComponent { 
	

	
	public static Spec spec = new Spec();
	


    public static Response getCommentsOnPost(int postId) {
        Response allCommentsOnPostResponse = given()
                .spec(Spec.requestSpec)
				.basePath("comments")
                .param("postId", postId)
                .get();
        allCommentsOnPostResponse
        		.then()
        		.statusCode(200);

        return allCommentsOnPostResponse;
    }

    public static Comment[] getCommentsOnPostAsCommentObj(int postId) {
        Comment[] allCommentsOnPostResponse = given()
                .spec(Spec.requestSpec)
				.basePath("comments")
                .param("postId", postId)
				.when()
				.get()
				.then()
				.statusCode(200)
				.extract().as(Comment[].class);

        return allCommentsOnPostResponse;
    }
}
