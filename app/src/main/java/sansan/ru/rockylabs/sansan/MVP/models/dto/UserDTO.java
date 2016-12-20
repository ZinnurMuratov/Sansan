package sansan.ru.rockylabs.sansan.MVP.models.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zinnur on 20.12.16.
 */

public class UserDTO {
    private  @SerializedName("_id") String id;
    private  @SerializedName("name") String name;
    private  @SerializedName("password") String password;
    private  @SerializedName("phone") String phone;
    private  @SerializedName("role") String role;
    private  @SerializedName("city") String city;

    public UserDTO(String id, String name, String password, String phone, String role, String city) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
