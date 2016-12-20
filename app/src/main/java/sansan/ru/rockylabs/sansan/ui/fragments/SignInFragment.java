package sansan.ru.rockylabs.sansan.ui.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import sansan.ru.rockylabs.sansan.MVP.presenters.Presenter;
import sansan.ru.rockylabs.sansan.MVP.presenters.SignInPresenter;
import sansan.ru.rockylabs.sansan.MVP.presenters.SignUpPresenter;
import sansan.ru.rockylabs.sansan.MVP.views.SignInView;
import sansan.ru.rockylabs.sansan.R;
import sansan.ru.rockylabs.sansan.di.App;
import sansan.ru.rockylabs.sansan.ui.base.BaseFragment;

/**
 * Created by Zinnur on 19.12.16.
 */

public class SignInFragment extends BaseFragment implements SignInView{
    @Bind(R.id.phone_input) protected EditText phoneET;
    @Bind(R.id.pass_input) protected EditText passET;
    @Bind(R.id.sign_in_btn) protected Button signInBtn;
    private ProgressDialog alertDialog;

    @Inject
    SignInPresenter presenter;

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.bind(this, view);
        presenter.onCreate(this);
        initBtn();
        return view;
    }

    private void initBtn(){
        signInBtn.setOnClickListener(view -> presenter.validate(phoneET.getText().toString(),passET.getText().toString()));
    }


    @Override
    public void showError(String error) {
        Snackbar.make(signInBtn, error, Snackbar.LENGTH_LONG).show();
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
    public void navigateToMain() {
        startActivityCallback.startMainActivity();
    }
}
