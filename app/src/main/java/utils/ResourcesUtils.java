package utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.DrawableRes;

import nerianachum.com.happybirthday.R;

/*
 * Utility class for retrieving drawables using complex logic
 */
public final class ResourcesUtils {

    private Context context;

    public ResourcesUtils(Context context) {
        this.context = context;
    }

    public @DrawableRes int[] getDigitDrawablesForAge(int ageInMonths) {
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

        return new int[]{digit1DrawableId, digit2DrawableId};
    }

    public String getAgeUnitStringForAge(int ageInMonths) {
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
