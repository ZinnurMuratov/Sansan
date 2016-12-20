package sansan.ru.rockylabs.sansan.MVP.models.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Zinnur on 19.12.16.
 */

public class BidsDTO implements Serializable {
    @SerializedName("_id") private String id;
    @SerializedName("creator") private String creator;
    @SerializedName("title") private String title;
    @SerializedName("phone") private String phone;
    @SerializedName("sum") private Integer sum;
    @SerializedName("status") private String status;
    @SerializedName("created") private String created;

    public BidsDTO(String id, String creator, String title, String phone, Integer sum, String status, String created) {
        this.id = id;
        this.creator = creator;
        this.title = title;
        this.phone = phone;
        this.sum = sum;
        this.status = status;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
