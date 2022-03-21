package rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.authentication.AuthenticationScheme;

public class API {
    private ItemAPI itemAPI;
    private ClientAPI clientAPI;
    private BankAccountAPI bankAccountAPI;
    private String baseURI;
    private String basePath;
    private AuthenticationScheme scheme;

    public API(String baseURI, String basePath, AuthenticationScheme scheme) {
        this.baseURI = baseURI;
        this.basePath = basePath;
        this.scheme = scheme;
    }

    public ItemAPI itemAPI() {
        if (itemAPI == null) {
            itemAPI = new ItemAPI(baseURI, basePath, scheme);
        }
        return itemAPI;
    }

    public ClientAPI clientAPI() {
        if (clientAPI == null) {
            clientAPI = new ClientAPI(baseURI, basePath, scheme);
        }
        return clientAPI;
    }

    public BankAccountAPI bankAccountAPI() {
        if (bankAccountAPI == null){
            bankAccountAPI = new BankAccountAPI(baseURI, basePath, scheme);
        }
        return bankAccountAPI;
    }
}
