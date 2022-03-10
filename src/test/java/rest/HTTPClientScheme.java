package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HTTPClientScheme {
    protected final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private RequestSpecification baseRequest() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all()
                .when();
    }

    public HTTPClientScheme(String baseUri, String basePath, AuthenticationScheme scheme) {
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = basePath;
        RestAssured.authentication = scheme;
    }

    protected Response get(String url) {
        return baseRequest().get(url)
                .prettyPeek();
    }

    protected Response post(String url, String body) {
        return baseRequest().body(body)
                .post(url)
                .prettyPeek();
    }

    protected Response delete(String url) {
        return baseRequest().delete(url)
                .prettyPeek();
    }

    protected Response put(String url, String body) {
        return baseRequest().body(body)
                .put(url)
                .prettyPeek();
    }
}
