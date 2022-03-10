import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import rest.ItemAPIScheme;

public class BasicAuthTest {
    @Test
    @Tag("positive")
    @DisplayName("Can get all items v2")
    void canGetAllItemsV2(){
        //Testing scheme authentication with basic auth
        PreemptiveBasicAuthScheme basicAuthScheme = new PreemptiveBasicAuthScheme(); //Basic auth scheme
        basicAuthScheme.setUserName("karamfilovs@gmail.com"); //Setting email
        basicAuthScheme.setPassword("123456"); //Setting password
        ItemAPIScheme itemAPIScheme = new ItemAPIScheme("https://st2016.inv.bg", "RESTapi", basicAuthScheme); //Instance for v2 of the api
        Response response = itemAPIScheme.getAllItems(); //Get all items
        Assertions.assertEquals(200, response.statusCode()); //Check the response status code
    }
}
