package services;

import com.github.javafaker.Faker;
import interfaces.CreationPets;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetServices {
    private Utils utils = new Utils();

    private final Faker faker = new Faker();
    private final CreationPets json = new CreationPets();

    public Response createPetService(String method, String name, List<String> tags, String id, String category, String status, String endpoint) {
        Map<String, Object> requestBody = buildPetRequestBody(id, name, category, tags, status);
        Map<String, String> params = new HashMap<>();
        return utils.executePostRequest(method, endpoint, requestBody, params);
    }

    public Response uploadPetInformation( String endpoint, Map<String, String> params) {
        Map<String, Object> body = new HashMap<>();
        return utils.executePostRequest("POST", endpoint,body, params);
    }

    public Response getPet(String typeSearch, String valueSearch) {
        String endpoint = buildGetPetEndpoint(typeSearch, valueSearch);
        return executeGetRequest(typeSearch, valueSearch, endpoint);
    }

    private Map<String, Object> buildPetRequestBody(String id, String name, String category, List<String> tags, String status) {
        List<Map<String, String>> tagList = buildTagList(tags);
        String[] photoUrls = {faker.internet().image(), faker.internet().image()};

        return json.petJson(
                id,
                name,
                String.valueOf(faker.number().numberBetween(1, 4)),
                json.getCategory(category),
                photoUrls,
                tagList,
                status
        );
    }

    private List<Map<String, String>> buildTagList(List<String> tags) {
        List<Map<String, String>> tagList = new ArrayList<>();
        for (String tag : tags) {
            Map<String, String> tagMap = new HashMap<>();
            tagMap.put("name", tag);
            tagList.add(tagMap);
        }
        return tagList;
    }


    private Response executeGetRequest(String typeSearch, String valueSearch, String endpoint) {
        Map<String, String> params = new HashMap<>();
        params.put(typeSearch,getParamValue(typeSearch,valueSearch));
        return utils.executeGetRequest(params,endpoint);

    }

    private String getParamValue(String typeSearch, String valueSearch) {
        switch (typeSearch) {
            case "status":
                return json.getStatus(valueSearch);
            case "tag":
                return json.getCategory(valueSearch);
            default:
                return valueSearch;
        }
    }

    private String buildGetPetEndpoint(String typeSearch, String valueSearch) {
        Map<String, String> endpointMap = new HashMap<>();
        endpointMap.put("status", "findByStatus");
        endpointMap.put("tag", "findByTags");
        endpointMap.put("id", valueSearch);

        return "/pet/" + endpointMap.getOrDefault(typeSearch, valueSearch);
    }


}