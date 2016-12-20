package sansan.ru.rockylabs.sansan.MVP.presenters;

import android.text.TextUtils;

import javax.inject.Inject;

import rx.Observable;
import sansan.ru.rockylabs.sansan.MVP.views.SplashView;
import sansan.ru.rockylabs.sansan.di.App;
import sansan.ru.rockylabs.sansan.utils.prefs.AuthPrefs;

/**
 * Created by Zinnur on 19.12.16.
 */

public class SplashPresenter extends BasePresenter {

    private SplashView view;

    @Inject
    public SplashPresenter(){

    }

    public void onCreate(SplashView view){
        App.getComponent().inject(this);
        this.view = view;
    }

    @Override
    public SplashView getView() {
        return view;
    }

    public void checkAuthorized() {
        final Observable<String> getTokenObservable = Observable.create(subscriber -> subscriber.onNext(AuthPrefs.getToken()));
        getTokenObservable.subscribe(token -> {
            view.setAuthorized(!TextUtils.isEmpty(token));
        });
    }
}