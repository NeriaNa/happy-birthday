package nerianachum.com.happybirthday.di.components;

import javax.inject.Singleton;

import dagger.Component;
import di.components.AppComponent;
import di.modules.AppModule;
import di.modules.UtilitiesModule;
import utils.CalendarUtils;

@Singleton
@Component(modules = {AppModule.class, UtilitiesModule.class})
public interface TestAppComponent extends AppComponent {
    CalendarUtils calendarUtils();
}