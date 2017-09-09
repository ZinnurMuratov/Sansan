package sansan.ru.rockylabs.sansan.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.Bind;
import butterknife.ButterKnife;
import sansan.ru.rockylabs.sansan.R;
import sansan.ru.rockylabs.sansan.utils.prefs.UserPrefs;

import static android.R.id.toggle;

/**
 * Created by Zinnur on 22.12.16.
 */

public abstract class BaseMainActivity extends AppCompatActivity {

    protected static String TAG = "pop";
    protected RxPermissions rxPermissions;

    @Bind(R.id.new_bids) protected LinearLayout newBidsPage;
    @Bind(R.id.active_bids) protected LinearLayout activeBidsPage;
    @Bind(R.id.archive_bids) protected LinearLayout archiveBidsPage;
    @Bind(R.id.profile) protected LinearLayout profilePage;
    @Bind(R.id.addNew) protected LinearLayout addNewPage;
    @Bind(R.id.toolbar) protected Toolbar toolbar;

    protected Fragment savedF;
    protected FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        rxPermissions = new RxPermissions(this);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(getfListener());
        savedF = fragmentManager.findFragmentByTag(TAG);
        setUp();


    }

    private void setUp(){
        if (UserPrefs.getUser().getRole().equals("admin")){
            addNewPage.setVisibility(View.VISIBLE);
        }
        toolbar.setTitle("СанСаныч");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorTextIcons));
        setSupportActionBar(toolbar);
    }



    protected void replaceFragment(Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, TAG);
        if (addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    protected void dismissBack(){
        if (fragmentManager.findFragmentByTag(TAG) != null){
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }


    private FragmentManager.OnBackStackChangedListener getfListener(){
        return () -> {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                toolbar.setNavigationOnClickListener(v -> onBackPressed());
            } else {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
        };
    }

    protected void colorController(){
        newBidsPage.setBackgroundResource(R.color.colorPrimary);
        activeBidsPage.setBackgroundResource(R.color.colorPrimary);
        archiveBidsPage.setBackgroundResource(R.color.colorPrimary);
        profilePage.setBackgroundResource(R.color.colorPrimary);
        addNewPage.setBackgroundResource(R.color.colorPrimary);
    }



}
