package core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.LoginCredentials;
import io.restassured.authentication.PreemptiveOAuth2HeaderScheme;
import org.junit.jupiter.api.BeforeAll;
import rest.API;
import rest.LoginAPI;

public class BaseAPITest {
    private static final String EMAIL = System.getProperty("email", "karamfilovs@gmail.com");
    private static final String PASSWORD = System.getProperty("password", "123456");
    private static final String DOMAIN = System.getProperty("domain", "st2016");
    private static final String BASE_URI = System.getProperty("baseUri", "https://api.inv.bg");
    private static final String BASE_PATH = System.getProperty("basePath", "v3");
    protected static API api;
    protected static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @BeforeAll
    static void beforeAll() {
        LoginAPI loginAPI = new LoginAPI(BASE_URI, BASE_PATH, "");
        LoginCredentials credentials = new LoginCredentials(EMAIL, PASSWORD, DOMAIN);
        String bearerToken = loginAPI.getToken(credentials);
        PreemptiveOAuth2HeaderScheme scheme = new PreemptiveOAuth2HeaderScheme();
        scheme.setAccessToken(bearerToken);
        api = new API(BASE_URI, BASE_PATH, scheme);
    }
}
