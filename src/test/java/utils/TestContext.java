package utils;
import constants.GlobalConstants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestContext {

    public Response response;
    private static final String BASE_URI = GlobalConstants.BASE_URI;

    public RequestSpecification requestSetup(RequestSpecBuilder requestBuilder) {
        RestAssured.reset();
        RestAssuredConfig config = new RestAssuredConfig();
        requestBuilder.setBaseUri(BASE_URI)
                .setConfig(config)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .setContentType(ContentType.JSON);
        return RestAssured
                .given()
                .spec(requestBuilder.build())
                .when();
    }
}
