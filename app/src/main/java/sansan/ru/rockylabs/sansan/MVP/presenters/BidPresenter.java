package sansan.ru.rockylabs.sansan.MVP.presenters;

import javax.inject.Inject;

import rx.Subscription;
import sansan.ru.rockylabs.sansan.MVP.models.dto.AbsResponseDTO;
import sansan.ru.rockylabs.sansan.MVP.models.dto.BidsDTO;
import sansan.ru.rockylabs.sansan.MVP.views.BidView;
import sansan.ru.rockylabs.sansan.MVP.views.View;
import sansan.ru.rockylabs.sansan.di.App;
import sansan.ru.rockylabs.sansan.utils.prefs.UserPrefs;

/**
 * Created by Zinnur on 20.12.16.
 */

public class BidPresenter extends BasePresenter {

    private BidView view;

    @Inject BidPresenter(){}

    public void onCreate(BidView view){
        App.getComponent().inject(this);
        this.view = view;
    }

    public void setData(BidsDTO bid){
        view.setTitle(bid.getTitle());
        view.setPhone(bid.getPhone());
        setVisibility(bid);
    }

    public void takeBid(BidsDTO bid){
        getView().showLoading();
        Subscription subscription = model.updateBid(bid.getId(), UserPrefs.getUser().getId(), "active")
                .subscribe(this::onNext, this::onError, () -> getView().hideLoading());
        addSubscription(subscription);
    }

    public void onError(Throwable e){
        getView().hideLoading();
        view.showError("Error");
        e.printStackTrace();
    }

    public void onNext(BidsDTO res){
        view.hideLoading();
        view.setSubscriberBtnVisibility(false);
        setVisibility(res);
    }

    private void setVisibility(BidsDTO bid){
        if (bid.getStatus().equals("new")) {
            view.setPhoneVisibility(false);
            view.setCallBtnVisibility(false);
        } else {
            view.setPhoneVisibility(true);
            view.setCallBtnVisibility(true);
        }
    }

    @Override
    protected View getView() {
        return view;
    }
}
