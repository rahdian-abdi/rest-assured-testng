package reqres.testcase.delete;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DeleteUser {
    public String PATH;
    public String URL;
    @BeforeMethod
    public void setUp(){
        String dir = System.getProperty("user.dir");
        PATH = dir+"/src/test/java/reqres/jsonschema/get";
        URL = "https://reqres.in/api/users/2";
    }
    @AfterMethod
    public void tearDown(){

    }
    @Test
    public void deleteUser(){
        given()
                .when().delete(URL)
                .then().statusCode(204);
    }
}
