package reqres.testcase.get;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class BaseUri {
    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void getUrl(){
        given().header("Content-Type", "application/json").
                contentType(ContentType.JSON).
        when().get("/users/2").
        then().statusCode(200).body("data.first_name", containsString("Janets"));
    }
}
