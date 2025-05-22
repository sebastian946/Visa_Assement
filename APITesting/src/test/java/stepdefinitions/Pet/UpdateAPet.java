package stepdefinitions.Pet;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;
import services.PetServices;

import java.util.HashMap;
import java.util.Map;

public class UpdateAPet {
    private PetServices service = new PetServices();
    private Response response;

    @Step("the system update the information {string} {string} {string}")
    @When("the system update the information {string} {string} {string}")
    public void the_system_update_the_information(String ID, String name, String status){
        Map<String, String> params = new HashMap<>();
        params.put("name",name);
        params.put("status",status);
        String endpoint = "/pet/"+ID;
        response = service.uploadPetInformation(endpoint,params);
    }

    @Step("the status code should be {int}")
    @Then("the status code should be {int}")
    public void the_status_code_should_be(int statusCode){
        Assert.assertEquals(statusCode,response.getStatusCode());
    }
}
