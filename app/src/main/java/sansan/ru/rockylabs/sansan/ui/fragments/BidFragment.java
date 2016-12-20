package sansan.ru.rockylabs.sansan.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import sansan.ru.rockylabs.sansan.MVP.models.dto.BidsDTO;
import sansan.ru.rockylabs.sansan.MVP.presenters.BidPresenter;
import sansan.ru.rockylabs.sansan.MVP.presenters.Presenter;
import sansan.ru.rockylabs.sansan.MVP.views.BidView;
import sansan.ru.rockylabs.sansan.R;
import sansan.ru.rockylabs.sansan.di.App;
import sansan.ru.rockylabs.sansan.ui.base.BaseMainFragment;

/**
 * Created by Zinnur on 20.12.16.
 */

public class BidFragment extends BaseMainFragment implements BidView{

    @Bind(R.id.phone_tv) protected TextView phoneTV;
    @Bind(R.id.call_btn) protected Button callBtn;
    @Bind(R.id.title_tv) protected TextView titleTV;
    @Bind(R.id.take_bid_btn) protected Button takeBid;

    private static final String BUNDLE_REPO_KEY = "BUNDLE_REPO_KEY";

    @Inject
    BidPresenter presenter;

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }

    public static BidFragment newInstance(BidsDTO bid) {
        BidFragment myFragment = new BidFragment();

        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_REPO_KEY, bid);
        myFragment.setArguments(args);

        return myFragment;
    }

    private BidsDTO getBid() {
        return (BidsDTO) getArguments().getSerializable(BUNDLE_REPO_KEY);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        presenter.onCreate(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bid, container, false);
        ButterKnife.bind(this, view);
        presenter.setData(getBid());
        initBtns();
        return view;
    }

    private void initBtns(){
        takeBid.setOnClickListener(view -> presenter.takeBid(getBid()));
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
    public void setTitle(String title) {
        titleTV.setText(title);
    }

    @Override
    public void setPhone(String phone) {
        phoneTV.setText(phone);
    }

    @Override
    public void setPhoneVisibility(Boolean visible) {
        if (visible) {
            phoneTV.setVisibility(View.VISIBLE);
        } else {
            phoneTV.setVisibility(View.GONE);
        }
    }

    @Override
    public void setCallBtnVisibility(Boolean visible) {
        if (visible) {
            callBtn.setVisibility(View.VISIBLE);
        } else {
            callBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void setSubscriberBtnVisibility(Boolean visible) {
        if (visible) {
            takeBid.setVisibility(View.VISIBLE);
        } else {
            takeBid.setVisibility(View.GONE);
        }
    }
}
