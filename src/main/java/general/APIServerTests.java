package general;

import static io.restassured.RestAssured.given;

public class APIServerTests {

    public static boolean isAppBackendUp() {
        return given()
                .spec(Spec.requestSpec)
                .get().statusCode() == 200;

    }
}