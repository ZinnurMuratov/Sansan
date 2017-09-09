package sansan.ru.rockylabs.sansan.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import sansan.ru.rockylabs.sansan.MVP.presenters.Presenter;
import sansan.ru.rockylabs.sansan.MVP.presenters.ProfilePresenter;
import sansan.ru.rockylabs.sansan.MVP.views.ProfileView;
import sansan.ru.rockylabs.sansan.R;
import sansan.ru.rockylabs.sansan.di.App;
import sansan.ru.rockylabs.sansan.ui.activities.StartActivity;
import sansan.ru.rockylabs.sansan.ui.base.BaseMainFragment;
import sansan.ru.rockylabs.sansan.utils.prefs.AuthPrefs;
import sansan.ru.rockylabs.sansan.utils.prefs.PrefUtils;
import sansan.ru.rockylabs.sansan.utils.prefs.UserPrefs;

/**
 * Created by Zinnur on 20.12.16.
 */

public class ProfileFragment extends BaseMainFragment implements ProfileView {
    @Bind(R.id.name_tv) protected TextView nameTV;
    @Bind(R.id.city_tv) protected TextView cityTV;
    @Bind(R.id.earned_label_tv) protected TextView earnedTV;
    private Toolbar toolbar;


    @Inject
    ProfilePresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ButterKnife.bind(this,view);
        activityCallback.setTitle("Профиль");
        presenter.onCreate(this);
        return view;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.quit:
                quit();
                return false;
            default:
                break;
        }
        return false;
    }

    private void quit(){
        new AlertDialog.Builder(getContext())
                .setMessage("Вы уверены, что хотите выйти")
                .setPositiveButton("Да", (dialog, w) -> {
                    PrefUtils.clear();
                    StartActivity.startActivity(getContext());
                })
                .setNegativeButton("Нет", (dialog, which) -> dialog.dismiss())
                .show();
    }




    @Override
    public void setEarned(String earned) {
        earnedTV.setText(earned + " руб.");
    }

    @Override
    public void setName(String name) {
        nameTV.setText(name);
    }

    @Override
    public void setCity(String city) {
        cityTV.setText(city);
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
    protected Presenter getPresenter() {
        return presenter;
    }
}
