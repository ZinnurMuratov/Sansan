package sansan.ru.rockylabs.sansan.MVP.models.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Zinnur on 19.12.16.
 */

public class BidsDTO implements Serializable {
    @SerializedName("_id") private String id;
    @SerializedName("creator") private String creator;
    @SerializedName("worker") private String worker;
    @SerializedName("worker_name") private String workerName;
    @SerializedName("title") private String title;
    @SerializedName("phone") private String phone;
    @SerializedName("price") private long sum;
    @SerializedName("status") private String status;
    @SerializedName("created") private String created;
    @SerializedName("subscribed") private String subscribed;
    @SerializedName("closed") private String closed;

    public BidsDTO(String id, String creator, String worker, String workerName, String title, String phone, Integer sum, String status, String created, String subscribed, String closed) {
        this.id = id;
        this.creator = creator;
        this.worker = worker;
        this.workerName = workerName;
        this.title = title;
        this.phone = phone;
        this.sum = sum;
        this.status = status;
        this.created = created;
        this.subscribed = subscribed;
        this.closed = closed;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(String subscribed) {
        this.subscribed = subscribed;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
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

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
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

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }
}
