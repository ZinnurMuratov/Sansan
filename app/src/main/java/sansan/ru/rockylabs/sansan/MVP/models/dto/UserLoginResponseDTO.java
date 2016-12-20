package sansan.ru.rockylabs.sansan.MVP.models.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zinnur on 19.12.16.
 */

public class UserLoginResponseDTO {
    private @SerializedName("success") Boolean success;
    private @SerializedName("token") String token;

    public UserLoginResponseDTO(Boolean success, String token) {
        this.success = success;
        this.token = token;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
