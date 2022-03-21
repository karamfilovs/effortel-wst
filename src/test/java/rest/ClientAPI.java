package rest;

import dto.Client;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.response.Response;

public class ClientAPI extends HTTPClientScheme {
    private static final String CLIENT_URL = "/clients";

    public ClientAPI(String baseUri, String basePath, AuthenticationScheme scheme) {
        super(baseUri, basePath, scheme);
    }


    public Response createClient(Client client){
        return post(CLIENT_URL, GSON.toJson(client));
    }

    public Response updateClient(String id, Client client){
        return put(CLIENT_URL + "/" + id, GSON.toJson(client));
    }

    public Response deleteClient(String id){
        return delete(CLIENT_URL + "/" + id);
    }

    public Response getClient(String id){
        return get(CLIENT_URL + "/" + id);
    }

    public Response getAllClients(){
        return get(CLIENT_URL);
    }
}
