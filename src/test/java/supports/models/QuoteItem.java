package supports.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.math.RoundingMode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteItem {
    private String item;
    private Integer quantity;
    private Float unitaryPrice;
    private Float discountPercentage;
    private Float discountAmount;

    private Float linePrice;

    public QuoteItem() {
    }

    public QuoteItem(String item, Integer quantity, Float price, Float discountPercentage) {
        this.item = item;
        this.quantity = quantity;
        this.unitaryPrice = price;
        this.discountPercentage = discountPercentage;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public Float getUnitaryPrice() {
        return this.unitaryPrice;
    }

    public Float getDiscountPercentage() {
        return this.discountPercentage;
    }

    public Float getDiscountAmount() {
        return this.discountAmount;
    }

    public String getItemName() {
        return this.item;
    }

    public Float getlinePrice() {
        return this.linePrice;
    }

    public String getItem() {
        return this.item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Float calculateTotalPrice() {
        float discount = (this.discountPercentage != null) ? this.discountPercentage : 0.0f;
        return BigDecimal.valueOf(this.unitaryPrice * this.quantity - this.quantity * this.unitaryPrice * discount).setScale(3, RoundingMode.HALF_UP).floatValue();
    }

    public Float calculateDiscountAmount() {
        float discount = (this.discountPercentage != null) ? this.discountPercentage : 0.0f;
        return BigDecimal.valueOf(this.quantity * this.unitaryPrice * discount).setScale(3, RoundingMode.HALF_UP).floatValue();
    }
}
