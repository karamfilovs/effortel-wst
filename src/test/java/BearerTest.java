import com.jayway.jsonpath.JsonPath;
import core.BaseAPITest;
import dto.Item;


import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


public class BearerTest extends BaseAPITest {

    @Test
    @Tag("negative")
    @DisplayName("Can create item in version 3")
    void canCreateItem(){
        Item item = Item.builder()
                .name("ItemName2" + LocalDateTime.now())
                .price_for_quantity(10)
                .price(10)
                .quantity_unit("unit")
                .currency("EUR")
                .build();
        Response response = api.itemAPI().createItem(item);
        Assertions.assertEquals(201, response.statusCode());
        String responseJson = response.getBody().asString();
        Integer id = JsonPath.parse(responseJson).read("$.id");
    }

    @Test
    @DisplayName("Can create client in version 3")
    void canCreateClient(){

    }
}
