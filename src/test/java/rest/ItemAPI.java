package rest;

import dto.Item;
import dto.LoginCredentials;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.PreemptiveOAuth2HeaderScheme;
import io.restassured.response.Response;

public class ItemAPI extends HTTPClientScheme {
    private static final String ITEM_URL = "/items";

    public ItemAPI(String baseUri, String basePath, AuthenticationScheme scheme) {
        super(baseUri, basePath, scheme);
    }

    public Response getAllItems() {
        return get(ITEM_URL);
    }

    public Response getItem(int id) {
        return get(ITEM_URL + "/" + id);
    }

    public Response createItem(Item item) {
        return post(ITEM_URL, GSON.toJson(item));
    }

    public Response deleteItem(int id) {
        return delete(ITEM_URL + "/" + id);
    }

    public Response updateItem(int id, Item item) {
        return put(ITEM_URL + "/" + id, GSON.toJson(item));
    }

    public static void main(String[] args) {
        //Testing scheme authentication with bearer token
        LoginCredentials loginToken = new LoginCredentials("karamfilovs@gmail.com", "123456", "st2016");
        LoginAPI loginAPI = new LoginAPI("https://api.inv.bg", "v3", "");
        String bearerToken = loginAPI.getToken(loginToken);
        PreemptiveOAuth2HeaderScheme oAuth2Scheme = new PreemptiveOAuth2HeaderScheme();
        oAuth2Scheme.setAccessToken(bearerToken);
        ItemAPI itemAPIScheme = new ItemAPI("https://api.inv.bg", "v3", oAuth2Scheme);
        itemAPIScheme.getAllItems();
    }
}
