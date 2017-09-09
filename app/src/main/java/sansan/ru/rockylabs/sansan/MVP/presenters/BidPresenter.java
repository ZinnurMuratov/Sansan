package sansan.ru.rockylabs.sansan.MVP.presenters;

import android.Manifest;
import android.util.Log;

import javax.inject.Inject;

import rx.Subscription;
import sansan.ru.rockylabs.sansan.MVP.models.dto.BidsDTO;
import sansan.ru.rockylabs.sansan.MVP.models.dto.BidsResponseDTO;
import sansan.ru.rockylabs.sansan.MVP.views.BidView;
import sansan.ru.rockylabs.sansan.MVP.views.View;
import sansan.ru.rockylabs.sansan.di.App;
import sansan.ru.rockylabs.sansan.utils.DateUtil;
import sansan.ru.rockylabs.sansan.utils.prefs.UserPrefs;

/**
 * Created by Zinnur on 20.12.16.
 */

public class BidPresenter extends BasePresenter {

    private BidView view;
    private BidsDTO bidsDTO;
    private Boolean inputActive = false;


    @Inject BidPresenter(){}

    public void onCreate(BidView view){
        App.getComponent().inject(this);
        this.view = view;
    }

    public void askToCall(){
        view.getRxPermissions()
                .request(Manifest.permission.CALL_PHONE)
                .subscribe(granted -> {
                    if (granted ) {view.call();}
                    else {Log.d("permissions  ", " idi nax");}
                });

    }



    public void setData(BidsDTO bid){
        view.setTitle(bid.getTitle());
        view.setPhone(bid.getPhone());
        if (bid.getWorkerName() != null) view.setWorkerName(bid.getWorkerName() + " ");
        view.setPrice(bid.getSum() + " руб.");

        view.setStatus(bid.getStatus() + " ");
        setDate(bid);
        setVisibility(bid);
    }

    public void update(BidsDTO bid, String status){
        String price = "0";
        if (inputActive && validateInput(view.getPrice())) {
            price = view.getPrice();
        }
        getView().showLoading();
        Subscription subscription = model.updateBid(bid.getId(), UserPrefs.getUser().getId(), status, DateUtil.getDate(), price)
                .subscribe(this::onNext, this::onError, () -> getView().hideLoading());
        addSubscription(subscription);
    }

    public void onError(Throwable e){
        getView().hideLoading();
        view.showError("Error");
        e.printStackTrace();
    }

    public void onNext(BidsResponseDTO res){
        view.hideLoading();
        view.setSubscriberBtnVisibility(false);
        if (res.getStatus()){
            setData(res.getBid());
            bidsDTO = res.getBid();
            view.setUpdated();
            if (inputActive) {
                showPriceInput(!inputActive);
            }
        } else if (res.getMessage().equals("busy")){
            setUpForBusyBids(true);
        } else {
            view.showError(res.getMessage());
        }
    }

    private void setVisibility(BidsDTO bid){
        if (bid.getStatus().equals("новый")){
            setUpForNewBids(bid);
        } else if (bid.getStatus().equals("активный")){
            setUpForActiveBids(bid);
        } else if (bid.getStatus().equals("выполнено")){
            setUpForFinishedBids(bid);
        } else if (bid.getStatus().equals("отказ")){
            setUpForCanceledBids(bid);
        }
    }

    private void setUpForNewBids(BidsDTO bid){
        setUpForBusyBids(false);
        view.setPriceBtnVisibility(false);
        view.setCallBtnVisibility(false);
        view.setSubscriberBtnVisibility(true);
        view.setCancelBtnVisibility(false);
        view.setDoneBtnVisibility(false);
        view.showMasterBlock(false);
        view.showPhoneBlock(false);
        view.showPriceBlock(false);
    }

    private void setUpForActiveBids(BidsDTO bid){
        setUpForBusyBids(false);
        view.setPriceBtnVisibility(false);
        view.setCallBtnVisibility(true);
        view.setSubscriberBtnVisibility(false);
        view.setCancelBtnVisibility(true);
        view.setDoneBtnVisibility(true);
        view.showMasterBlock(true);
        view.showPhoneBlock(true);
        view.showPriceBlock(false);
    }

    private void setUpForCanceledBids(BidsDTO bid){
        setUpForBusyBids(false);
        view.setPriceBtnVisibility(false);
        view.setCallBtnVisibility(true);
        view.setSubscriberBtnVisibility(false);
        view.setCancelBtnVisibility(false);
        view.setDoneBtnVisibility(false);
        view.showMasterBlock(true);
        view.showPhoneBlock(true);
        view.showPriceBlock(true);
    }

    private void setUpForFinishedBids(BidsDTO bid){
        setUpForBusyBids(false);
        view.setPriceBtnVisibility(true);
        view.setCallBtnVisibility(true);
        view.setSubscriberBtnVisibility(false);
        view.setCancelBtnVisibility(false);
        view.setDoneBtnVisibility(false);
        view.showMasterBlock(true);
        view.showPhoneBlock(true);
        view.showPriceBlock(true);
    }

    private void setUpForBusyBids(Boolean show){
        view.showButtons(!show);
        view.showTextViews(!show);
        view.showTemplate(show);
    }

    private void setDate(BidsDTO bid){
        if (bid.getStatus().equals("новый")){
            view.setDateLabel("Создан:");
            view.setDate(bid.getCreated() + " ");
        } else if (bid.getStatus().equals("активный")){
            view.setDateLabel("Принят:");
            view.setDate(bid.getSubscribed() + " ");
        } else if (bid.getStatus().equals("выполнено")){
            view.setDateLabel("Закрыт:");
            view.setDate(bid.getClosed() + " ");
        } else if (bid.getStatus().equals("отказ")){
            view.setDateLabel("закрыт:");
            view.setDate(bid.getClosed() + " ");
        }
    }

    public void showPriceInput(Boolean show){
        view.showPriceInput(show);
        view.showSendPriceBtn(show);
        view.setCallBtnVisibility(!show);
        view.setPriceBtnVisibility(!show);
        inputActive = show;
    }


    @Override
    protected View getView() {
        return view;
    }

    public BidsDTO getBid(){
        return bidsDTO;
    }

    private Boolean validateInput(String price){
       return !price.isEmpty();
    }

}
