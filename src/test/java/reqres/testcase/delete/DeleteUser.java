package reqres.testcase.delete;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DeleteUser {
    public String path;
    @BeforeMethod
    public void setUp(){
        String dir = System.getProperty("user.dir");
        path = dir+"/src/test/java/reqres/jsonschema/get";
    }
    @AfterMethod
    public void tearDown(){

    }
    @Test
    public void deleteUser(){
        String url = "https://reqres.in/api/users/2";
        given().delete(url)
                .then()
                .statusCode(204);
    }
}
