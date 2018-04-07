package di.modules;

import java.util.Random;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilitiesModule {
    @Provides
    @Singleton
    public Random provideRandom() {
        return new Random();
    }
}
