package demosoft.route;

public class IncomingOrder {
    public Long loyaltyLevel;
    public Long priceInCents;

    public IncomingOrder() {
    }

    public Long getLoyaltyLevel() {
        return loyaltyLevel;
    }

    public void setLoyaltyLevel(Long loyaltyLevel) {
        this.loyaltyLevel = loyaltyLevel;
    }

    public Long getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(Long priceInCents) {
        this.priceInCents = priceInCents;
    }

    @Override
    public String toString() {
        return "IncomingOrder{" +
                "loyaltyLevel=" + loyaltyLevel +
                ", priceInCents=" + priceInCents +
                '}';
    }
}
