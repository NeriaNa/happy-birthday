package di.components;

import javax.inject.Singleton;

import app.App;
import dagger.Component;
import di.modules.AppModule;
import di.modules.UtilitiesModule;

@Singleton
@Component(modules = {AppModule.class, UtilitiesModule.class})
public interface AppComponent {

    PresenterComponent presenterComponent();

    void inject(App app);
}