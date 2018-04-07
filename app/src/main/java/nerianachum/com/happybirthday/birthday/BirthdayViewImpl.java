package nerianachum.com.happybirthday.birthday;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.RequestCreator;

import butterknife.BindView;
import butterknife.ButterKnife;
import nerianachum.com.happybirthday.R;

public class BirthdayViewImpl implements BirthdayView {

    private View rootView;

    @BindView(R.id.view_birthday_name) AppCompatTextView nameTextView;
    @BindView(R.id.view_birthday_profile_picture) AppCompatImageView profilePictureImageView;
    @BindView(R.id.view_birthday_background) AppCompatImageView backgroundImageView;
    @BindView(R.id.view_birthday_age1) AppCompatImageView ageDigit1ImageView;
    @BindView(R.id.view_birthday_age2) AppCompatImageView ageDigit2ImageView;
    @BindView(R.id.view_birthday_period_unit) AppCompatTextView periodUnitTextView;

    public BirthdayViewImpl(LayoutInflater inflater, ViewGroup container, Activity activity) {
        rootView = inflater.inflate(R.layout.activity_birthday, container, false);
        ButterKnife.bind(this, activity);
    }

    @Override
    public void setNameLabelText(CharSequence text) {
        nameTextView.setText(text);
    }

    @Override
    public void setBackground(@NonNull RequestCreator requestCreator) {
        requestCreator.into(backgroundImageView);
    }

    @Override
    public void setProfilePicture(@NonNull RequestCreator requestCreator) {
        requestCreator.into(profilePictureImageView);
    }

    @Override
    public void setAgeImages(RequestCreator digit1RequestCreator, @NonNull RequestCreator digit2RequestCreator) {
        if (digit1RequestCreator == null) {
            ageDigit1ImageView.setVisibility(View.GONE);
        } else {
            ageDigit1ImageView.setVisibility(View.VISIBLE);
            digit1RequestCreator.into(ageDigit1ImageView);
        }
        digit2RequestCreator.into(ageDigit2ImageView);
    }

    @Override
    public void setAgeUnitLabelText(CharSequence text) {
        periodUnitTextView.setText(text);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }
}
