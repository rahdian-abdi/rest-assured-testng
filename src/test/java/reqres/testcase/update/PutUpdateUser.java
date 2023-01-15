package reqres.testcase.update;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reqres.dataprovider.CustomDataProvider;

import static io.restassured.RestAssured.*;

public class PutUpdateUser {
    private String path;
    @BeforeMethod
    public void setUp(){
        String dir = System.getProperty("user.dir");
        path = dir+"/src/test/java/reqres/jsonschema/get";

    }
    @AfterMethod
    public void tearDown(){

    }
    @Test(dataProvider = "UserId", dataProviderClass = CustomDataProvider.class)
    public void updateUser(int user){
        String url = "https://reqres.in/api/users/"+user;

        JSONObject request = new JSONObject();
        request.put("name", "afina");
        request.put("job", "lecturer");

        given().body(request).
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
        when().put(url).
        then().statusCode(200)
              .body("$", Matchers.hasKey("updatedAt"));
    }
}
