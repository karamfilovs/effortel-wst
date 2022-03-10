package rest;

import dto.Item;
import io.restassured.response.Response;

public class ItemAPI extends HTTPClient {
    private static final String ITEM_URL = "/items";

    public ItemAPI(String baseUri, String basePath, String token) {
        super(baseUri, basePath, token);
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


}
