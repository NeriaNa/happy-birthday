package nerianachum.com.happybirthday.utils;

import android.support.annotation.StringRes;

import org.junit.Rule;
import org.junit.Test;

import app.App;
import di.modules.AppModule;
import di.modules.UtilitiesModule;
import it.cosenonjaviste.daggermock.DaggerMockRule;
import nerianachum.com.happybirthday.R;
import nerianachum.com.happybirthday.di.components.TestAppComponent;
import utils.ResourcesUtils;

import static org.junit.Assert.assertEquals;

/*
 * Reference: https://medium.com/@fabioCollini/android-testing-using-dagger-2-mockito-and-a-custom-junit-rule-c8487ed01b56
 */
public class ResourcesUtilsTest {

    @Rule
    public DaggerMockRule<TestAppComponent> rule = new DaggerMockRule<>(TestAppComponent.class,
            new AppModule(new App()), new UtilitiesModule())
            .set(component -> resourcesUtils = component.resourcesUtils());

    private ResourcesUtils resourcesUtils;

    @Test
    public void getDigitDrawablesForAge_0_returns0() {
        String[] digitsDrawables = resourcesUtils.getDigitDrawablesForAge(0);
        assertEquals(null, digitsDrawables[0]);
        assertEquals("a0", digitsDrawables[1]);
    }

    @Test
    public void getDigitDrawablesForAge_minus1_returns0() {
        String[] digitsDrawables = resourcesUtils.getDigitDrawablesForAge(-1);
        assertEquals(null, digitsDrawables[0]);
        assertEquals("a0", digitsDrawables[1]);
    }

    @Test
    public void getDigitDrawablesForAge_1_returns1() {
        String[] digitsDrawables = resourcesUtils.getDigitDrawablesForAge(1);
        assertEquals(null, digitsDrawables[0]);
        assertEquals("a1", digitsDrawables[1]);
    }

    @Test
    public void getDigitDrawablesForAge_11_returns11() {
        String[] digitsDrawables = resourcesUtils.getDigitDrawablesForAge(11);
        assertEquals("a1", digitsDrawables[0]);
        assertEquals("a1", digitsDrawables[1]);
    }

    @Test
    public void getDigitDrawablesForAge_12_returns1() {
        String[] digitsDrawables = resourcesUtils.getDigitDrawablesForAge(12);
        assertEquals(null, digitsDrawables[0]);
        assertEquals("a1", digitsDrawables[1]);
    }

    @Test
    public void getDigitDrawablesForAge_18_returns1half() {
        String[] digitsDrawables = resourcesUtils.getDigitDrawablesForAge(18);
        assertEquals(null, digitsDrawables[0]);
        assertEquals("a1_half", digitsDrawables[1]);
    }

    @Test
    public void getDigitDrawablesForAge_24_returns2() {
        String[] digitsDrawables = resourcesUtils.getDigitDrawablesForAge(24);
        assertEquals(null, digitsDrawables[0]);
        assertEquals("a2", digitsDrawables[1]);
    }

    @Test
    public void getDigitDrawablesForAge_120_returns10() {
        String[] digitsDrawables = resourcesUtils.getDigitDrawablesForAge(120);
        assertEquals("a1", digitsDrawables[0]);
        assertEquals("a0", digitsDrawables[1]);
    }

    @Test
    public void getDigitDrawablesForAge_largeNumber_returns99() {
        String[] digitsDrawables = resourcesUtils.getDigitDrawablesForAge(31415926);
        assertEquals("a9", digitsDrawables[0]);
        assertEquals("a9", digitsDrawables[1]);
    }




    @Test
    public void getAgeUnitStringForAge_1_returnsMonth() {
        @StringRes int ageUnit = resourcesUtils.getAgeUnitStringForAge(1);
        assertEquals(R.string.view_birthday_month, ageUnit);
    }

    @Test
    public void getAgeUnitStringForAge_2_returnsMonths() {
        @StringRes int ageUnit = resourcesUtils.getAgeUnitStringForAge(2);
        assertEquals(R.string.view_birthday_months, ageUnit);
    }

    @Test
    public void getAgeUnitStringForAge_12_returnsYear() {
        @StringRes int ageUnit = resourcesUtils.getAgeUnitStringForAge(12);
        assertEquals(R.string.view_birthday_year, ageUnit);
    }

    @Test
    public void getAgeUnitStringForAge_18_returnsYears() {
        @StringRes int ageUnit = resourcesUtils.getAgeUnitStringForAge(18);
        assertEquals(R.string.view_birthday_years, ageUnit);
    }

    @Test
    public void getAgeUnitStringForAge_24_returnsYears() {
        @StringRes int ageUnit = resourcesUtils.getAgeUnitStringForAge(24);
        assertEquals(R.string.view_birthday_years, ageUnit);
    }
}