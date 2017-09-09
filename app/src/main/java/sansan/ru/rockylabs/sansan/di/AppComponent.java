package sansan.ru.rockylabs.sansan.di;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import sansan.ru.rockylabs.sansan.MVP.models.ModelImpl;
import sansan.ru.rockylabs.sansan.MVP.presenters.BasePresenter;
import sansan.ru.rockylabs.sansan.MVP.presenters.SplashPresenter;
import sansan.ru.rockylabs.sansan.di.modules.GsonModule;
import sansan.ru.rockylabs.sansan.ui.activities.MainActivity;
import sansan.ru.rockylabs.sansan.ui.activities.SplashActivity;
import sansan.ru.rockylabs.sansan.di.modules.ContextModule;
import sansan.ru.rockylabs.sansan.di.modules.ModelModule;
import sansan.ru.rockylabs.sansan.di.modules.PresenterModule;
import sansan.ru.rockylabs.sansan.ui.fragments.ActiveBidsFragment;
import sansan.ru.rockylabs.sansan.ui.fragments.ArchiveBidsFragment;
import sansan.ru.rockylabs.sansan.ui.fragments.BidFragment;
import sansan.ru.rockylabs.sansan.ui.fragments.BidsFragment;
import sansan.ru.rockylabs.sansan.ui.fragments.CreateBidFragment;
import sansan.ru.rockylabs.sansan.ui.fragments.ProfileFragment;
import sansan.ru.rockylabs.sansan.ui.fragments.SignInFragment;
import sansan.ru.rockylabs.sansan.ui.fragments.SignUpFragment;

/**
 * Created by Zinnur on 19.12.16.
 */

@Singleton
@Component(modules = {PresenterModule.class, ContextModule.class, ModelModule.class, GsonModule.class})
public interface AppComponent {

    Context getContext();

    Gson getGson();

    void inject(MainActivity a);

    void inject(SplashPresenter p);

    void inject(SplashActivity a);

    void inject(ModelImpl m);

    void inject(BasePresenter b);

    void inject(SignUpFragment f);

    void inject(SignInFragment f);

    void inject(BidsFragment f);

    void inject(ProfileFragment f);

    void inject(ActiveBidsFragment f);

    void inject(ArchiveBidsFragment f);

    void inject(CreateBidFragment f);

    void inject(BidFragment f);
}