package actions;

import _base.ApiAction;
import core.ApiResponse;
import core.Authorization;
import core.BaseClient;
import io.restassured.path.json.exception.JsonPathException;
import org.testng.Assert;
import supports.constants.ENDPOINTS;
import supports.models.QuoteCreationReqPayload;

public class QuoteCreationActions extends ApiAction {

    public QuoteCreationActions(Authorization auth) {
        super(auth);
    }

    public ApiResponse createQuote(QuoteCreationReqPayload payload) {
        ApiResponse response = getRestClient(BaseClient.class).POST(ENDPOINTS.QUOTE_CREATION, payload.toString());
        System.out.printf(response.getJsonResponse());
        return response;
    }

    public String getErrorMessage(ApiResponse response) {
        try{
            return response.jsonPath().get("confirmation.message");
        }
        catch (JsonPathException ex){
            return response.getJsonResponse();
        }
    }
}
