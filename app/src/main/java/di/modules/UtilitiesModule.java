package di.modules;

import android.app.Application;

import java.util.Random;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import utils.CalendarUtils;
import utils.ResourcesUtils;

@Module
public class UtilitiesModule {
    @Provides
    @Singleton
    public Random provideRandom() {
        return new Random();
    }

    @Provides
    @Singleton
    public ResourcesUtils provideResourcesUtils(Application application) {
        return new ResourcesUtils(application);
    }

    @Provides
    @Singleton
    public CalendarUtils provideCalendarUtils() {
        return new CalendarUtils();
    }
}
