package steps;

import _base.ApiBaseStep;
import actions.QuoteCreationActions;
import core.ApiResponse;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import org.testng.Assert;
import supports.enums.ERROR_MESSAGES;
import supports.models.QuoteCreationReqPayload;
import supports.models.QuoteCreationRes;
import supports.models.QuoteItem;

import java.util.List;
import java.util.Map;

public class QuoteCreationSteps extends ApiBaseStep implements En {
    public QuoteCreationSteps() {
        When("a customer {word} with a list of items as below", (String customer, DataTable dataTable) -> {
            customer = (customer.equals("null")) ? null : customer;
            QuoteCreationReqPayload reqPayload = new QuoteCreationReqPayload(customer);
            List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
            if (rows.size() > 0) {
                for (Map<String, String> columns : rows) {
                    float discountValue = (columns.get("discount") != null) ? Float.valueOf(columns.get("discount")) : 0.0f;
                    String itemName = (columns.get("item") != null) ? columns.get("item") : null;
                    Integer quantity = (columns.get("quantity") != null) ? Integer.valueOf(columns.get("quantity")) : null;
                    Float price = (columns.get("price") != null) ? Float.valueOf(columns.get("price")) : null;
                    QuoteItem item = new QuoteItem(itemName, quantity, price, discountValue);
                    reqPayload.addItems(item);
                }
            }
            save(QuoteCreationReqPayload.class, reqPayload);
        });


        When("I create a quote for that customer", () -> {
            QuoteCreationReqPayload reqPayload = load(QuoteCreationReqPayload.class);
            ApiResponse response = use(QuoteCreationActions.class).createQuote(reqPayload);
            save(ApiResponse.class, response);
        });

        When("the Api responds with statusCode {int}", (Integer expectedStatusCode) -> {
            ApiResponse response = load(ApiResponse.class);
            Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Status code was mismatched with expectation");
        });


        When("the Api responds with error message '{ERROR_MESSAGES}'", (ERROR_MESSAGES errorMessage) -> {
            ApiResponse response = load(ApiResponse.class);
            Assert.assertTrue(use(QuoteCreationActions.class).getErrorMessage(response).contains(errorMessage.msg), String.format("Mismatched error message: Expectation [%s] BUT Actual [%s]", errorMessage.msg, response.getJsonResponse()));
        });


        When("the quoteCreation Api responds with the correct details and total line price calculated correctly", () -> {
            QuoteCreationReqPayload reqPayload = load(QuoteCreationReqPayload.class);
            ApiResponse response = load(ApiResponse.class);
            QuoteCreationRes quoteResponse = response.jsonPath().getObject("quote", QuoteCreationRes.class);
            quoteResponse.compareTo(reqPayload);
            Assert.assertEquals(quoteResponse.totalPrice, reqPayload.expectedTotalPrice(), "Total price was mismatched");
            Assert.assertEquals(response.jsonPath().get("confirmation.message"), "Quote created successfully.", "Confirmation message was mismatched");
        });
    }
}
