import dto.LoginCredentials;
import io.restassured.authentication.PreemptiveOAuth2HeaderScheme;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import rest.ItemAPI;
import rest.LoginAPI;


public class Oauth2Test {

    @Test
    @Tag("positive")
    @DisplayName("Can get all items v3")
    void canGetAllItemsV3() {
        //Testing scheme authentication with bearer token
        LoginCredentials loginToken = new LoginCredentials("karamfilovs@gmail.com", "123456", "st2016"); //Dto storing the creds
        LoginAPI loginAPI = new LoginAPI("https://api.inv.bg", "v3", ""); //Creating login api instance to be able to access getToken method
        String bearerToken = loginAPI.getToken(loginToken); //Getting the bearer token
        PreemptiveOAuth2HeaderScheme oAuth2Scheme = new PreemptiveOAuth2HeaderScheme(); //This was the problem last time (I was using Oauth2Scheme)
        oAuth2Scheme.setAccessToken(bearerToken); //Setting the token for the scheme
        ItemAPI itemAPIScheme = new ItemAPI("https://api.inv.bg", "v3", oAuth2Scheme); //This is new ItemAPI using scheme not token
        Response response = itemAPIScheme.getAllItems();
        Assertions.assertEquals(200, response.statusCode());
    }


}
