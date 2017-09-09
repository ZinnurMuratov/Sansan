package sansan.ru.rockylabs.sansan.MVP.models.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Zinnur on 22.12.16.
 */

public class BidsResponseDTO extends AbsDTO {

    private @SerializedName("bid") BidsDTO bid;

    public BidsResponseDTO(Boolean success, String message) {
        super(success, message);
    }

    public BidsResponseDTO(Boolean success, String message, BidsDTO bid) {
        super(success, message);
        this.bid = bid;
    }

    public BidsDTO getBid() {
        return bid;
    }

    public void setBid(BidsDTO bid) {
        this.bid = bid;
    }
}
