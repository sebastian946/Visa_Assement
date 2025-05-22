package stepdefinitions.User;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;
import services.UserServices;

public class UserStepDefinition {
    private Response response;
    private UserServices userServices = new UserServices();

    @Step("the manager creates a new user with {string} {string}")
    @When("the manager creates a new user with {string} {string}")
    public void createNewUser(String id, String userStatus) {
        response = userServices.createUser(id,userStatus);
    }

    @Step("the request response {int}")
    @Then("the request response {int}")
    public void validateStatusCode(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Step("the user was created")
    @Then("the user was created")
    public void validateUserCreation() {
        if(response.getStatusCode() == 200){
            Assert.assertNotNull(response.jsonPath().getString("id"));
        }else{
            Assert.assertNotNull(response.jsonPath().getString("message"));
        }
    }

    @Step("the manager upload a list {int} of users")
    @When("the manager upload a list {int} of users")
    public void uploadUserList(int size) {
        response = userServices.createWithList(size);
    }

    @Step("the list of new user was upload")
    @Then("the list of new user was upload")
    public void validateUserListUpload() {
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0);
    }


    @Step("the user send login request")
    @When("the user send login request")
    public void sendLoginRequest() {
        response = userServices.login();
    }

    @Step("the body must response the logged session")
    @Then("the body must response the logged session")
    public void validateLoginResponse() {
        Assert.assertTrue(response.getBody().asString().contains("Logged in user session:"));
    }

    @Step("the user send logout request")
    @When("the user send logout request")
    public void sendLogoutRequest() {
        response = userServices.logout();
    }

    @Step("the request must return a message {string}")
    @Then("the request must return a message {string}")
    public void validateLogoutMessage(String expectedMessage) {
        Assert.assertEquals(expectedMessage, response.getBody().asString());
    }

    @Step("the user search the {string}")
    @When("the user search the {string}")
    public void searchUser(String username) {
        response = userServices.getUser(username);
    }

    @Step("must return the user information")
    @Then("must return the user information")
    public void validateUserInformation() {
        if(response.getStatusCode() == 200){
            Assert.assertNotNull(response.jsonPath().getString("id"));
            Assert.assertNotNull(response.jsonPath().getString("username"));
        }else{
            Assert.assertNotNull(response.body().asString());
        }

    }

    @Step("the user delete the {string}")
    @When("the user delete the {string}")
    public void deleteUser(String username) {
        response = userServices.deleteUser(username);
    }
}

