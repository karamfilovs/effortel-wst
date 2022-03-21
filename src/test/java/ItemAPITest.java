import core.BaseAPITest;
import dto.Item;
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
}
