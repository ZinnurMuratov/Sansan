package sansan.ru.rockylabs.sansan.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import sansan.ru.rockylabs.sansan.R;
import sansan.ru.rockylabs.sansan.ui.fragments.SignInFragment;
import sansan.ru.rockylabs.sansan.ui.fragments.SignUpFragment;
import sansan.ru.rockylabs.sansan.ui.fragments.StartFragment;

public class StartActivity extends AppCompatActivity implements StartActivityCallback{

    private static String TAG = "TAG";
    private FragmentManager fragmentManager;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, StartActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        fragmentManager = getSupportFragmentManager();
        replaceFragment(new StartFragment(), false);
    }

    private void replaceFragment(Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, TAG);
        if (addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void startSignInFragment() {
        replaceFragment(new SignInFragment(), true);
    }

    @Override
    public void startSignUpFragment() {
        replaceFragment(new SignUpFragment(), true);
    }

    @Override
    public void startMainActivity() {
        startActivityForResult(new Intent(this, MainActivity.class), 0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        finish();
    }


}
