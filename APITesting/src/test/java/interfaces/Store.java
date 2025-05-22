package interfaces;

import java.util.HashMap;
import java.util.Map;

public class Store {

    public Map<String, String> store(String id, String petId, String quantity, String status, Boolean complete){
        Map<String, String> storeJson = new HashMap<>();

        storeJson.put("id",id);
        storeJson.put("petId", petId);
        storeJson.put("quantity", quantity);
        storeJson.put("shipDate", "2025-05-21T04:45:49.667Z");
        storeJson.put("status",status);
        storeJson.put("complete", String.valueOf(true));

        return storeJson;
    }
}
