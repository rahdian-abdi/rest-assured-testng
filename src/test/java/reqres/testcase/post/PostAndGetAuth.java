package reqres.testcase.post;

import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class PostAndGetAuth {
    private String LOGIN;
    private String TOKEN;
    private String GET_AUTH;
    private JsonPath jsonPath;
    private String body;

    @BeforeMethod
    public void setUp(){
        LOGIN = "https://dummyjson.com/auth/login";
        GET_AUTH = "https://dummyjson.com/auth/products";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "kminchelle");
        jsonObject.put("password", "0lelplR");

        Response response = given().body(jsonObject).header("content-type", "application/json").
                            when().post(LOGIN).
                            then().statusCode(200).extract().response();

        int statusCode = response.statusCode();
        System.out.println("The Status Code is : " + statusCode);

        jsonPath = response.jsonPath();
        TOKEN = jsonPath.get("token");

    }
    @AfterMethod
    public void tearDown(){
        System.out.println(body);
    }
    @Test
    public void loginValidation(){

        Response response = given().header("Authorization", TOKEN).contentType(ContentType.JSON).
                            when().get(GET_AUTH).
                            then().body("products.title", Matchers.hasItem("iPhone 9")).
                                   body("products", Matchers.hasSize(30)).
                            extract().response();

        body = response.asPrettyString();
    }
}
