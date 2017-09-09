package sansan.ru.rockylabs.sansan.MVP.views;

import com.tbruyelle.rxpermissions.RxPermissions;

/**
 * Created by Zinnur on 20.12.16.
 */

public interface BidView extends View {

    void setTitle(String title);

    void setPhone(String phone);

    void setStatus(String status);

    void setDateLabel(String dateLabel);

    void setDate(String date);

    void setPrice(String price);

    void setWorkerName(String workerName);

    void setPriceBtnVisibility(Boolean visible);

    void setCancelBtnVisibility(Boolean visible);

    void setPhoneVisibility(Boolean visible);

    void setCallBtnVisibility(Boolean visible);

    void setSubscriberBtnVisibility(Boolean visible);

    void setDoneBtnVisibility(Boolean visible);

    void showPhoneBlock(Boolean visible);

    void showMasterBlock(Boolean visible);

    void showPriceBlock(Boolean visible);

    void showButtons(Boolean visible);

    void showTextViews(Boolean visible);

    void showTemplate(Boolean visible);

    void showPriceInput(Boolean visible);

    void showSendPriceBtn(Boolean visible);

    String getPrice();

    void setUpdated();

    RxPermissions getRxPermissions();

    void call();

}
