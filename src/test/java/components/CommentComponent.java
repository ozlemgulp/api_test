package components;


import static io.restassured.RestAssured.given;

import config.Spec;
import io.restassured.response.Response;
import models.Comment;

public class CommentComponent { 
	
	//Create new Specification for each component and set the path "users"

	
	public static Spec spec = new Spec("comments");
	


    public static Response getCommentsOnPost(int postId) {
        Response allCommentsOnPostResponse = given()
                .spec(Spec.requestSpec)
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
                .param("postId", postId)
				.when()
				.get()
				.then()
				.statusCode(200)
				.extract().as(Comment[].class);

        return allCommentsOnPostResponse;
    }
}
