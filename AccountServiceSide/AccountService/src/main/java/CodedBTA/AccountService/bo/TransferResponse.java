package CodedBTA.AccountService.bo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class TransferResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
