package stepsdefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import static org.junit.Assert.*;
import utils.PropertiesFile;
import utils.TestContext;

public class GetRandomActivity {
    private TestContext context;
    private static final String Activity_Endpoint = PropertiesFile.getProperty("activityEndpoint");

    public GetRandomActivity(TestContext context){
        this.context = context;
    }

    @When("user visits the website")
    public void userVisitsTheWebsite(){
        context.response = context.requestSetup().when().get(Activity_Endpoint);
    }

    @Then("user will get random activity suggestion successfully")
    public void userWillGetRandomActivitySuggestionSuccessfully() {
        assertEquals(Long.valueOf(200), Long.valueOf(context.response.getStatusCode()));
    }

    @Then("user will be able to view the random activity suggestion details")
    public void userWillBeAbleToViewTheRandomActivitySuggestionDetails() {
        context.response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/randomActivity.json"));
    }
}
