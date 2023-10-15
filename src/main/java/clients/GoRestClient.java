package clients;

import entities.User;
import gherkin.deps.com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import response.GetResponse;
import response.PostErrorResponse;
import response.PostResponse;


import static io.restassured.RestAssured.given;

public class GoRestClient{
    Response response;
    Gson gson = new Gson();
    public GetResponse getGoRest() {
        response= given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://gorest.co.in/public/v1/users")
                .then()
                .extract().response();
        Assert.assertEquals(response.statusCode(),200);

        response.prettyPrint();
        String stringResponse=  response.jsonPath().prettify();

        GetResponse finalResponse = gson.fromJson(stringResponse, GetResponse.class);
        return finalResponse;

    }

    public PostResponse postGoRest(User user) {
        String accessToken= "Bearer 1db9c9b6c959682be7c96f74ca532c3cb0bd331f46b86a92602f8d319481b6f5";

        Response response = given().accept(ContentType.JSON)
                .and().header("Authorization", accessToken)
                .and().contentType(ContentType.JSON)
                .body(user)
                .when().post("https://gorest.co.in/public/v1/users");

        Assert.assertEquals(response.statusCode(),201);

        response.prettyPrint();

        String stringResponse=  response.jsonPath().prettify();
        PostResponse finalResponse = gson.fromJson(stringResponse, PostResponse.class);
        return finalResponse;

    }

    public PostErrorResponse postSameUserGoRest(User user) {
        String accessToken= "Bearer 1db9c9b6c959682be7c96f74ca532c3cb0bd331f46b86a92602f8d319481b6f5";

        Response response = given().accept(ContentType.JSON)
                .and().header("Authorization", accessToken)
                .and().contentType(ContentType.JSON)
                .body(user)
                .when().post("https://gorest.co.in/public/v1/users");

        Assert.assertEquals(response.statusCode(),422);

        response.prettyPrint();

        String stringResponse=  response.jsonPath().prettify();
        PostErrorResponse finalResponse = gson.fromJson(stringResponse, PostErrorResponse.class);
        return finalResponse;

    }




}
