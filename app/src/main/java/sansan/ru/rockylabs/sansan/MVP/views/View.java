package sansan.ru.rockylabs.sansan.MVP.views;

/**
 * Created by Zinnur on 19.12.16.
 */


public interface View {

    void showError(String error);

    void showLoading();

    void hideLoading();


}