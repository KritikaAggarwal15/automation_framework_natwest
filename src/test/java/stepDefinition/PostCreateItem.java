package stepDefinition;

import com.natWest.webService.customListeners.CustomListener;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.testng.Assert;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import static com.natWest.genericUtils.AssertionUtility.assertJsonIgnoringExtraFields;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class PostCreateItem extends CustomListener {
    Map<String, Object> requestJson;

    @Given("a user wants to create a new object with the following details at row {int}")
    public void aUserWantsToCreateANewObjectWithTheFollowingDetails(int index, DataTable table) {
        this.index = index;
        rowList = table.asMaps(String.class, String.class);
        Map<String, String> columns = rowList.get(index);
        requestJson = new HashMap<>();
        Map<String, Object> dataObject = new HashMap<>();

        for (Map.Entry<String, String> entry : columns.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (value != null && !value.isEmpty()) {
                if (!Objects.equals(key, "name")) {
                    dataObject.put(key, value);
                } else {
                    requestJson.put(key, value);
                }
            }
        }
        if (!dataObject.isEmpty()) {
            requestJson.put("data", dataObject);
        }
        requestSpecification = requestSpecification.body(requestJson);

    }

    @When("the user sends a POST request to create a new object")
    public void theUserSendsAPOSTRequestToCreateANewObject() {
        actualResponse = requestSpecification.log().all().post(getGetObjectEndpoint());
        actualResponse.prettyPrint();
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        Assert.assertEquals(actualResponse.statusCode(), expectedStatusCode);
    }

    @And("the JSON response data should match the data used for sending the request")
    public void theJSONResponseDataShouldMatchTheDataUsedForSendingTheRequest() {
        assertJsonIgnoringExtraFields(requestJson, actualResponse);
    }


    @And("the json response should generate {string} field with a value")
    public void theJsonResponseShouldGenerateAnIdFieldWithAValue(String expectedFieldName) {
        actualResponse.then().assertThat().body(expectedFieldName, notNullValue());
        if (Objects.equals(expectedFieldName, "id")) {
            ids.put(index, actualResponse.jsonPath().get(expectedFieldName));
        }
    }

    @And("{string} should be in date time format")
    public void shouldBeInDateTimeFormat(String expectedFieldName) {
        String dateTimePattern = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d+\\+\\d{2}:\\d{2}";
        String fieldValue = actualResponse.jsonPath().getString(expectedFieldName);
        boolean isDateTimeValid = Pattern.matches(dateTimePattern, fieldValue);
        assertThat(isDateTimeValid, is(true));
    }

    @And("verify that the item has been created with the same values provided in post request")
    public void verifyThatTheItemHasBeenCreatedOnToTheServer() {
        String endpoint = getGetObjectEndpoint()+ "/" + actualResponse.jsonPath().getString("id");
        actualResponse = null;
        actualResponse = requestSpecification.log().all().get(endpoint);
    }
}







