package interfaces;

import java.util.HashMap;
import java.util.Map;

public class User {
    public Map<String, String> user(String id,String username,String firstName, String lastName, String email,
                                    String password, String phone, String userStatus){
        Map<String, String> storeJson = new HashMap<>();

        storeJson.put("id", String.valueOf(id));
        storeJson.put("username", username);
        storeJson.put("firstName", firstName);
        storeJson.put("lastName", lastName);
        storeJson.put("email",email);
        storeJson.put("password", password);
        storeJson.put("phone", phone);
        storeJson.put("userStatus", userStatus);

        return storeJson;
    }
}
