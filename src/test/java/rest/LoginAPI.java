package rest;

import com.jayway.jsonpath.JsonPath;
import dto.LoginCredentials;
import io.restassured.response.Response;

public class LoginAPI extends HTTPClient {

    public LoginAPI(String baseUri, String basePath, String token) {
        super(baseUri, basePath, token);
    }

    /**
     * Retrieve bearer token for specified user credentials
     * @param loginToken user credentials dto
     * @return token as a string
     */
    public String getToken(LoginCredentials loginToken) {
        Response response = post("/login/token", GSON.toJson(loginToken));
        String responseJson = response.getBody().asString();
        return JsonPath.parse(responseJson).read("$.token");
    }


}
