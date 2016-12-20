package sansan.ru.rockylabs.sansan.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sansan.ru.rockylabs.sansan.MVP.presenters.Presenter;
import sansan.ru.rockylabs.sansan.R;
import sansan.ru.rockylabs.sansan.ui.activities.StartActivityCallback;
import sansan.ru.rockylabs.sansan.ui.base.BaseFragment;

/**
 * Created by Zinnur on 19.12.16.
 */

public class StartFragment extends BaseFragment {
    @Bind(R.id.sign_in_btn)
    protected Button signInBtn;

    @Bind(R.id.sign_up_btn)
    protected Button signUpBtn;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.sign_up_btn)
    public void onSignBtnClick(View v){
        startActivityCallback.startSignUpFragment();
    }

    @OnClick(R.id.sign_in_btn)
    public void onSignInBtnClick(View v){
        startActivityCallback.startSignInFragment();
    }

    @Override
    protected Presenter getPresenter() {
        return null;
    }
}