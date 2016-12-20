package sansan.ru.rockylabs.sansan.MVP.models.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Zinnur on 20.12.16.
 */

public class EarnedResponseDTO implements Serializable {
    private  @SerializedName("success") Boolean success;
    private  @SerializedName("earned") String earned;

    public EarnedResponseDTO(Boolean success, String earned) {
        this.success = success;
        this.earned = earned;
    }

    public void setEarned(String earned) {
        this.earned = earned;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getEarned() {
        return earned;
    }

    public Boolean getSuccess() {
        return success;
    }


}
