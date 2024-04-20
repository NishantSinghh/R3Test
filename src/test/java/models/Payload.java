package models;

import properties.HttpMethods;

import java.util.Map;

/**
 * POJO to create request payloads
 */
public class Payload {
    private Map<String, String> headers;
    private Map<String, String> pathParams;
    private Map<String, String> queryParams;
    private Map<String, Object> body;
    private HttpMethods methods;
    private String endpoint;

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getPathParams() {
        return pathParams;
    }

    public void setPathParams(Map<String, String> pathParams) {
        this.pathParams = pathParams;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String toString() {
        return "Payload{\n" +
                "headers=" + headers +
                ",\n pathParams=" + pathParams +
                ",\n queryParams=" + queryParams +
                ",\n body=" + body +
                ",\n methods=" + methods +
                ",\n endpoint='" + endpoint + '\'' +
                '}';
    }

    public HttpMethods getMethods() {
        return methods;
    }

    public void setMethods(HttpMethods methods) {
        this.methods = methods;
    }

}
