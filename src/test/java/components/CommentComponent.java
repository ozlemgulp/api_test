package components;


import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import config.Spec;

public class CommentComponent { 
	
	//Create new Specification for each component and set the path "users"

	
	public static Spec spec = new Spec("comments");
	


    public static Response getCommentsOnPost(int postId) {
        Response allCommentsOnPostResponse = given()
                .spec(Spec.requestSpec)
                .param("postId", postId)
                .get();

        return allCommentsOnPostResponse;
    }

}
