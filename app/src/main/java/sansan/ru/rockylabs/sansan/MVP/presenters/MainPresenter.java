package sansan.ru.rockylabs.sansan.MVP.presenters;

import android.provider.Settings;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import javax.inject.Inject;

import rx.Subscription;
import sansan.ru.rockylabs.sansan.MVP.models.dto.AbsDTO;
import sansan.ru.rockylabs.sansan.MVP.models.dto.TokenResponseDTO;
import sansan.ru.rockylabs.sansan.MVP.views.MainView;
import sansan.ru.rockylabs.sansan.MVP.views.View;
import sansan.ru.rockylabs.sansan.di.App;
import sansan.ru.rockylabs.sansan.utils.prefs.UserPrefs;

/**
 * Created by Zinnur on 22.12.16.
 */

public class MainPresenter extends BasePresenter {

    private MainView view;

    @Override
    protected View getView() {
        return view;
    }

    @Inject MainPresenter() {}

    public void onCreate(MainView view){
        App.getComponent().inject(this);
        this.view = view;
        getFirebaseToken();
    }

    private void getFirebaseToken(){
        String token = FirebaseInstanceId.getInstance().getToken();
        String android_id = Settings.Secure.getString(App.getComponent().getContext()
                .getContentResolver(), Settings.Secure.ANDROID_ID);
        String userId = getUserID();

        Log.d("fcm ", token + " ");
        Log.d("android_id ",android_id + " ");
        if (token != null && android_id != null && userId != null){
            sendFirebaseToken(token, android_id, userId);
        }
    }

    private void sendFirebaseToken(String token, String id, String userId){
        Subscription subscription = model.fcm(userId, token, id)
                .subscribe(this::onNext, this::onError, () -> getView().hideLoading());
        addSubscription(subscription);
    }

    private void onError(Throwable e){
        e.printStackTrace();
    }

    private void onNext(AbsDTO res){
        Log.d(" fcm =>", " " + res.getMessage());
    }


    private String getUserID(){
        return UserPrefs.getUser().getId();
    }
}
