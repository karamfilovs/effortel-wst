package rest;

public class BankAccountAPI extends HTTPClient {
    //TODO: Homework
    //Docs: https://api.inv.bg/v3/swagger-ui/#/bank/postBankAccount

    public BankAccountAPI(String baseUri, String basePath, String token) {
        super(baseUri, basePath, token);
    }
}
