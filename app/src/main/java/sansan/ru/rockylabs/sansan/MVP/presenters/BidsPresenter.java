package sansan.ru.rockylabs.sansan.MVP.presenters;

import android.util.Log;

import java.net.UnknownHostException;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import sansan.ru.rockylabs.sansan.MVP.models.dto.BidsDTO;
import sansan.ru.rockylabs.sansan.MVP.views.BidsView;
import sansan.ru.rockylabs.sansan.MVP.views.View;
import sansan.ru.rockylabs.sansan.di.App;
import sansan.ru.rockylabs.sansan.utils.PageUtil;

/**
 * Created by Zinnur on 19.12.16.
 */

public class BidsPresenter extends BasePresenter{

    private BidsView view;

    @Override
    protected View getView() {
        return view;
    }

    @Inject BidsPresenter(){}

    public void onCreate(BidsView view){
        App.getComponent().inject(this);
        this.view = view;
    }

    public void request(){
        getView().showLoading();
        Subscription subscription = model.getBids(getPage(),"new")
                .subscribe(this::onNextGetShots, this::onError, () -> getView().hideLoading());
        addSubscription(subscription);
    }



    public void onRefresh(){
        getView().showLoading();
        Subscription subscription = model.getBids(getPage(),"new")
                .subscribe(this::onNextRefreshShots, this::onError, () -> getView().hideLoading());
        addSubscription(subscription);
    }

    public int getPage(){
        int page = PageUtil.getPage(view.getBidsCount());
        Log.d("page ", " "+page);
        return page;
    }

    public void onError(Throwable e){
        getView().hideLoading();
        e.printStackTrace();
        if(e instanceof UnknownHostException) {
            view.showError("Network connection unavailable");
        }
    }

    public void onNextRefreshShots(List<BidsDTO> bids){
        view.clearAdapter();
        view.updateAdapter(bids);
    }

    public void onNextGetShots(List<BidsDTO> bids){
        view.updateAdapter(bids);
    }


}
