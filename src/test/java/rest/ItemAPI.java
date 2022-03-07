package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.Item;
import dto.SuccessResponse;
import io.restassured.response.Response;

public class ItemAPI {
    private static Gson gson = new GsonBuilder().create();
    private static final String ITEMS_URL = "/items";
    private static final String ITEM_URL = "/item";

    public static Response getAllItems(){
        return HTTPClient.get(ITEMS_URL);
    }

    public static Response getItem(int id){
        return HTTPClient.get(ITEM_URL + "/" + id);
    }

    public static Response createItem(Item item){
        return HTTPClient.post(ITEM_URL, gson.toJson(item));
    }

    public static Response deleteItem(int id){
        return HTTPClient.delete(ITEM_URL + "/" + id);
    }

    public static Response updateItem(int id, Item item){
       return HTTPClient.put(ITEM_URL + "/" + id, gson.toJson(item));
    }

    public static void main(String[] args) {
        Response getAllResp = getAllItems();
        Item itemDto = new Item("Beer2", "lt.", 5, "EUR");
        Response createItemResp = createItem(itemDto);
        String itemCreateString = createItemResp.getBody().asString();
        SuccessResponse successResponse = gson.fromJson(itemCreateString, SuccessResponse.class);
        int itemId = successResponse.getSuccess().getId();
        Response getItemResp = getItem(itemId);
        itemDto.setName("UpdatedBeer");
        itemDto.setCurrency("BGN");
        Response updateItemResp = updateItem(itemId, itemDto);
        //Response deleteItemResp = deleteItem(itemId);
    }
}
