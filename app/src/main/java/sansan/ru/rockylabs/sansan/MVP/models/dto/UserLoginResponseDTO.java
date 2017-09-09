package sansan.ru.rockylabs.sansan.MVP.models.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zinnur on 19.12.16.
 */

public class UserLoginResponseDTO extends TokenResponseDTO {
    private @SerializedName("user") UserDTO user;

    public UserLoginResponseDTO(Boolean success, String token, UserDTO user) {
        super(success,token);
        this.user = user;
    }

    public UserLoginResponseDTO(Boolean success, String token) {
        super(success,token);
    }

    public UserDTO getUser() {
        return user;
    }
}
