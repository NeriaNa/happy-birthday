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

import javax.inject.Inject;

import nerianachum.com.happybirthday.BasePresenter;
import nerianachum.com.happybirthday.R;
import pojos.User;
import utils.CalendarUtils;
import utils.ResourcesUtils;

public class BirthdayActivity extends BasePresenter implements BirthdayView.BirthdayViewListener {

    private BirthdayView birthdayView;
    private User user;

    @Inject Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);

        getApp().getAppComponent().presenterComponent().inject(this);

        birthdayView = new BirthdayViewImpl(LayoutInflater.from(this), null, this);
        birthdayView.setListener(this);

        user = super.extras.getParcelable(getString(R.string.user));
        if (user != null) {
            birthdayView.setNameLabelText(getString(R.string.view_birthday_name, user.getFullName().toUpperCase()));
        }

        int layoutVersion = random.nextInt(3);

        birthdayView.setProfilePictureVisibility(View.GONE);
        setBackground(layoutVersion, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                /*
                 * Profile picture is hidden until background is loaded to avoid "odd" loading
                 */
                birthdayView.setProfilePictureVisibility(View.VISIBLE);
            }

            @Override
            public void onError() {

            }
        });
        setProfilePicture(layoutVersion);
        setAgeViews();
    }

    private void setBackground(int layoutVersion, com.squareup.picasso.Callback callback) {
        @DrawableRes int background;
        switch (layoutVersion) {
            case 0:
                background = R.drawable.android_elephant_popup;
                break;
            case 1:
                background = R.drawable.android_fox_popup;
                break;
            default:
                background = R.drawable.android_pelican_popup;
                break;
        }

        birthdayView.setBackground(Picasso.with(this).load(background), callback);
    }

    private void setProfilePicture(int layoutVersion) {
        if (user.getProfilePicture() != null) {
            birthdayView.setProfilePicture(Picasso.with(BirthdayActivity.this).load(user.getProfilePicture()));
        } else {
            @DrawableRes int profilePicturePlaceholder;
            switch (layoutVersion) {
                case 0:
                    profilePicturePlaceholder = R.drawable.default_place_holder_yellow;
                    break;
                case 1:
                    profilePicturePlaceholder = R.drawable.default_place_holder_green;
                    break;
                default:
                    profilePicturePlaceholder = R.drawable.default_place_holder_blue;
                    break;
            }
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
