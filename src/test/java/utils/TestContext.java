package utils;
import constants.GlobalConstants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.customfilters.ThreadSafeRequestLoggingFilter;
import utils.customfilters.ThreadSafeResponseLoggingFilter;

public class TestContext {

    private final ThreadLocal<Response> response = new ThreadLocal<>();
    private static final String BASE_URI = GlobalConstants.BASE_URI;

    public Response getResponse() {
        return response.get();
    }

    public void setResponse(Response response) {
        this.response.set(response);
    }
    public RequestSpecification requestSetup(RequestSpecBuilder requestBuilder) {
        RestAssured.reset();
        RestAssuredConfig config = new RestAssuredConfig();
        requestBuilder.setBaseUri(BASE_URI)
                .setConfig(config)
                .addFilter(new ThreadSafeRequestLoggingFilter())
                .addFilter(new ThreadSafeResponseLoggingFilter())
                .setContentType(ContentType.JSON);
        return RestAssured
                .given()
                .spec(requestBuilder.build())
                .when();
    }
}
