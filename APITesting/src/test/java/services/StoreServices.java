package services;

import com.github.javafaker.Faker;
import interfaces.Store;
import io.cucumber.java.it.Ma;
import io.restassured.response.Response;
import utils.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StoreServices {
    private final String ENDPOINT_ORDER = "/store/order";
    private Map<String, String> params = new HashMap<>();
    private Map<String, Object> jsonBody = new HashMap<>();
    private Utils utils = new Utils();
    private Store json = new Store();
    private Faker faker = new Faker();

    public Response createOrder(String id, String petId){
        String[] options = {"approved", "placed","delivered"};
        Boolean[] complete = {true,false};
        Random random = new Random();
        String selected = options[random.nextInt(options.length)];
        Boolean complet = complete[random.nextInt(complete.length)];
        jsonBody = new HashMap<>(json.store(id,
                petId,Integer.toString(faker.number().numberBetween(1,20)),selected,complet));
        return utils.executePostRequest("POST","/store/order",jsonBody,params);
    }


    public Response getInventory(){
        return utils.executeGetRequest(params,"/store/inventory");
    }

    public Response getSpecificOrder(String id){
        return utils.executeGetRequest(params,ENDPOINT_ORDER+"/"+id);
    }

    public Response deleteOrder(String id){
        return utils.executeDelete(ENDPOINT_ORDER+"/"+id);
    }
}
