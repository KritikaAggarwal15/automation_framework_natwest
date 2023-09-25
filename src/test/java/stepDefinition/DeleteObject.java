package stepDefinition;

import com.natWest.webService.customListeners.CustomListener;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class DeleteObject extends CustomListener {

    String endPoint;

    @Given("an object with details")
    public void anObjectWithDetails() {
    }

    @And("a user wants to delete this object")
    public void aUserWantsToDeleteThisObject() {
    }

    @When("the user sends a {string} request to the server {int}")
    public void theUserSendsARequestToTheServer(String httpMethodType, int idx) {
        this.index=idx;
        endPoint = getGetObjectEndpoint() + "/" + ids.get(index);
        actualResponse = requestSpecification.log().all().request(httpMethodType.toUpperCase(), endPoint);
    }

    @And("the JSON response should contain {string} message")
    public void theJSONResponseShouldContainMessage(String message) {
        String expectedErrorMessage = "{\"message\":\"Object with id = " + ids.get(index) + " has been deleted.\"}";
        Assert.assertEquals(actualResponse.asString(), expectedErrorMessage);
    }

    @And("deleted object should have been deleted from the server")
    public void deletedObjectShouldBeVerifiedFromTheServerUsingGetRequest() {
        actualResponse = requestSpecification.log().all().get(endPoint);
        Assert.assertEquals(actualResponse.getStatusCode(), 404);
        String expectedErrorMessage = "{\"error\":\"Oject with id=" + ids.get(index) + " was not found.\"}";
        Assert.assertEquals(actualResponse.asString(), expectedErrorMessage);
    }
}
