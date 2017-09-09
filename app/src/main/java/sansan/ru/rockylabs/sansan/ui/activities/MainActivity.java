package sansan.ru.rockylabs.sansan.ui.activities;

import android.os.Bundle;

import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Inject;

import sansan.ru.rockylabs.sansan.MVP.models.dto.BidsDTO;
import sansan.ru.rockylabs.sansan.MVP.presenters.MainPresenter;
import sansan.ru.rockylabs.sansan.MVP.views.MainView;
import sansan.ru.rockylabs.sansan.R;
import sansan.ru.rockylabs.sansan.di.App;
import sansan.ru.rockylabs.sansan.ui.base.BaseMainActivity;
import sansan.ru.rockylabs.sansan.ui.fragments.ActiveBidsFragment;
import sansan.ru.rockylabs.sansan.ui.fragments.ArchiveBidsFragment;
import sansan.ru.rockylabs.sansan.ui.fragments.BidFragment;
import sansan.ru.rockylabs.sansan.ui.fragments.BidsFragment;
import sansan.ru.rockylabs.sansan.ui.fragments.CreateBidFragment;
import sansan.ru.rockylabs.sansan.ui.fragments.ProfileFragment;

public class MainActivity extends BaseMainActivity implements MainView{


    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        presenter.onCreate(this);
        initElements();
        if (savedF == null) openNewBids();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initElements(){
        newBidsPage.setOnClickListener(view -> openNewBids());
        activeBidsPage.setOnClickListener(view -> openActiveBids());
        archiveBidsPage.setOnClickListener(view -> openArchivedBids());
        profilePage.setOnClickListener(view -> openProfile());
        addNewPage.setOnClickListener(view -> openCreateBidPage());
    }


    @Override
    public void openNewBids() {
        dismissBack();
        colorController();
        newBidsPage.setBackgroundResource(R.color.colorPrimaryDark);
        replaceFragment(new BidsFragment(), false);
    }

    @Override
    public void openActiveBids() {
        dismissBack();
        colorController();
        activeBidsPage.setBackgroundResource(R.color.colorPrimaryDark);
        replaceFragment(new ActiveBidsFragment(), false);
    }

    @Override
    public void openArchivedBids() {
        dismissBack();
        colorController();
        archiveBidsPage.setBackgroundResource(R.color.colorPrimaryDark);
        replaceFragment(new ArchiveBidsFragment(), false);
    }

    @Override
    public void openProfile() {
        dismissBack();
        colorController();
        profilePage.setBackgroundResource(R.color.colorPrimaryDark);
        replaceFragment(new ProfileFragment(), false);
    }

    @Override
    public void openCreateBidPage() {
        dismissBack();
        colorController();
        addNewPage.setBackgroundResource(R.color.colorPrimaryDark);
        replaceFragment(new CreateBidFragment(), false);
    }

    @Override
    public void openBid(BidsDTO bid) {
        dismissBack();
        replaceFragment(BidFragment.newInstance(bid), true);
    }

    @Override
    public void setTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public RxPermissions getRxPermissions() {
        return rxPermissions;
    }



}
