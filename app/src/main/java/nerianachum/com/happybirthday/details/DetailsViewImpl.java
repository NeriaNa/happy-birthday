package nerianachum.com.happybirthday.details;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.RequestCreator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import nerianachum.com.happybirthday.R;

public class DetailsViewImpl implements DetailsView {

    private View rootView;
    private DetailsViewListener listener;

    @BindView(R.id.view_details_profile_picture) CircularImageView profilePictureImageView;
    @BindView(R.id.view_details_full_name) AppCompatEditText fullNameEditText;
    @BindView(R.id.view_details_date_of_birth) AppCompatTextView dateOfBirthTextView;
    @BindView(R.id.view_details_show_birthday_screen) AppCompatButton showBirthdayScreenButton;

    public DetailsViewImpl(LayoutInflater inflater, ViewGroup container, Activity activity) {
        rootView = inflater.inflate(R.layout.activity_details, container, false);
        ButterKnife.bind(this, activity);
    }

    @OnClick(R.id.view_details_profile_picture_edit)
    public void onProfilePictureEditClicked() {
        if (listener != null) {
            listener.onProfilePictureEditClicked();
        }
    }

    @OnClick(R.id.view_details_date_of_birth)
    public void onDateOfBirthClicked() {
        if (listener != null) {
            listener.onDateOfBirthClicked();
        }
    }

    @OnClick(R.id.view_details_show_birthday_screen)
    public void onShowBirthdayScreenButtonClicked() {
        if (listener != null) {
            listener.onShowBirthdayScreenClicked();
        }
    }

    @OnTextChanged(value = R.id.view_details_full_name, callback = OnTextChanged.Callback.TEXT_CHANGED)
    void onFullNameInputChanged(CharSequence fullName, int start, int before, int count) {
        if (listener != null) {
            listener.onFullNameChanged(fullName);
        }
    }

    @OnTextChanged(value = R.id.view_details_date_of_birth, callback = OnTextChanged.Callback.TEXT_CHANGED)
    void onDateOfBirthInputChanged(CharSequence fullName, int start, int before, int count) {
        if (listener != null) {
            listener.onDateOfBirthChanged(fullName);
        }
    }

    @Override
    public CharSequence getFullNameInput() {
        return fullNameEditText.getText();
    }

    @Override
    public CharSequence getDateOfBirthInput() {
        return dateOfBirthTextView.getText();
    }

    @Override
    public void setProfilePicture(RequestCreator requestCreator) {
        requestCreator.into(profilePictureImageView);
    }

    @Override
    public void setDateOfBirthLabelText(CharSequence text) {
        dateOfBirthTextView.setText(text);
    }

    @Override
    public void setShowBirthdayScreenButtonEnabled(boolean enabled) {
        showBirthdayScreenButton.setEnabled(enabled);
    }

    @Override
    public void setListener(DetailsViewListener listener) {
        this.listener = listener;
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