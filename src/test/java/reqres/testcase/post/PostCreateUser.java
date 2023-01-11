package reqres.testcase.post;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reqres.dataprovider.CustomDataProvider;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class PostCreateUser {
    public String path;
    @BeforeMethod
    public void setUp(){
        String dir = System.getProperty("user.dir");
        path = dir+"/src/test/java/reqres/jsonschema/get";
    }
    @AfterMethod
    public void tearDown(){

    }
    @Test(dataProvider = "CreateParameter", dataProviderClass = CustomDataProvider.class)
    public void postCreateUser(String parameter){
        String url = "https://reqres.in/api/"+parameter;

        JSONObject json = new JSONObject();
        json.put("name", "dian");
        json.put("job", "software engineer in test");

        switch (parameter){
            case "users":
                given().body(json)
                        .when().post(url)
                        .then().statusCode(201)
                                .body("$", Matchers.hasKey("createdAt"))
                                .body("$", Matchers.hasKey("id"));
                break;
            default:
                given().body(json.toJSONString())
                        .when().post(url)
                        .then()
                        .statusCode(404)
                        .body("$", Matchers.containsString("not found"));
                break;
        }


    }
}
