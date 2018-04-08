package nerianachum.com.happybirthday.di.modules;

import org.mockito.Mockito;

import di.modules.UtilitiesModule;
import utils.CalendarUtils;

public class TestUtilitiesModule extends UtilitiesModule {
    @Override
    public CalendarUtils provideCalendarUtils() {
        return Mockito.mock(CalendarUtils.class);
    }
}