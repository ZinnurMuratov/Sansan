package sansan.ru.rockylabs.sansan.di;

import android.app.Application;

import sansan.ru.rockylabs.sansan.di.modules.ContextModule;

/**
 * Created by Zinnur on 19.12.16.
 */

public class App extends Application {
    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }


}