package reqres.dataprovider;

import org.testng.annotations.DataProvider;

public class CustomDataProvider {
    @DataProvider(name = "ValidPage")
    public Object[][] validPage(){
        Object[][] data = {{2}, {1}, {200}};
        return data;
    }
    @DataProvider(name = "InvalidPage")
    public Object[][] invalidPage(){
        Object[][] data = {{"xxx"}, {"valid"}, {""}};
        return data;
    }
    @DataProvider(name = "UserId")
    public Object[][] validUserId(){
        Object[][] data = {{2}, {1}, {200}};
        return data;
    }
    @DataProvider(name = "InvalidUserId")
    public Object[][] invalidUserId(){
        Object[][] data = {{"xxx"}, {"ccc"}, {""}};
        return data;
    }
    @DataProvider(name = "CreateParameter")
    public Object[][] createUserParameter(){
        Object[][] data = {{"users"}, {"userzz"}, {"usher"}};
        return data;
    }
    @DataProvider(name = "CreateUser")
    public Object[][] postCreateUser(){
        Object[][] data = {{"dian", "software engineer in test"}, {"caca", "lecturer"}};
        return data;
    }
}
