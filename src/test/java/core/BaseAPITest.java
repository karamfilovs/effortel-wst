package core;

import dto.LoginToken;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import rest.API;
import rest.LoginAPI;

public class BaseAPITest {
    private static final String EMAIL = System.getProperty("email","karamfilovs@gmail.com");
    private static final String PASSWORD = System.getProperty("password", "123456");
    private static final String DOMAIN = System.getProperty("domain", "st2016");
    private static final String BASE_URI = System.getProperty("baseUri", "https://api.inv.bg");
    private static final String BASE_PATH = System.getProperty("basePath","v3");
    protected  static API api;

    @BeforeAll
    static void beforeAll(){
        //Obtain token
        LoginAPI loginAPI = new LoginAPI(BASE_URI, BASE_PATH, "");
        LoginToken loginToken = new LoginToken(EMAIL, PASSWORD, DOMAIN);
        String bearerToken  = loginAPI.getToken(loginToken);
        //Configure all API classes
        //Provide access for all tests to the api classes
        api = new API(BASE_URI, BASE_PATH, bearerToken);
    }
}
