package dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class Item {
    //I will use Gson lib to serialize to json
    private String name;
    private String quantity_unit;
    private int price_for_quantity;
    private String currency;
    private double price;



    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Item item = new ItemBuilder().build();
        item.setName("Whiskey");
        item.setCurrency("EUR");
        item.setQuantity_unit("lt.");
        System.out.println(gson.toJson(item));
        String jsonString = "{\n" +
                "  \"name\": \"Coffee\",\n" +
                "  \"price_for_quantity\": 0\n" +
                "}";
        Item item2 = gson.fromJson(jsonString, Item.class);
        System.out.println(item2.getName());
    }
}