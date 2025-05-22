package interfaces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreationPets {

    public Map<String, Object> petJson(String id, String name, String categoryId, String categoryName,
                                       String[] photoUrls, List<Map<String, String>> tags, String status) {
        Map<String, Object> pet = new HashMap<>();
        pet.put("id", id);
        pet.put("name", name);
        pet.put("status", status);

        Map<String, String> category = new HashMap<>();
        category.put("id", categoryId);
        category.put("name", categoryName);

        pet.put("category", category);

        pet.put("photoUrls", photoUrls);

        pet.put("tags", tags);

        return pet;
    }

    public String getCategory(String id) {
        Map<String, String> category = new HashMap<>();
        category.put("1", "Dogs");
        category.put("2", "Cats");
        category.put("3", "Reptile");
        category.put("4", "Fish");
        return category.get(id);
    }

    public String getStatus(String id) {
        Map<String, String> status = new HashMap<>();
        status.put("1", "available");
        status.put("2", "pending");
        status.put("3", "sold");
        return status.get(id);
    }
}