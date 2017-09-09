package sansan.ru.rockylabs.sansan.MVP.presenters;

import javax.inject.Inject;

import rx.Subscription;
import sansan.ru.rockylabs.sansan.MVP.models.dto.AbsDTO;
import sansan.ru.rockylabs.sansan.MVP.models.dto.TokenResponseDTO;
import sansan.ru.rockylabs.sansan.MVP.views.CreateBidView;
import sansan.ru.rockylabs.sansan.MVP.views.View;
import sansan.ru.rockylabs.sansan.di.App;
import sansan.ru.rockylabs.sansan.utils.DateUtil;
import sansan.ru.rockylabs.sansan.utils.prefs.UserPrefs;

/**
 * Created by Zinnur on 20.12.16.
 */

public class CreateBidPresenter extends BasePresenter {

    private CreateBidView view;

    @Inject CreateBidPresenter(){}

    public void onCreate(CreateBidView view){
        App.getComponent().inject(this);
        this.view = view;
    }

    @Override
    protected View getView() {
        return view;
    }

    public void validate(String title, String phone){
        if (validatePhone(phone) && validateName(title)){
            create(title,phone);
        } else {
            view.showError("Проверьте поля");
        }
    }

    private void create(String title, String phone){
        getView().showLoading();
        Subscription subscription = model.createBid(title,phone,getCity(), DateUtil.getDate())
                .subscribe(this::onNext, this::onError, () -> getView().hideLoading());
        addSubscription(subscription);
    }

    public void onError(Throwable e){
        getView().hideLoading();
        view.showError("Error");
        e.printStackTrace();
    }

    public void onNext(AbsDTO res){
        view.hideLoading();
        if (res.getStatus()){
            view.navigateToBidFeed();
        } else {
            view.showError("Failed");
        }
    }


    private String getCity(){
        String city = "Казань";
        try {
            city = UserPrefs.getUser().getCity();
        } catch (Exception e){
            e.printStackTrace();
        }
        return city;
    }

    private Boolean validateName(String title){
        return !title.isEmpty();
    }

    private Boolean validatePhone(String phone){
        return !phone.isEmpty();
    }
}
