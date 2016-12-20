package sansan.ru.rockylabs.sansan.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;
import sansan.ru.rockylabs.sansan.MVP.models.Model;
import sansan.ru.rockylabs.sansan.MVP.models.ModelImpl;

/**
 * Created by Zinnur on 19.12.16.
 */

@Module
public class PresenterModule {

    @Provides
    @Singleton
    Model provideModel() {
        return new ModelImpl();
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() { return new CompositeSubscription();}
}