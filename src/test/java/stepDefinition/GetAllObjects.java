package stepDefinition;

import com.natWest.webService.customListeners.CustomListener;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class GetAllObjects extends CustomListener {
    @Given("a user wants to retrieve all available objects")
    public void userWantsToRetrieveAllTheAvailableObjects() {
    }

    @When("a user sends a GET request to retrieve objects")
    public void aUserSendsAGETRequest() {
        actualResponse = requestSpecification.log().all().get(getGetObjectEndpoint());
    }

    @And("the response should contain a list of JSON objects")
    public void theResponseShouldContainAListOfObjects() {
        actualResponse.then().assertThat().body("", hasSize(greaterThan(0)));
    }

    @And("each JSON objects should have the following Fields:")
    public void allJSONObjectsShouldHaveTheFollowingFields(DataTable expectedFields) {
        List<String> fieldNames = expectedFields.asList(String.class);
        List<Map<String, String>> jsonResponseList = actualResponse.jsonPath().getList("$");
        for (Map<String, String> jsonObject : jsonResponseList) {
            for (String fieldName : fieldNames) {
                Assert.assertTrue(jsonObject.containsKey(fieldName), "Field '" + fieldName + "' is missing in a JSON object.");
            }
        }
    }

    @And("{string} field value should not be blank for each JSON Object")
    public void FieldValueShouldNotBeBlankForEachJSONObject(String field) {
        actualResponse.then().assertThat()
                .body(field, everyItem(notNullValue()));
    }
}
