import core.BaseAPITest;
import dto.Client;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

@Tag("client-api")
public class ClientAPITest extends BaseAPITest {

    @Test
    @Tag("only")
    @Tag("smoke")
    @Tag("positive")
    @DisplayName("Can create client")
    void canCreateClient() {
        Client client = Client.builder()
                .name("Happy" + LocalDateTime.now())
                .address("Sofia")
                .town("Sofia")
                .mol("Orlin H")
                .bulstat("102120120")
                .is_reg_vat(false)
                .build();
        Response response = api.clientAPI().createClient(client);
        Assertions.assertEquals(201, response.statusCode());
    }
}
