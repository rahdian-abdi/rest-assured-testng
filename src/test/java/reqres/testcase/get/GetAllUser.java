package reqres.testcase.get;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.*;
import reqres.dataprovider.CustomDataProvider;
import java.io.File;

public class GetAllUser {
    public File JSON_SCHEMA;
    public String URL;
    public String INVALID_URL;
    @BeforeMethod
    public void setUp(){
        String DIR = System.getProperty("user.dir");
        String JSON_SCHEMA_PATH = DIR+"/src/test/resources/reqres/jsonschema/get";
        JSON_SCHEMA = new File(JSON_SCHEMA_PATH+"/GetAllUser.json");

        URL = "https://reqres.in/api/users?page=";
        INVALID_URL = "https://reqres.in/api/userspage=1";
    }
    @AfterMethod
    public void tearDown(){
    }
    @Test(dataProvider = "ValidPage", dataProviderClass = CustomDataProvider.class)
    public void getAllUser(int page){
        switch (page){
            case 2:
                given().header("Content-Type", "application/json")
                       .contentType(ContentType.JSON).
                when().get(URL+page).
                then().statusCode(200)
                      .body("data[0].email", equalTo("michael.lawson@reqres.in"))
                      .body("page", equalTo(2))
                      .body("data.first_name", hasItem("Michael"))
                      .body(matchesJsonSchema(JSON_SCHEMA));
                break;
            case 1:
                given().header("Content-Type", "application/json").contentType(ContentType.JSON).
                when().get(URL+page).
                then().statusCode(200)
                      .body("data[0].email", equalTo("george.bluth@reqres.in"))
                      .body("page", equalTo(1))
                      .body("data.first_name", contains("George", "Janet", "Emma", "Eve", "Charles", "Tracey"))
                      .body(matchesJsonSchema(JSON_SCHEMA));
                break;
            default:
                given().get(URL+page)
                        .then()
                        .statusCode(200)
                        .body("data", empty());
        }
    }
    @Test(dataProvider = "InvalidPage", dataProviderClass = CustomDataProvider.class)
    public void getAllUserInvalidPage(String page){
        given().
        when().get(URL+page).
        then().
                statusCode(404).
                body("data", empty());
    }
    @Test
    public void getAllUserWrongUrl(){
        given()
                .when().get(INVALID_URL)
                .then().statusCode(404);
    }
}
