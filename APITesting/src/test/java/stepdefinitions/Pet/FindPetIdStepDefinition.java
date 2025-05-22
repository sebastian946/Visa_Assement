package stepdefinitions.Pet;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;
import services.PetServices;

public class FindPetIdStepDefinition {
    private PetServices service = new PetServices();
    private Response response;

    @Step("the system searches for pets with ID {string}")
    @When("the system searches for pets with ID {string}")
    public void the_system_searches_for_pets_with_ID(String ID){
        response = service.getPet("id",ID);
    }

    @Step("the response status code should {int}")
    @Then("the response status code should {int}")
    public void the_response_status_code_should(int statusCode){
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }
}
