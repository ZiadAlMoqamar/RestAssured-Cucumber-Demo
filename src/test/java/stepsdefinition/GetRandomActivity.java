package stepsdefinition;

import constants.GlobalConstants;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.module.jsv.JsonSchemaValidator;
import static org.junit.Assert.*;
import utils.TestContext;

public class GetRandomActivity {
    private final TestContext context;
    private static final String Activity_Endpoint = GlobalConstants.Activity_Endpoint;
    private static final String RANDOM_ACTIVITY_RESPONSE_SCHEMA = GlobalConstants.RANDOM_ACTIVITY_RESPONSE_SCHEMA;

    public GetRandomActivity(TestContext context){
        this.context = context;
    }

    @When("user visits the website")
    public void userVisitsTheWebsite(){
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder().setBasePath(Activity_Endpoint);
        context.response = context.requestSetup(requestBuilder).get();
    }

    @Then("user will get random activity suggestion successfully")
    public void userWillGetRandomActivitySuggestionSuccessfully() {
        assertEquals(Long.valueOf(200), Long.valueOf(context.response.getStatusCode()));
    }

    @Then("user will be able to view the random activity suggestion details")
    public void userWillBeAbleToViewTheRandomActivitySuggestionDetails() {
        context.response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(RANDOM_ACTIVITY_RESPONSE_SCHEMA));
    }
}
