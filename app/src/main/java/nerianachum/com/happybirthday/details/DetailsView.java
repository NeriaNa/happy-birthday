package nerianachum.com.happybirthday.details;

import com.squareup.picasso.RequestCreator;

import nerianachum.com.happybirthday.MvpView;

public interface DetailsView extends MvpView {

    interface DetailsViewListener {
        void onFullNameChanged(CharSequence fullName);
        void onDateOfBirthChanged(CharSequence dateOfBirth);
        void onShowBirthdayScreenClicked();
        void onProfilePictureEditClicked();
    }

    CharSequence getFullNameInput();
    CharSequence getDateOfBirthInput();

    void setProfilePicture(RequestCreator requestCreator);
    void setShowBirthdayScreenButtonEnabled(boolean enabled);

    /**
     * Set a listener that will be notified by this view
     * @param listener listener that should be notified; null to clear
     */
    void setListener(DetailsViewListener listener);

}
