package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class HTTPClient {
     private String token;
     protected final Gson GSON = new GsonBuilder()
             .setPrettyPrinting()
             .create();

     private RequestSpecification baseRequest(){
         return RestAssured.given()
                 .auth().oauth2(token)
                 .contentType(ContentType.JSON)
                 .accept(ContentType.JSON)
                 .log().all()
                 .when();
     }

    public HTTPClient (String baseUri, String basePath, String token){
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = basePath;
        this.token = token;
    }

    protected Response get(String url){
        return baseRequest().get(url)
                .prettyPeek();
    }

    protected Response post(String url, String body){
       return baseRequest().body(body)
               .post(url)
               .prettyPeek();
    }

    protected Response delete(String url){
        return baseRequest().delete(url)
                .prettyPeek();
    }

    protected Response put(String url, String body){
        return baseRequest().body(body)
                .put(url)
                .prettyPeek();
    }
}
