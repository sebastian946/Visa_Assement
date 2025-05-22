package services;

import com.github.javafaker.Faker;
import interfaces.User;
import io.restassured.response.Response;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServices {
    private final String ENDPOINT = "/user";
    private Map<String, String> params = new HashMap<>();
    private Map<String, Object> jsonBody = new HashMap<>();
    private Utils utils = new Utils();
    private User json = new User();
    private Faker faker = new Faker();
    private final String USERNAME = utils.getUsernameUser();
    private final String PASSWORD = utils.getPasswordUser();

    public void userBody(String Id, String userStatus){
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String phone = faker.phoneNumber().cellPhone();
        jsonBody = new HashMap<>(json.user(Id,USERNAME,firstName,lastName,email,PASSWORD,phone,userStatus));
    }

    public Response createUser(String Id, String userStatus){
        userBody(Id,userStatus);
        return utils.executePostRequest("POST",ENDPOINT,jsonBody,params);
    }

    public Response createWithList(int size){
        List<Map<String,Object>> users = new ArrayList<>(size);
        for(int i=0; i<size;i++){
            int id = faker.number().numberBetween(1,50);
            int userStatus = faker.number().numberBetween(1,4);
            userBody(String.valueOf(id), String.valueOf(userStatus));
            users.add(jsonBody);
        }
        return utils.executePostRequest("POST",ENDPOINT+"/createWithList",users,params);
    }

    public Response login(){
        params.put("username",USERNAME);
        params.put("password",PASSWORD);
        return utils.executeGetRequest(params,ENDPOINT+"/login");
    }

    public Response logout(){
        return utils.executeGetRequest(params,ENDPOINT+"/logout");
    }

    public Response getUser(String username){
        Response res;
        if(username.equals("default")){
            res = utils.executeGetRequest(params,ENDPOINT+"/"+this.USERNAME);
        }else{
            res = utils.executeGetRequest(params,ENDPOINT+"/"+username);
        }
        return res;
    }

    public Response updateUser(String username, String Id, String userStatus){
        String user;
        if(username.equals("default")){
            user = this.USERNAME;
        }else{
            user = username;
        }
        userBody(Id,userStatus);
        return utils.executePostRequest("PUT",ENDPOINT+"/"+user,jsonBody,params);
    }

    public Response deleteUser(String username){
        String user;
        if(username.equals("default")){
            user = this.USERNAME;
        }else{
            user = username;
        }
        return utils.executeDelete(ENDPOINT+"/"+user);
    }
}
