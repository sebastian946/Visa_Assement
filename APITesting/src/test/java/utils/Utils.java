package utils;

import io.cucumber.java.BeforeAll;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String CONTENT_TYPE = "application/json";
    private static final String BASEURL = dotenv.get("ENDPOINT");
    private static Map<String, String> header = new HashMap<>();

    @BeforeAll
    public void setup(){
        RestAssured.filters(new AllureRestAssured());
    }

    public String getBaseurl(){
        return BASEURL;
    }
    public String getUsernameUser(){
        return dotenv.get("USERNAME_USER");
    }
    public String getPasswordUser(){
        return dotenv.get("PASSWORD_USER");
    }
    public final Map<String, String> Headers(){
        header.put("Content-Type", CONTENT_TYPE);
        header.put("accept","application/json");
        return header;
    }

    public Response executePostRequest(String method, String endpoint, Map<String, Object> body, Map<String, String> params) {
        if(params.isEmpty()){
            return RestAssured.given()
                    .headers(Headers())
                    .baseUri(BASEURL)
                    .contentType(CONTENT_TYPE)
                    .queryParams(params)
                    .body(body)
                    .when()
                    .request(method, endpoint);
        }
        return RestAssured.given()
                .headers(Headers())
                .baseUri(BASEURL)
                .contentType(CONTENT_TYPE)
                .queryParams(params)
                .body(body)
                .when()
                .request(method, endpoint);
    }
    public Response executePostRequest(String method, String endpoint, List<Map<String, Object>> body, Map<String, String> params) {
        if(params.isEmpty()){
            return RestAssured.given()
                    .headers(Headers())
                    .baseUri(BASEURL)
                    .contentType(CONTENT_TYPE)
                    .queryParams(params)
                    .body(body)
                    .when()
                    .request(method, endpoint);
        }
        return RestAssured.given()
                .headers(Headers())
                .baseUri(BASEURL)
                .contentType(CONTENT_TYPE)
                .queryParams(params)
                .body(body)
                .when()
                .request(method, endpoint);
    }
    public Response executeGetRequest(Map<String, String> params,String endpoint) {

        if(params.isEmpty()){
            return RestAssured.given()
                    .baseUri(BASEURL)
                    .headers(Headers())
                    .contentType(CONTENT_TYPE)
                    .when()
                    .get(endpoint);
        }else{
            return RestAssured.given()
                    .baseUri(BASEURL)
                    .headers(Headers())
                    .contentType(CONTENT_TYPE)
                    .queryParam(params.toString())
                    .when()
                    .get(endpoint);
        }

    }
    public Response executeDelete(String endpoint){
        return RestAssured.given()
                .baseUri(BASEURL)
                .headers(Headers())
                .contentType(CONTENT_TYPE)
                .when()
                .delete(endpoint);
    }
}
