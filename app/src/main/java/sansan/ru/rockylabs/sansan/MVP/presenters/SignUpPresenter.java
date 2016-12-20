package sansan.ru.rockylabs.sansan.MVP.presenters;

import android.util.Log;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import sansan.ru.rockylabs.sansan.MVP.models.dto.UserLoginResponseDTO;
import sansan.ru.rockylabs.sansan.MVP.models.dto.UserSignUpResponseDTO;
import sansan.ru.rockylabs.sansan.MVP.views.SignUpView;
import sansan.ru.rockylabs.sansan.MVP.views.View;
import sansan.ru.rockylabs.sansan.di.App;

/**
 * Created by Zinnur on 19.12.16.
 */

public class SignUpPresenter extends BasePresenter {

    private SignUpView view;

    @Override
    protected View getView() {
        return view;
    }

    @Inject SignUpPresenter(){}

    public void onCreate(SignUpView view){
        App.getComponent().inject(this);
        this.view = view;
    }

    public void validate(String name, String phone, String password, String confirmPassword){
        if (validateName(name) && validatePhone(phone) && validatePassword(password, confirmPassword)){
            signUp(name,phone,password);
        } else {
            view.showError("Проверьте ввод");
        }
    }

    private void signUp(String name, String phone, String password){
        view.showLoading();
        Subscription subscription = model.signUp(name, phone, password, "worker")
                .doOnError(throwable -> throwable.printStackTrace())
                .flatMap(apiChainMapper(phone,password))
                .subscribe(this::onNextAuth, this::onError, () -> view.hideLoading());
        addSubscription(subscription);
    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
        view.hideLoading();
    }

    private void onNextAuth(UserLoginResponseDTO userLoginResponseDTO) {
        view.hideLoading();
        model.storeToken(userLoginResponseDTO.getToken());
        view.navigateToMain();
    }



    private Boolean validateName(String name){
        return !name.isEmpty();
    }

    private Boolean validatePhone(String phone){
        return !phone.isEmpty();
    }

    private Boolean validatePassword(String password, String confirmPassword){
        return !password.isEmpty() && !confirmPassword.isEmpty() && password.equals(confirmPassword);
    }

    private Func1<UserSignUpResponseDTO, Observable<UserLoginResponseDTO>> apiChainMapper(String phone, String password){
        return userSignUpResponseDTO -> model.signIn(phone, password);
    }


}
