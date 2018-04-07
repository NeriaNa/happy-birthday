package utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.DrawableRes;

import java.util.Arrays;
import java.util.List;

import nerianachum.com.happybirthday.R;

/*
 * Utility class for retrieving drawables using complex logic
 */
public final class ResourcesUtils {

    private ResourcesUtils()
    {

    }

    public static List<Integer> getDigitDrawablesForAge(Context context, int ageInMonths) {
        Resources res = context.getResources();
        String packageName = context.getPackageName();
        @DrawableRes int digit1DrawableId = 0, digit2DrawableId;
        if (ageInMonths < 12) {
            if (ageInMonths / 10 > 0) {
                digit1DrawableId = res.getIdentifier("a" + ageInMonths / 10, "drawable", packageName);
            }
            digit2DrawableId = res.getIdentifier("a" + ageInMonths % 10, "drawable", packageName);
        } else if (ageInMonths >= 18 && ageInMonths < 24) {
            digit2DrawableId = res.getIdentifier("a1_half", "drawable", packageName);
        } else if (ageInMonths >= 100 * 12) {
            digit1DrawableId = res.getIdentifier("a9", "drawable", packageName);
            digit2DrawableId = res.getIdentifier("a9", "drawable", packageName);
        } else {
            if (ageInMonths / 12 / 10 > 0) {
                digit1DrawableId = res.getIdentifier("a" + ageInMonths / 12 / 10, "drawable", packageName);
            }
            digit2DrawableId = res.getIdentifier("a" + ageInMonths / 12 % 10, "drawable", packageName);
        }

        return Arrays.asList(digit1DrawableId, digit2DrawableId);
    }

    public static String getAgeUnitStringForAge(Context context, int ageInMonths) {
        String periodUnit;
        if (ageInMonths == 1) {
            periodUnit = context.getString(R.string.view_birthday_month);
        } else if (ageInMonths < 12) {
            periodUnit = context.getString(R.string.view_birthday_months);
        } else if (ageInMonths < 18) {
            periodUnit = context.getString(R.string.view_birthday_year);
        } else {
            periodUnit = context.getString(R.string.view_birthday_years);
        }

        return periodUnit;
    }
}
