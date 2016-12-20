package sansan.ru.rockylabs.sansan.MVP.views;

/**
 * Created by Zinnur on 20.12.16.
 */

public interface BidView extends View {

    void setTitle(String title);

    void setPhone(String phone);

    void setPhoneVisibility(Boolean visible);

    void setCallBtnVisibility(Boolean visible);

    void setSubscriberBtnVisibility(Boolean visible);
}
