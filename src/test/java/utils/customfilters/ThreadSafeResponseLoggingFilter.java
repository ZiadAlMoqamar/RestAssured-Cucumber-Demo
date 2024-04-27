package utils.customfilters;

import io.restassured.filter.FilterContext;
import io.restassured.filter.OrderedFilter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadSafeResponseLoggingFilter implements OrderedFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadSafeResponseLoggingFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);
        LOGGER.info("Received response:\nStatus code: {}\nHeaders: {}\nBody: {}", response.getStatusCode(), response.getHeaders(), response.getBody().asString());
        return response;
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
