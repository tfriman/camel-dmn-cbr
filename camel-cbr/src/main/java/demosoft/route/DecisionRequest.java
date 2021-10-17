package demosoft.route;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DecisionRequest {
    @JsonProperty("LoyaltyLevel")
    Long loyaltyLevel;
    @JsonProperty("TotalPrice")
    Long totalPrice;

    public DecisionRequest(Long loyaltyLevel, Long totalPrice) {
        this.loyaltyLevel = loyaltyLevel;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "DecisionRequest{" +
                "loyaltyLevel=" + loyaltyLevel +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public Long getLoyaltyLevel() {
        return loyaltyLevel;
    }

    public void setLoyaltyLevel(Long loyaltyLevel) {
        this.loyaltyLevel = loyaltyLevel;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public DecisionRequest() {
    }
}
