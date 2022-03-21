package rest;

import dto.BankAccount;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class BankAccountAPI extends HTTPClientScheme {
    private static final String BANK_ACCOUNT_URL = "/bank/accounts";
    public BankAccountAPI(String baseUri, String basePath, AuthenticationScheme scheme) {
        super(baseUri, basePath, scheme);
    }

    public Response getBankAccount(int id){
        return get(BANK_ACCOUNT_URL + "/" + id);
    }

    public Response getAllBankAccounts(){
        return get(BANK_ACCOUNT_URL);
    }

    public Response deleteBankAccount(int id){
        return delete(BANK_ACCOUNT_URL + "/" + id);
    }

    public Response createBankAccount(BankAccount bankAccount){
        return post(BANK_ACCOUNT_URL, GSON.toJson(bankAccount));
    }

    public Response updateBankAccount(int id, BankAccount bankAccount){
        return patch(BANK_ACCOUNT_URL + "/" + id, GSON.toJson(bankAccount));
    }

    //This is not supported by the inv.bg api version 3, it is just an example
    public Response updatePartiallyBankAccount(int id, Map<String, String> queryParams){
        return patch(BANK_ACCOUNT_URL + "/" + id, queryParams);
    }

}
