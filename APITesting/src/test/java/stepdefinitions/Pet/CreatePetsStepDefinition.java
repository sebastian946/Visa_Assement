package stepdefinitions.Pet;

import com.github.javafaker.Faker;
import interfaces.CreationPets;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;
import services.PetServices;

import java.util.List;

public class CreatePetsStepDefinition {

    private Response response;
    private String ID;
    private CreationPets json = new CreationPets();
    private Faker faker = new Faker();
    private String category = json.getCategory(String.valueOf(faker.number().numberBetween(1, 4)));
    private String status = json.getStatus(String.valueOf(faker.number().numberBetween(1, 3)));

    @Step("the pet with ID {string} does not exist in the system")
    @Given("the pet with ID {string} does not exist in the system")
    public void the_pet_with_id_does_not_exist_in_the_system(String id) {
        this.ID = id;
    }

    @Step("a POST request is sent to the endpoint {string} with the following data")
    @When("a POST request is sent to the endpoint {string} with the following data")
    public void a_post_request_is_sent_to_the_endpoint_with_the_following_data(String endPoint, List<String> tags) {
        PetServices service = new PetServices();
        this.response = service.createPetService("POST",faker.name().name(),tags,this.ID,this.category,this.status,endPoint);
    }

    @Step("the response status code should be {int}")
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }


}