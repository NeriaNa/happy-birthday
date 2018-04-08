package utils;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import nerianachum.com.happybirthday.R;

/*
 * Utility class for retrieving drawables using complex logic
 */
public final class ResourcesUtils {

    public ResourcesUtils() {
    }

    public String[] getDigitDrawablesForAge(int ageInMonths) {
        String digit1DrawableId = null, digit2DrawableId;
        if (ageInMonths < 0) {
            digit2DrawableId = "a0";
        } else if (ageInMonths < 12) {
            if (ageInMonths / 10 > 0) {
                digit1DrawableId = "a" + ageInMonths / 10;
            }
            digit2DrawableId = "a" + ageInMonths % 10;
        } else if (ageInMonths >= 18 && ageInMonths < 24) {
            digit2DrawableId = "a1_half";
        } else if (ageInMonths >= 100 * 12) {
            digit1DrawableId = "a9";
            digit2DrawableId = "a9";
        } else {
            if (ageInMonths / 12 / 10 > 0) {
                digit1DrawableId = "a" + ageInMonths / 12 / 10;
            }
            digit2DrawableId = "a" + ageInMonths / 12 % 10;
        }

        return new String[]{digit1DrawableId, digit2DrawableId};
    }

    public @StringRes int getAgeUnitStringForAge(int ageInMonths) {
        @StringRes int periodUnit;
        if (ageInMonths == 1) {
            periodUnit = R.string.view_birthday_month;
        } else if (ageInMonths < 12) {
            periodUnit = R.string.view_birthday_months;
        } else if (ageInMonths < 18) {
            periodUnit = R.string.view_birthday_year;
        } else {
            periodUnit = R.string.view_birthday_years;
        }

        return periodUnit;
    }

    public @DrawableRes int getBackgroundForVersion(int layoutVersion) {
        switch (layoutVersion) {
            case 0:
                return R.drawable.android_elephant_popup;
            case 1:
                return R.drawable.android_fox_popup;
            default:
                return R.drawable.android_pelican_popup;
        }
    }

    public @DrawableRes int getDefaultPlaceholderForVersion(int layoutVersion) {
        switch (layoutVersion) {
            case 0:
                return R.drawable.default_place_holder_yellow;
            case 1:
                return R.drawable.default_place_holder_green;
            default:
                return R.drawable.default_place_holder_blue;
        }
    }
}
