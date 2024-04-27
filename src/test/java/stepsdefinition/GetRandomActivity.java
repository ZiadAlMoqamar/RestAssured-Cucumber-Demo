package stepsdefinition;

import constants.GlobalConstants;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.module.jsv.JsonSchemaValidator;

import utils.TestContext;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        context.setResponse(context.requestSetup(requestBuilder).get());
        Date startTime = new Date();
        long threadId = Thread.currentThread().getId();
        System.out.println("Thread ID: " + threadId + ", Start Time: " + startTime);
    }

    @Then("user will get random activity suggestion successfully")
    public void userWillGetRandomActivitySuggestionSuccessfully() {
        assertEquals(Long.valueOf(200), Long.valueOf(context.getResponse().getStatusCode()));
    }

    @Then("user will be able to view the random activity suggestion details")
    public void userWillBeAbleToViewTheRandomActivitySuggestionDetails() {
        context.getResponse().then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(RANDOM_ACTIVITY_RESPONSE_SCHEMA));
    }
}
