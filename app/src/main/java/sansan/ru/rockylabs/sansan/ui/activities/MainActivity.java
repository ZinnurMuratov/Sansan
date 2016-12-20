package sansan.ru.rockylabs.sansan.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import sansan.ru.rockylabs.sansan.MVP.models.dto.BidsDTO;
import sansan.ru.rockylabs.sansan.MVP.views.MainView;
import sansan.ru.rockylabs.sansan.R;
import sansan.ru.rockylabs.sansan.ui.fragments.BidFragment;
import sansan.ru.rockylabs.sansan.ui.fragments.BidsFragment;
import sansan.ru.rockylabs.sansan.ui.fragments.CreateBidFragment;
import sansan.ru.rockylabs.sansan.ui.fragments.ProfileFragment;
import sansan.ru.rockylabs.sansan.utils.prefs.UserPrefs;

public class MainActivity extends AppCompatActivity implements MainView{

    private static String TAG = "MainActivity";
    private FragmentManager fragmentManager;

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    @Bind(R.id.new_bids) protected LinearLayout newBidsPage;
    @Bind(R.id.active_bids) protected LinearLayout activeBidsPage;
    @Bind(R.id.archive_bids) protected LinearLayout archiveBidsPage;
    @Bind(R.id.profile) protected LinearLayout profilePage;
    @Bind(R.id.addNew) protected LinearLayout addNewPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorTextIcons));
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();
        initElements();
        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment == null) openNewBids();
    }

    private void replaceFragment(Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, TAG);
        if (addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    private void initElements(){
        if (UserPrefs.getUser().getRole().equals("admin")){
            addNewPage.setVisibility(View.VISIBLE);
        }
        newBidsPage.setOnClickListener(view -> openNewBids());
        activeBidsPage.setOnClickListener(view -> openActiveBids());
        archiveBidsPage.setOnClickListener(view -> openArchivedBids());
        profilePage.setOnClickListener(view -> openProfile());
        addNewPage.setOnClickListener(view -> openCreateBidPage());
    }

    private void colorController(){
        newBidsPage.setBackgroundResource(R.color.colorPrimary);
        activeBidsPage.setBackgroundResource(R.color.colorPrimary);
        archiveBidsPage.setBackgroundResource(R.color.colorPrimary);
        profilePage.setBackgroundResource(R.color.colorPrimary);
        addNewPage.setBackgroundResource(R.color.colorPrimary);
    }

    @Override
    public void openNewBids() {
        colorController();
        newBidsPage.setBackgroundResource(R.color.colorPrimaryDark);
        toolbar.setTitle("Новые заявки");
        replaceFragment(new BidsFragment(), false);
    }

    @Override
    public void openActiveBids() {
        colorController();
        activeBidsPage.setBackgroundResource(R.color.colorPrimaryDark);
        toolbar.setTitle("Активные заявки");
    }

    @Override
    public void openArchivedBids() {
        colorController();
        archiveBidsPage.setBackgroundResource(R.color.colorPrimaryDark);
        toolbar.setTitle("Архив");
    }

    @Override
    public void openProfile() {
        colorController();
        profilePage.setBackgroundResource(R.color.colorPrimaryDark);
        toolbar.setTitle("Профиль");
        replaceFragment(new ProfileFragment(), false);
    }

    @Override
    public void openCreateBidPage() {
        colorController();
        addNewPage.setBackgroundResource(R.color.colorPrimaryDark);
        toolbar.setTitle("Создать заявку");
        replaceFragment(new CreateBidFragment(), false);
    }

    @Override
    public void openBid(BidsDTO bid) {
        replaceFragment(BidFragment.newInstance(bid), true);
    }
}
