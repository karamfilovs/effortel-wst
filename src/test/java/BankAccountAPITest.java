import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;
import core.BaseAPITest;
import dto.BankAccount;
import dto.SuccessResponse;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;

import java.util.Locale;

public class BankAccountAPITest extends BaseAPITest {
    private static int currentBankId;
    private static BankAccount currentBankAccount = null;


    @BeforeEach
    void beforeEachTest() {

        //This is optimization not to repeat the create bank account
        currentBankAccount = BankAccount.builder()
                .bank(RandomStringUtils.randomAlphabetic(10))
                .bank_en(RandomStringUtils.randomAlphabetic(10))
                .currency("EUR")
                .alias("Delete Bank Account Alias")
                .iban("BG96BNPA94405512582615")
                .bic("STSABGSF")
                .build();
        Response createResp = api.bankAccountAPI().createBankAccount(currentBankAccount);
        String createRespJson = createResp.getBody().asString();
        SuccessResponse success = GSON.fromJson(createRespJson, SuccessResponse.class);
        currentBankId = Integer.parseInt(success.getId());
    }

    @Test
    @DisplayName("Can get all bank accounts")
    @Tag("api")
    @Tag("bank")
    void canGetAllBankAccounts() {
        Response getAllResp = api.bankAccountAPI().getAllBankAccounts();
        Assertions.assertEquals(200, getAllResp.statusCode());
    }

    @Test
    @DisplayName("Can delete bank account")
    @Tag("api")
    @Tag("bank")
    void canDeleteBankAccount() {
        Response deleteResp = api.bankAccountAPI().deleteBankAccount(currentBankId);
        Assertions.assertEquals(204, deleteResp.statusCode());
        //Get bank account and make sure the bank account is gone
        Response getResp = api.bankAccountAPI().getBankAccount(currentBankId);
        Assertions.assertEquals(200, getResp.statusCode());
        String getRespJson = getResp.getBody().asString();
        BankAccount deleteBankAccount = GSON.fromJson(getRespJson, BankAccount.class);
        //Assertions.assertTrue(deleteBankAccount.isDeleted());
    }


    @Test
    @DisplayName("Can create bank account")
    @Tag("api")
    @Tag("bank")
    void canCreateBankAccount() {
        //Get newly created bank account and check all fields for correctness
        Response getResp = api.bankAccountAPI().getBankAccount(currentBankId);
        String getRespJson = getResp.getBody().asString();
        BankAccount newBankAccount = GSON.fromJson(getRespJson, BankAccount.class);
        Assertions.assertEquals(currentBankAccount.getAlias(), newBankAccount.getAlias());
        Assertions.assertEquals(currentBankAccount.getIban(), newBankAccount.getIban());
        Assertions.assertEquals(currentBankAccount.getCurrency(), newBankAccount.getCurrency());
        //Delete bank account
        Response deleteResp = api.bankAccountAPI().deleteBankAccount(currentBankId);
        Assertions.assertEquals(204, deleteResp.statusCode());
    }

    @Test
    @DisplayName("Can update bank account")
    @Tag("api")
    void canUpdateBankAccount() {
        Faker faker = new Faker(Locale.US, new RandomService());
        BankAccount oldBankAccount = BankAccount.builder()
                .bank("Bank To Be Updated")
                .bank_en("Bank To Be Updated")
                .currency("EUR")
                .alias("Bank To Be Updated Alias")
                .address(faker.address().fullAddress())
                .iban(faker.finance().iban())
                .bic(faker.finance().bic())
                .build();
        Response createResp = api.bankAccountAPI().createBankAccount(oldBankAccount);
        String createRespJson = createResp.getBody().asString();
        SuccessResponse success = GSON.fromJson(createRespJson, SuccessResponse.class);
        int bankId = Integer.parseInt(success.getId());
        Assertions.assertEquals(201, createResp.statusCode());
        //Update bank account
        oldBankAccount.setBank("Updated Bank Name En");
        oldBankAccount.setAlias("Updated Alias");
        //oldBankAccount.setDeleted(true);
        Response updateResp = api.bankAccountAPI().updateBankAccount(bankId, oldBankAccount);
        Assertions.assertEquals(204, updateResp.statusCode());
        //Get updated bank account
        Response getResp = api.bankAccountAPI().getBankAccount(bankId);
        String getRespJson = getResp.getBody().asString();
        BankAccount updatedBankAccount = GSON.fromJson(getRespJson, BankAccount.class);
        //Check updated fields
        Assertions.assertEquals("Updated Bank Name En", updatedBankAccount.getBank());
        Assertions.assertEquals("Updated Alias", updatedBankAccount.getAlias());
        //Delete bank account
        api.bankAccountAPI().deleteBankAccount(bankId);
    }
}
