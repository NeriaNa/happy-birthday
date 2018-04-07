package app;

import android.app.Application;

import di.components.AppComponent;
import di.components.DaggerAppComponent;
import di.modules.AppModule;
import di.modules.UtilitiesModule;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .utilitiesModule(new UtilitiesModule())
                .build();

        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
