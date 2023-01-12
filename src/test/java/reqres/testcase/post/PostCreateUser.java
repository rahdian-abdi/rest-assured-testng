package reqres.testcase.post;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import reqres.dataprovider.CustomDataProvider;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class PostCreateUser {
    public String PATH;
    public String URL;
    public String URL_DDT;
    @BeforeMethod
    public void setUp(){
        String DIR = System.getProperty("user.dir");
        PATH = DIR+"/src/test/java/reqres/jsonschema/get";

        URL = "https://reqres.in/api/";
        URL_DDT = "https://reqres.in/api/users";
    }
    @AfterMethod
    public void tearDown(){

    }
    @Test(dataProvider = "CreateParameter", dataProviderClass = CustomDataProvider.class)
    public void postCreateUser(String parameter){

        JSONObject request = new JSONObject();
        request.put("name", "dian");
        request.put("job", "software engineer in test");

        switch (parameter){
            case "users":
                given().body(request)
                       .header("Content-Type", "application/json")
                       .contentType(ContentType.JSON).
                when().post(URL+parameter).
                then().statusCode(201)
                      .body("$", Matchers.hasKey("createdAt"))
                      .body("$", Matchers.hasKey("id"));
                break;
            default:
                given().body(request).
                when().post(URL+parameter).
                then().statusCode(404)
                      .body("$", Matchers.containsString("not found"));
                break;
        }
    }
    @Test(dataProvider = "CreateUser", dataProviderClass = CustomDataProvider.class)
    public void postCreateMultiple(String name, String job){
        JSONObject request = new JSONObject();
        request.put("name", name);
        request.put("job", job);

        given().body(request)
               .header("Content-Type", "application/json")
               .contentType(ContentType.JSON).
        when().post(URL_DDT).
        then().statusCode(201)
               .body("$", Matchers.hasKey("createdAt"))
               .body("$", Matchers.hasKey("id"));
    }
}
