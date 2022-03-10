package rest;

public class API {
    private ItemAPI itemAPI;
    private ClientAPI clientAPI;
    private BankAccountAPI bankAccountAPI;
    private String baseURI;
    private String basePath;
    private String token;

    public API(String baseURI, String basePath, String token) {
        this.baseURI = baseURI;
        this.basePath = basePath;
        this.token = token;

    }

    public ItemAPI itemAPI() {
        if (itemAPI == null) {
            itemAPI = new ItemAPI(baseURI, basePath, token);
        }
        return itemAPI;
    }

    public ClientAPI clientAPI() {
        if (clientAPI == null) {
            clientAPI = new ClientAPI(baseURI, basePath, token);
        }
        return clientAPI;
    }

    //TODO: Homework
}
