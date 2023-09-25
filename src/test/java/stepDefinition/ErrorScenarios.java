package stepDefinition;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.natWest.webService.customListeners.CustomListener;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


public class ErrorScenarios extends CustomListener {
    String filePath;
    String endPoint;
    String id;

    @Given("the {string} Id {string}")
    public void theId(String condition, String id) {

    }

    @When("a user calls {string} service to {string} Object with {string}")
    public void aUserCallsServiceToObject(String httpMethodType, String arg1, String id) {
        this.id = id;
        endPoint = getGetObjectEndpoint() + "/" + id;
        actualResponse = requestSpecification.log().all().request(httpMethodType.toUpperCase(), endPoint);
    }

    @And("the response should contain error message {string}")
    public void theResponseShouldContainErrorMessage(String message) {
        actualResponse = requestSpecification.log().all().get(endPoint);
        String expectedErrorMessage = "{\"error\":\"Oject with id=" + id + " was not found.\"}";
        Assert.assertEquals(actualResponse.asString(), expectedErrorMessage);
    }

    @Given("an invalid JSON having {string}")
    public void anInvalidJSON(String condition) {
        filePath = "src/test/resources/jsonRequest/" + condition.replaceAll("\\s+", "") + ".json";
    }

    @And("a user wants to create a new object with invalid JSON")
    public void aUserWantsToCreateANewObjectWithInvalidJSONWhichHas() {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
            ObjectMapper objectMapper = new ObjectMapper();
            Object parsedJsonObject = objectMapper.readValue(jsonContent, Object.class);
            requestSpecification.body(parsedJsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @And("other field value should be null on sending invalid JSON payload")
    public void otherFieldValueShouldBeNullOnSendingInvalidJSONPayload(DataTable expectedFields) {
        List<String> fieldNames = expectedFields.asList(String.class);
        Map<String, String> jsonObject = actualResponse.jsonPath().getMap("");
        for (String fieldName : fieldNames) {
            Assert.assertNull(jsonObject.get(fieldName), "Field '" + fieldName + "' has non null value in the JSON object.");
        }
    }

    @And("JSON objects should have the following Fields:")
    public void jsonObjectsShouldHaveTheFollowingFields(DataTable expectedFields) {
        List<String> fieldNames = expectedFields.asList(String.class);
        Map<String, String> jsonObject = actualResponse.jsonPath().getMap("");

        for (String fieldName : fieldNames) {
            Assert.assertTrue(jsonObject.containsKey(fieldName), "Field '" + fieldName + "' is missing in the JSON object.");
        }
    }

    @And("{string} field value should not be blank for JSON Object")
    public void fieldValueShouldNotBeBlankForJSONObject(String field) {
        Assert.assertNotNull(field);
    }
}
