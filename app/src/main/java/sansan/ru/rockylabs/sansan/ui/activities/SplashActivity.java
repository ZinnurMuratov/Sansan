package sansan.ru.rockylabs.sansan.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import sansan.ru.rockylabs.sansan.MVP.presenters.SplashPresenter;
import sansan.ru.rockylabs.sansan.MVP.views.SplashView;
import sansan.ru.rockylabs.sansan.di.App;
import sansan.ru.rockylabs.sansan.utils.prefs.AuthPrefs;

public class SplashActivity extends AppCompatActivity implements SplashView {


    @Inject
    protected SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        presenter.onCreate(this);
        presenter.checkAuthorized();
    }

    @Override
    public void setAuthorized(boolean isAuthorized) {
        startActivityForResult(new Intent(this, isAuthorized ? MainActivity.class : StartActivity.class), 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        finish();
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

}
