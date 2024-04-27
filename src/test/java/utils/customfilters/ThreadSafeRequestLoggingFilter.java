package utils.customfilters;

import io.restassured.filter.FilterContext;
import io.restassured.filter.OrderedFilter;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadSafeRequestLoggingFilter implements OrderedFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadSafeRequestLoggingFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        String method = requestSpec.getMethod();
        String uri = requestSpec.getURI();
        Headers headers = requestSpec.getHeaders();
        String body = requestSpec.getBody();

        LOGGER.info("Sending request:\nMethod: {}\nURI: {}\nHeaders: {}\nBody: {}", method, uri, headers, body);

        return ctx.next(requestSpec, responseSpec);
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
