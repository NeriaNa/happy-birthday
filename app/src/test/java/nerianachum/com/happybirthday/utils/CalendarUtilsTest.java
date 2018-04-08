package nerianachum.com.happybirthday.utils;

import org.junit.Rule;
import org.junit.Test;

import java.util.Calendar;

import app.App;
import di.modules.AppModule;
import di.modules.UtilitiesModule;
import it.cosenonjaviste.daggermock.DaggerMockRule;
import nerianachum.com.happybirthday.di.components.TestAppComponent;
import utils.CalendarUtils;

import static org.junit.Assert.assertEquals;

/*
 * Reference: https://medium.com/@fabioCollini/android-testing-using-dagger-2-mockito-and-a-custom-junit-rule-c8487ed01b56
 */
public class CalendarUtilsTest {

    @Rule
    public DaggerMockRule<TestAppComponent> rule = new DaggerMockRule<>(TestAppComponent.class,
            new AppModule(new App()), new UtilitiesModule())
                    .set(component -> calendarUtils = component.calendarUtils());

    private CalendarUtils calendarUtils;

    @Test
    public void getTimeDifferenceInMonths_plus12Months_returns12() {
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = ((Calendar)startTime.clone());
        endTime.add(Calendar.MONTH, 12);
        int result = calendarUtils.getTimeDifferenceInMonths(startTime, endTime);
        assertEquals(12, result);
    }
    @Test
    public void getTimeDifferenceInMonths_minus1Month_swapsInputs_returns1() {
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = ((Calendar)startTime.clone());
        endTime.add(Calendar.MONTH, -1);
        int result = calendarUtils.getTimeDifferenceInMonths(startTime, endTime);
        assertEquals(1, result);
    }

    @Test
    public void getTimeDifferenceInMonths_startTimeEqualsEndTime_returns0() {
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = ((Calendar)startTime.clone());
        int result = calendarUtils.getTimeDifferenceInMonths(startTime, endTime);
        assertEquals(0, result);
    }

    @Test
    public void getTimeDifferenceInMonths_plusOneDate_returns0() {
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = ((Calendar)startTime.clone());
        endTime.add(Calendar.DATE, 1);
        int result = calendarUtils.getTimeDifferenceInMonths(startTime, endTime);
        assertEquals(0, result);
    }

    @Test
    public void getTimeDifferenceInMonths_minusOneDate_returns0() {
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = ((Calendar)startTime.clone());
        endTime.add(Calendar.DATE, -1);
        int result = calendarUtils.getTimeDifferenceInMonths(startTime, endTime);
        assertEquals(0, result);
    }
}