package rest;

import com.jayway.jsonpath.JsonPath;
import dto.LoginToken;
import io.restassured.response.Response;

public class LoginAPI extends HTTPClient {

    public LoginAPI(String baseUri, String basePath, String token) {
        super(baseUri, basePath, token);
    }

    public String getToken(LoginToken loginToken){
        Response response = post("/login/token", GSON.toJson(loginToken));
        String responseJson = response.getBody().asString();
        return JsonPath.parse(responseJson).read("$.token");
    }


}
