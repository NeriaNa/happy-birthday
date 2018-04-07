package nerianachum.com.happybirthday.birthday;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import nerianachum.com.happybirthday.BasePresenter;
import nerianachum.com.happybirthday.R;
import pojos.User;
import utils.CalendarUtils;
import utils.ResourcesUtils;

public class BirthdayActivity extends BasePresenter {

    private BirthdayView birthdayView;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);

        birthdayView = new BirthdayViewImpl(LayoutInflater.from(this), null, this);

        user = super.extras.getParcelable(getString(R.string.user));
        if (user != null) {
            birthdayView.setNameLabelText(getString(R.string.view_birthday_name, user.getFullName().toUpperCase()));
        }

        setRandomDesign();
        setAgeViews();
    }

    private void setRandomDesign() {
        Random r = new Random();
        int layoutVersion = r.nextInt(3);

        @DrawableRes int background, profilePicturePlaceholder;
        switch (layoutVersion) {
            case 0:
                background = R.drawable.android_elephant_popup;
                profilePicturePlaceholder = R.drawable.default_place_holder_yellow;
                break;
            case 1:
                background = R.drawable.android_fox_popup;
                profilePicturePlaceholder = R.drawable.default_place_holder_green;
                break;
            default:
                background = R.drawable.android_pelican_popup;
                profilePicturePlaceholder = R.drawable.default_place_holder_blue;
                break;
        }
        if (user.getProfilePicture() != null) {
            birthdayView.setProfilePicture(Picasso.with(this).load(user.getProfilePicture()));
        } else {
            birthdayView.setProfilePicture(Picasso.with(this).load(profilePicturePlaceholder));
        }
        birthdayView.setBackground(Picasso.with(this).load(background));
    }

    private void setAgeViews() {
        int ageInMonths = CalendarUtils.getTimeDifferenceInMonths(user.getDateOfBirth(), Calendar.getInstance());

        List<Integer> digitsDrawables = ResourcesUtils.getDigitDrawablesForAge(this, ageInMonths);
        RequestCreator digit1RequestCreator = digitsDrawables.get(0) == 0 ? null : Picasso.with(this).load(digitsDrawables.get(0));
        RequestCreator digit2RequestCreator = Picasso.with(this).load(digitsDrawables.get(1));
        birthdayView.setAgeImages(digit1RequestCreator, digit2RequestCreator);

        String periodUnit = ResourcesUtils.getAgeUnitStringForAge(this, ageInMonths);
        birthdayView.setAgeUnitLabelText(getString(R.string.view_birthday_period_unit, periodUnit));
    }
}
