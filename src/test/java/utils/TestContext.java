package utils;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class TestContext {

    public Response response;
    private static final String CONTENT_TYPE = PropertiesFile.getProperty("content.type");

    public RequestSpecification requestSetup() {
        RestAssured.reset();
        RestAssuredConfig config = new RestAssuredConfig();
        RestAssured.baseURI = PropertiesFile.getProperty("baseURL");
        return RestAssured.given()
                .config(config)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .accept(CONTENT_TYPE);
    }
}
