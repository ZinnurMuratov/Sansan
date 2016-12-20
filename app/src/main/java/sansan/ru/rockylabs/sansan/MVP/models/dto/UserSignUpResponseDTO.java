package sansan.ru.rockylabs.sansan.MVP.models.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zinnur on 19.12.16.
 */

public class UserSignUpResponseDTO {
    private @SerializedName("status") String status;
    private @SerializedName("message") String message;

    public UserSignUpResponseDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }





}
