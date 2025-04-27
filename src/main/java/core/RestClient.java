package core;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Header;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.Method.*;

public class RestClient {

    private static final int TIME_OUT = 60000;
    private final String host;

    protected RestClient(String host) {
        this.host = host;
    }

    protected ApiResponse post(ApiHeader headers, String endPoint, Object payLoad) {
        List<Header> headerList = headers.headers;
        Map<String, String> specHeaders = new HashMap<>();
        for (Header h : headerList) {
            specHeaders.putIfAbsent(h.getName(), h.getValue());
        }
        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
        specBuilder.setBaseUri(host)
                .setBasePath(endPoint)
                .setConfig(configTimeout(TIME_OUT))
                .addHeaders(specHeaders)
                .setBody(payLoad);
        Response response = given().spec(specBuilder.build()).request(POST);
        return new ApiResponse(response);
    }
    private RestAssuredConfig configTimeout(int timeOut) {
        RestAssuredConfig config = RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig().
                setParam("http.connection.timeout", timeOut).
                setParam("http.socket.timeout", timeOut).
                setParam("http.connection-manager.timeout", timeOut));
        return config;
    }
}
