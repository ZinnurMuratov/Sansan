package sansan.ru.rockylabs.sansan.MVP.presenters;

import javax.inject.Inject;

import rx.Subscription;
import sansan.ru.rockylabs.sansan.MVP.models.dto.UserLoginResponseDTO;
import sansan.ru.rockylabs.sansan.MVP.views.SignInView;
import sansan.ru.rockylabs.sansan.MVP.views.SignUpView;
import sansan.ru.rockylabs.sansan.MVP.views.View;
import sansan.ru.rockylabs.sansan.di.App;
import sansan.ru.rockylabs.sansan.utils.prefs.UserPrefs;

/**
 * Created by Zinnur on 19.12.16.
 */

public class SignInPresenter extends BasePresenter {
    private SignInView view;

    @Override
    protected View getView() {
        return view;
    }

    @Inject
    SignInPresenter(){}

    public void onCreate(SignInView view){
        App.getComponent().inject(this);
        this.view = view;
    }

    public void validate(String phone, String password){
        if (validatePhone(phone) && validatePassword(password)){
            login(phone,password);
        } else {
            view.showError("Проверьте данные");
        }
    }

    private void login(String phone, String password){
        view.showLoading();
        Subscription subscription = model.signIn(phone, password)
                .subscribe(this::onNextAuth, this::onError, () -> view.hideLoading());
        addSubscription(subscription);
    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
        view.showError("Ошибка при авторизации");
        view.hideLoading();
    }

    private void onNextAuth(UserLoginResponseDTO userLoginResponseDTO) {
        view.hideLoading();
        if (userLoginResponseDTO.getSuccess()){
            model.storeToken(userLoginResponseDTO.getToken());
            UserPrefs.setUser(userLoginResponseDTO.getUser());
            view.navigateToMain();
        } else {
            view.showError("Неправильный пароль");
        }
    }

    private Boolean validatePhone(String phone){
        return !phone.isEmpty();
    }

    private Boolean validatePassword(String password){
        return !password.isEmpty();
    }
}
