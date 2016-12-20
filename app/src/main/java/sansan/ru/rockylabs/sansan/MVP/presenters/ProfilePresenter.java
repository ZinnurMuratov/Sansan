package sansan.ru.rockylabs.sansan.MVP.presenters;

import java.net.UnknownHostException;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import sansan.ru.rockylabs.sansan.MVP.models.dto.BidsDTO;
import sansan.ru.rockylabs.sansan.MVP.models.dto.EarnedResponseDTO;
import sansan.ru.rockylabs.sansan.MVP.views.ProfileView;
import sansan.ru.rockylabs.sansan.MVP.views.View;
import sansan.ru.rockylabs.sansan.di.App;
import sansan.ru.rockylabs.sansan.utils.prefs.UserPrefs;

/**
 * Created by Zinnur on 20.12.16.
 */

public class ProfilePresenter extends BasePresenter {

    private ProfileView view;

    @Override
    protected View getView() {
        return view;
    }

    @Inject
    ProfilePresenter (){}

    public void onCreate(ProfileView view){
        App.getComponent().inject(this);
        this.view = view;
        getGeneralEarning();
        setData();
    }

    private void setData(){
        if (UserPrefs.getUser().getName() != null) {
            view.setName(UserPrefs.getUser().getName());
        }
        if (UserPrefs.getUser().getCity() != null) {
            view.setCity(UserPrefs.getUser().getCity());
        }
    }


    private void getGeneralEarning(){
        Subscription subscription = model.getGeneralEarnings(UserPrefs.getUser().getId())
                .subscribe(this::onNext, this::onError, () -> getView().hideLoading());
        addSubscription(subscription);
    }

    public void onError(Throwable e){
        getView().hideLoading();
        e.printStackTrace();
    }

    public void onNext(EarnedResponseDTO bids){
        view.setEarned(bids.getEarned());
    }



}
