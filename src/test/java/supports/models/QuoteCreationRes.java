package supports.models;

import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class QuoteCreationRes {
    public String id;
    public String customer;
    public Integer revision;
    public Float totalPrice;
    public List<QuoteItem> lines = new ArrayList<>();
    public Integer status;

    public void compareTo(QuoteCreationReqPayload reqPayload) throws Exception {
        if (!this.customer.equals(reqPayload.getCustomer())) {
            throw new Exception(String.format("Customer name was mismatched. Expectation [%f] - Actual [%f]", reqPayload.getCustomer(), this.customer));
        } else if (!this.totalPrice.equals(reqPayload.expectedTotalPrice().floatValue())) {
            throw new Exception(String.format("Total Price was mismatched. Expectation [%f] - Actual [%f]", reqPayload.expectedTotalPrice(), this.totalPrice));
        } else if (this.lines.size() != reqPayload.getItems().size()) {
            throw new Exception(String.format("The item size was mismatched. Expectation [%f] - Actual [%f]", reqPayload.getItems().size(), this.lines.size()));
        } else if (this.lines.size() > 0) {
            for (QuoteItem line : lines) {
                QuoteItem reqItem = reqPayload.getItemByName(line.getItemName());
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(line.getItemName(), reqItem.getItemName(), "Item's name was mismatched");
                softAssert.assertEquals(line.getQuantity(), reqItem.getQuantity(), "Item's quantity was mismatched");
                softAssert.assertEquals(line.getUnitaryPrice(), reqItem.getUnitaryPrice(), "Item's unitary price was mismatched");
                softAssert.assertEquals(line.getDiscountPercentage(), reqItem.getDiscountPercentage(), "Item's discount percentage was mismatched");
                softAssert.assertEquals(line.getDiscountAmount(), reqItem.calculateDiscountAmount(), "Item's discount amount was mismatched");
                softAssert.assertEquals(line.getlinePrice(), reqItem.calculateTotalPrice(), "Item's line price was mismatched");
                softAssert.assertAll();
            }
        }
    }
}