package demosoft.route;

public class DecisionRequest {
    String message;

    public DecisionRequest(String message) {
        this.message = message;
    }

    public DecisionRequest() {

    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
