package nerianachum.com.happybirthday.birthday;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.Calendar;
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
    @Inject ResourcesUtils resourcesUtils;
    @Inject CalendarUtils calendarUtils;

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
        if (user.getProfilePicture() == null) {
            setProfilePicturePlaceholder(layoutVersion);
        }
        setBackground(layoutVersion, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                /*
                 * Profile picture starts loading only when background is set to avoid "odd" loading
                 */
                if (user.getProfilePicture() != null) {
                    setProfilePicture();
                }
            }

            @Override
            public void onError() {

            }
        });

        setAgeViews();
    }

    private void setBackground(int layoutVersion, com.squareup.picasso.Callback callback) {
        @DrawableRes int background = resourcesUtils.getBackgroundForVersion(layoutVersion);
        birthdayView.setBackground(Picasso.with(this).load(background), callback);
    }

    private void setProfilePicture() {
        birthdayView.setProfilePicture(Picasso.with(BirthdayActivity.this).load(user.getProfilePicture()));
    }

    private void setProfilePicturePlaceholder(int layoutVersion) {
        @DrawableRes int profilePicturePlaceholder = resourcesUtils.getDefaultPlaceholderForVersion(layoutVersion);
        birthdayView.setProfilePicture(Picasso.with(BirthdayActivity.this).load(profilePicturePlaceholder));
    }

    private void setAgeViews() {
        birthdayView.setSwirlsVisibility(View.INVISIBLE);
        birthdayView.setPeriodUnitLabelVisibility(View.INVISIBLE);
        birthdayView.setNameLabelVisibility(View.INVISIBLE);
        birthdayView.setShareButtonVisibility(View.INVISIBLE);
        
        int ageInMonths = calendarUtils.getTimeDifferenceInMonths(user.getDateOfBirth(), Calendar.getInstance());
        String[] digitsDrawableIds = resourcesUtils.getDigitDrawablesForAge(ageInMonths);

        RequestCreator digit1RequestCreator = null;
        if (digitsDrawableIds[0] != null) {
            @DrawableRes int digit1Drawable = getResources()
                    .getIdentifier(digitsDrawableIds[0], "drawable", getPackageName());
            digit1RequestCreator = Picasso.with(this).load(digit1Drawable);
        }
        @DrawableRes int digit2Drawable = getResources()
                .getIdentifier(digitsDrawableIds[1], "drawable", getPackageName());
        RequestCreator digit2RequestCreator = Picasso.with(this).load(digit2Drawable);
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

        String periodUnit = getString(resourcesUtils.getAgeUnitStringForAge(ageInMonths));
        birthdayView.setAgeUnitLabelText(getString(R.string.view_birthday_period_unit, periodUnit));
    }

    @Override
    public void onBackButtonClicked() {
        supportFinishAfterTransition();
    }
}
