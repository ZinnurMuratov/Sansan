package sansan.ru.rockylabs.sansan.MVP.models.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Zinnur on 19.12.16.
 */

public class UserSignUpResponseDTO implements Serializable{
    private @SerializedName("success") Boolean success;
    private @SerializedName("message") String message;

    public UserSignUpResponseDTO(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getStatus() {
        return success;
    }

    public void setStatus(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }





}
