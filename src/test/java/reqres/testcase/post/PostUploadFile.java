package reqres.testcase.post;

import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PostUploadFile {
    private String URL;
    private String PATH;
    private File file;
    @BeforeMethod
    public void setUp(){
        URL = "https://the-internet.herokuapp.com/upload";
        String DIR = System.getProperty("user.dir");
        PATH = DIR+"/src/test/resources/media/coda.ico";
        file = new File(PATH);
    }
    @Test
    public void uploadFileTest(){
        Response response = given().multiPart("file", file, "multipart/form-data").
                            when().post(URL).
                            then().extract().response();

        String body = response.asPrettyString();
        System.out.println(body);
    }
}
