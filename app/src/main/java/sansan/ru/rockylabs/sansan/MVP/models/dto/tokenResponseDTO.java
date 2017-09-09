package sansan.ru.rockylabs.sansan.MVP.models.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Zinnur on 20.12.16.
 */

public class TokenResponseDTO implements Serializable {
    private  @SerializedName("success") Boolean success;
    private  @SerializedName("token") String token;

    public TokenResponseDTO(Boolean success, String token) {
        this.success = success;
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public Boolean getSuccess() {
        return success;
    }


}