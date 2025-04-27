package supports.models;

import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class QuoteCreationReqPayload {
    private String customer;
    private List<QuoteItem> items;

    public QuoteCreationReqPayload(String customer) {
        this.customer = customer;
        items = new ArrayList<>();
    }

    public String getCustomer() {
        return this.customer;
    }

    public void addItems(QuoteItem quoteItem) {
        this.items.add(quoteItem);
    }

    public List<QuoteItem> getItems() {
        return this.items;
    }

    public QuoteItem getItemByName(String itemName) {
        return this.items.stream().filter(it -> it.getItemName().equals(itemName)).findAny().orElse(null);
    }

    public Float expectedTotalPrice() {
        Float totalPrice = 0.0f;
        for (QuoteItem item : items) {
            totalPrice += item.calculateTotalPrice();
        }
        return BigDecimal.valueOf(totalPrice).setScale(3, RoundingMode.HALF_UP).floatValue();
    }

    @Override
    public String toString() {
        return new GsonBuilder().setLenient().create().toJson(this).replace("\\u003d\\u003d", "==");
    }
}
