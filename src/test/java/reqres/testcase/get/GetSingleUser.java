package reqres.testcase.get;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reqres.dataprovider.CustomDataProvider;

import java.io.File;

import static io.restassured.RestAssured.given;

public class GetSingleUser {
    public String URL;
    public String INVALID_URL;
    public File JSON_SCHEMA;
    @BeforeMethod
    public void setUp(){
        String DIR = System.getProperty("user.dir");
        String PATH = DIR+"/src/test/java/reqres/jsonschema/get/";
        JSON_SCHEMA = new File(PATH+"GetSingleUser.json");

        URL = "https://reqres.in/api/users/";
        INVALID_URL = "https://reqres.in/api/users/";
    }
    @AfterMethod
    public void tearDown(){

    }
    @Test(dataProvider = "UserId", dataProviderClass = CustomDataProvider.class)
    public void getSingleUser(int id){
        switch (id){
            case 2:
                given()
                        .when().get(URL+id)
                        .then().statusCode(200)
                                .body("data.email", Matchers.equalTo("janet.weaver@reqres.in"))
                                .body(JsonSchemaValidator.matchesJsonSchema(JSON_SCHEMA));
                break;
            case 1:
                given().get(URL+id)
                        .then()
                        .statusCode(200)
                        .body("data.email", Matchers.equalTo("george.bluth@reqres.in"));
                break;
            default:
                given().get(URL+id)
                        .then()
                        .statusCode(404);
        }
    }
    @Test(dataProvider = "InvalidUserId", dataProviderClass = CustomDataProvider.class)
    public void getSingleUserInvalidId(String id){
        given().when()
                .get(INVALID_URL+id)
                .then()
                .statusCode(404);
    }
}
