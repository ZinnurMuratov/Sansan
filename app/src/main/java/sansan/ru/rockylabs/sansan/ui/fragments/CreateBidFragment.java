package sansan.ru.rockylabs.sansan.ui.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import sansan.ru.rockylabs.sansan.MVP.presenters.CreateBidPresenter;
import sansan.ru.rockylabs.sansan.MVP.presenters.Presenter;
import sansan.ru.rockylabs.sansan.MVP.views.CreateBidView;
import sansan.ru.rockylabs.sansan.R;
import sansan.ru.rockylabs.sansan.di.App;
import sansan.ru.rockylabs.sansan.ui.base.BaseMainFragment;

/**
 * Created by Zinnur on 20.12.16.
 */

public class CreateBidFragment extends BaseMainFragment implements CreateBidView {

    @Bind(R.id.title_input) protected EditText titleET;
    @Bind(R.id.phone_input) protected EditText phoneET;
    @Bind(R.id.create_btn) protected Button createBtn;
    private ProgressDialog alertDialog;

    @Inject
    CreateBidPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_bid, container, false);
        ButterKnife.bind(this, view);
        presenter.onCreate(this);
        initBtns();
        return view;
    }


    private void initBtns(){
        createBtn.setOnClickListener(view -> presenter.validate(titleET.getText().toString(),phoneET.getText().toString()));
    }


    @Override
    public void showError(String error) {
        Snackbar.make(createBtn,error,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        alertDialog = new ProgressDialog(getContext());
        alertDialog.setMessage("Подождите");
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    @Override
    public void hideLoading() {
        alertDialog.dismiss();
    }

    @Override
    public void navigateToBidFeed() {
        activityCallback.openNewBids();
    }

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }
}
