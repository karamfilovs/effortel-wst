import com.github.javafaker.Faker;
import core.BaseAPITest;
import dto.Item;
import dto.SuccessResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ItemAPITest extends BaseAPITest {

    @Test
    @DisplayName("Can create new item")
    @Tag("api")
    @Tag("positive")
    void canCreateNewItem(){
        Item coffee = Item.builder()
                .name("Coffee " + LocalDateTime.now())
                .price_for_quantity(10)
                .quantity_unit("kg.")
                .currency("EUR").build();
        Response response = api.itemAPI().createItem(coffee);
        Assertions.assertEquals(201, response.statusCode(), "Status code does not match");
    }

    @Test
    @DisplayName("Can get all items")
    @Tag("api")
    @Tag("positive")
    void canGetAllItems(){
        Response response = api.itemAPI().getAllItems();
        Assertions.assertEquals(200, response.statusCode(), "Status code does not match");
    }

    @Test
    @DisplayName("Can delete item")
    @Tag("api")
    @Tag("positive")
    void canDeleteITem(){
        Faker faker = new Faker();
        Item coffee = new Item(faker.beer().name(), "lt.", 5, "EUR", 5);
        Response response = api.itemAPI().createItem(coffee);
        Assertions.assertEquals(201, response.statusCode(), "Status code does not match");
        String respJson = response.getBody().asString();
        SuccessResponse success = GSON.fromJson(respJson, SuccessResponse.class);
        int itemId = Integer.parseInt(success.getId());
        Response deleteResp = api.itemAPI().deleteItem(itemId);
        Assertions.assertEquals(204, deleteResp.statusCode());
    }
}
