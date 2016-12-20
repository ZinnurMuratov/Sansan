package sansan.ru.rockylabs.sansan.MVP.models;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import sansan.ru.rockylabs.sansan.MVP.models.dto.BidsDTO;
import sansan.ru.rockylabs.sansan.MVP.models.dto.UserLoginResponseDTO;
import sansan.ru.rockylabs.sansan.MVP.models.dto.UserSignUpResponseDTO;
import sansan.ru.rockylabs.sansan.api.ApiInterface;
import sansan.ru.rockylabs.sansan.api.ApiModule;
import sansan.ru.rockylabs.sansan.di.App;
import sansan.ru.rockylabs.sansan.utils.Const;
import sansan.ru.rockylabs.sansan.utils.prefs.AuthPrefs;

/**
 * Created by Zinnur on 19.12.16.
 */

public class ModelImpl implements Model {

    private final Observable.Transformer schedulersTransformer;

    @Inject
    protected ApiInterface apiInterface;


    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;

    public ModelImpl() {
        App.getComponent().inject(this);
        schedulersTransformer = o -> ((Observable) o).subscribeOn(ioThread)
                .observeOn(uiThread)
                .unsubscribeOn(ioThread);
    }


    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }

    @Override
    public Observable<UserSignUpResponseDTO> signUp(String name, String phone, String password, String role) {
        return apiInterface
                .signUp(name,phone,password,role)
                .compose(applySchedulers());

    }

    @Override
    public Observable<UserLoginResponseDTO> signIn(String phone, String password) {
        return apiInterface
                .signIn(phone,password)
                .compose(applySchedulers());
    }

    @Override
    public Observable<List<BidsDTO>> getBids(int page, String status) {
        return apiInterface
                .getBids(page, Const.LIMIT, status)
                .compose(applySchedulers());
    }

    @Override
    public void storeToken(String token) {
        AuthPrefs.setToken(token);
    }
}