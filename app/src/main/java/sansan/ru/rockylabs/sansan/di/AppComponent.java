package sansan.ru.rockylabs.sansan.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import sansan.ru.rockylabs.sansan.MVP.models.ModelImpl;
import sansan.ru.rockylabs.sansan.MVP.presenters.BasePresenter;
import sansan.ru.rockylabs.sansan.MVP.presenters.SplashPresenter;
import sansan.ru.rockylabs.sansan.ui.activities.SplashActivity;
import sansan.ru.rockylabs.sansan.di.modules.ContextModule;
import sansan.ru.rockylabs.sansan.di.modules.ModelModule;
import sansan.ru.rockylabs.sansan.di.modules.PresenterModule;
import sansan.ru.rockylabs.sansan.ui.fragments.BidsFragment;
import sansan.ru.rockylabs.sansan.ui.fragments.SignInFragment;
import sansan.ru.rockylabs.sansan.ui.fragments.SignUpFragment;

/**
 * Created by Zinnur on 19.12.16.
 */

@Singleton
@Component(modules = {PresenterModule.class, ContextModule.class, ModelModule.class})
public interface AppComponent {

    Context getContext();

    void inject(SplashPresenter p);

    void inject(SplashActivity a);

    void inject(ModelImpl model);

    void inject(BasePresenter basePresenter);

    void inject(SignUpFragment f);

    void inject(SignInFragment f);

    void inject(BidsFragment f);


}