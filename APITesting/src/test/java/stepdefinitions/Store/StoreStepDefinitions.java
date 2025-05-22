package stepdefinitions.Store;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import services.StoreServices;

import static org.junit.Assert.*;

public class StoreStepDefinitions {
    private int actualStatus;
    private Response response;
    private String orderId;
    private String petId;
    private StoreServices storeServices = new StoreServices();


    @Step("the user created a new order with {string} and {string}")
    @When("the user created a new order with {string} and {string}")
    public void the_user_created_a_new_order_with_and_is_request(String id, String petid) {
        try{
            this.orderId = id;
            this.petId = petid;
            response = storeServices.createOrder(this.orderId,this.petId);
            actualStatus = response.getStatusCode();
            System.out.println(actualStatus);
        }catch (Exception e){
            actualStatus = 400;
        }
    }

    @Step("the user receive a {int}")
    @Then("the user receive a {int}")
    public void the_user_receive_a(int expectedStatus) {
        assertEquals(expectedStatus, actualStatus);
    }

    @Step("the response body response is {string}")
    @Then("the response body response is {string}")
    public void the_response_body_response_is(String match) {
        boolean expectedMatch = Boolean.parseBoolean(match);
        if (expectedMatch) {
            assertNotNull(response.getBody());
        } else {
            assertTrue(response.getBody() == null ||
                    response.getBody().asString().contains("error") ||
                    !response.getBody().asString().contains(orderId));
        }
    }


    @Step("the user send request to get inventory")
    @When("the user send request to get inventory")
    public void the_user_send_request_to_get_inventory() {
        try{
            response = storeServices.getInventory();
            actualStatus = response.getStatusCode();
        }catch (Exception e){
            actualStatus = 400;
        }
    }

    @Step("the user get status {int}")
    @Then("the user get status {int}")
    public void the_user_get_status(int expectedStatus) {
        assertEquals(expectedStatus, actualStatus);
    }

    @Step("the response body with {string} {string}")
    @Then("the response body with {string} {string}")
    public void the_response_body_with(String status1, String status2) {
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.contains(status1));
        assertTrue(responseBody.contains(status2));
    }



    @Step("the user send request to get the order {string}")
    @When("the user send request to get the order {string}")
    public void the_user_send_request_to_get_the_order(String id) {
        try {
            response = storeServices.getSpecificOrder(id);
            actualStatus = response.getStatusCode();
        } catch (Exception e) {
            actualStatus = 400;
        }
    }


    @Step("the user get body response")
    @Then("the user get body response")
    public void the_user_get_body_response() {
        if (actualStatus == 200) {
            assertNotNull(response.getBody());
        }
    }


    @Step("the user send request to delete an order {string}")
    @When("the user send request to delete an order {string}")
    public void the_user_send_request_to_delete_an_order(String id) {
        try {
            response = storeServices.deleteOrder(id);
            actualStatus = response.getStatusCode();
        } catch (Exception e) {
            actualStatus = 400;
        }
    }

    @Step("the user receive status {int}")
    @Then("the user receive status {int}")
    public void the_user_receive_status(Integer expectedStatusCode) {
        assertEquals(expectedStatusCode.intValue(), actualStatus);
    }


}
