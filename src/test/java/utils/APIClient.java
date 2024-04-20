package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Payload;
import org.apache.logging.log4j.Logger;
import properties.HttpMethods;


/**
 * API execution class uses Rest Assured to make different http calls
 */
public class APIClient {
    private static final Logger logger = TestLogger.getLogger();
    Response response;

    public Response getResponse(Payload payload) {

        RequestSpecification requestSpecification = RestAssured.given();
        if (payload.getHeaders() != null) {
            requestSpecification.headers(payload.getHeaders());
        }
        if (payload.getPathParams() != null) {
            requestSpecification.pathParams(payload.getPathParams());
        }
        if (payload.getQueryParams() != null) {
            requestSpecification.queryParams(payload.getQueryParams());
        }
        if (payload.getBody() != null && (payload.getMethods() != HttpMethods.GET || payload.getMethods() != HttpMethods.DELETE)) {
            requestSpecification.body(payload.getBody());
        }
        switch (payload.getMethods()) {
            case GET:
                response = requestSpecification.log().all().get(payload.getEndpoint());
                break;
            case PUT:
                response = requestSpecification.log().all().put(payload.getEndpoint());
                break;
            case POST:
                response = requestSpecification.log().all().post(payload.getEndpoint());
                break;
            case DELETE:
                response = requestSpecification.log().all().delete(payload.getEndpoint());
                break;
            default:
                System.out.printf("Http-Method %s not supported yet%n", payload.getMethods().toString());
        }
        logger.info(response.getBody().asString());
        return response;
    }
}
