package nerianachum.com.happybirthday.birthday;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;

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

public class BirthdayActivity extends BasePresenter implements BirthdayView.BirthdayViewListener {

    private BirthdayView birthdayView;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);

        birthdayView = new BirthdayViewImpl(LayoutInflater.from(this), null, this);
        birthdayView.setListener(this);

        user = super.extras.getParcelable(getString(R.string.user));
        if (user != null) {
            birthdayView.setNameLabelText(getString(R.string.view_birthday_name, user.getFullName().toUpperCase()));
        }

        setRandomDesign(new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                /*
                 * Start loading profile picture only when background is loaded, to make sure it
                 * doesn't show up before background
                 */
                if (user.getProfilePicture() != null) {
                    birthdayView.setProfilePicture(Picasso.with(BirthdayActivity.this).load(user.getProfilePicture()));
                }
            }

            @Override
            public void onError() {

            }
        });
        setAgeViews();
    }

    private void setRandomDesign(com.squareup.picasso.Callback callback) {
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

        birthdayView.setBackground(Picasso.with(this).load(background), callback);
        if (user.getProfilePicture() == null) {
            birthdayView.setProfilePicture(Picasso.with(BirthdayActivity.this).load(profilePicturePlaceholder));
        }
    }

    private void setAgeViews() {
        birthdayView.setSwirlsVisibility(View.GONE);
        birthdayView.setPeriodUnitLabelVisibility(View.GONE);
        birthdayView.setNameLabelVisibility(View.GONE);
        birthdayView.setShareButtonVisibility(View.GONE);
        int ageInMonths = CalendarUtils.getTimeDifferenceInMonths(user.getDateOfBirth(), Calendar.getInstance());

        List<Integer> digitsDrawables = ResourcesUtils.getDigitDrawablesForAge(this, ageInMonths);
        RequestCreator digit1RequestCreator = digitsDrawables.get(0) == 0
                ? null : Picasso.with(this).load(digitsDrawables.get(0));
        RequestCreator digit2RequestCreator = Picasso.with(this).load(digitsDrawables.get(1));
        birthdayView.setAgeImages(digit1RequestCreator, digit2RequestCreator,
                new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                birthdayView.setSwirlsVisibility(View.VISIBLE);
                birthdayView.setPeriodUnitLabelVisibility(View.VISIBLE);
                birthdayView.setNameLabelVisibility(View.VISIBLE);
                birthdayView.setShareButtonVisibility(View.VISIBLE);
            }

            @Override
            public void onError() {

            }
        });

        String periodUnit = ResourcesUtils.getAgeUnitStringForAge(this, ageInMonths);
        birthdayView.setAgeUnitLabelText(getString(R.string.view_birthday_period_unit, periodUnit));
    }

    @Override
    public void onBackButtonClicked() {
        supportFinishAfterTransition();
    }
}
