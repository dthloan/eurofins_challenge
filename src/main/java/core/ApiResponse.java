package core;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;

import java.io.File;

public class ApiResponse {

    private Response response;

    public ApiResponse(Response response) {
        this.response = response;
    }

    public JsonPath jsonPath() {
        return response.jsonPath().using(new JsonPathConfig(JsonPathConfig.NumberReturnType.BIG_DECIMAL));
    }

    public String getJsonResponse() {
        return response.getBody().prettyPrint();
    }

    public Integer getStatusCode() {
        return response.getStatusCode();
    }

}
