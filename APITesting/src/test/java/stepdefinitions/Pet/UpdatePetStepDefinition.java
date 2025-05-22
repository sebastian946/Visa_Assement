package stepdefinitions.Pet;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;
import services.PetServices;

import java.util.Arrays;
import java.util.List;

public class UpdatePetStepDefinition {

    private PetServices services = new PetServices();
    private Faker faker = new Faker();
    private Response response;
    private List<String> tags = Arrays.asList("black", "tall", "old");
    private String Id;
    private String endpoint = "/pet";

    @Step("the system create a Pet with ID {string}")
    @Given("the system create a Pet with ID {string}")
    public void step_void(String id) {
        Id = id;
        String name = faker.name().name();
        String category = String.valueOf(faker.number().numberBetween(1, 4));
        String status = String.valueOf(faker.number().numberBetween(1, 3));

        response = services.createPetService("POST", name, tags, Id, category, status, endpoint);

    }

    @Step("the system edits the {string} with the value {string} of the pet information")
    @When("the system edits the {string} with the value {string} of the pet information")
    public void the_system_edits_the_field_with_the_value_name_of_the_pet_information(String field, String value) {
        String name = faker.name().name();
        String category = String.valueOf(faker.number().numberBetween(1, 4));
        String status = String.valueOf(faker.number().numberBetween(1, 3));

        if (field.equalsIgnoreCase("name")) {
            response = services.createPetService("PUT", value, tags, Id, category, status, endpoint);
        } else if (field.equalsIgnoreCase("status")) {
            response = services.createPetService("PUT", name, tags, Id, category, value, endpoint);
        }

    }

    @Step("the system must respond with status code {int}")
    @Then("the system must respond with status code {int}")
    public void the_system_must_respond_with_status_code(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @Step("the response should contain the {string} with the {string}")
    @Then("the response should contain the {string} with the {string}")
    public void the_response_should_contain_the_message(String fields, String expectedValue) {
        Object actualValue = response.jsonPath().get(fields);
        Assert.assertNotNull( actualValue);

        String actualValueStr = actualValue.toString();
        Assert.assertEquals(expectedValue, actualValueStr);
    }
}