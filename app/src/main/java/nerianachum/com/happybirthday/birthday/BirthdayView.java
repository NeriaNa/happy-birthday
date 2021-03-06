package nerianachum.com.happybirthday.birthday;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.squareup.picasso.RequestCreator;

import nerianachum.com.happybirthday.MvpView;

public interface BirthdayView extends MvpView {

    interface BirthdayViewListener {
        void onBackButtonClicked();
    }

    void setNameLabelText(CharSequence text);
    void setBackground(@NonNull RequestCreator requestCreator, com.squareup.picasso.Callback callback);
    void setProfilePicture(@NonNull RequestCreator requestCreator);
    void setAgeImages(@Nullable RequestCreator digit1RequestCreator,
                      @NonNull RequestCreator digit2RequestCreator,
                      com.squareup.picasso.Callback callback);
    void setAgeUnitLabelText(CharSequence text);

    void setSwirlsVisibility(int visibility);
    void setPeriodUnitLabelVisibility(int visibility);
    void setNameLabelVisibility(int visibility);
    void setShareButtonVisibility(int visibility);

    /**
     * Set a listener that will be notified by this view
     * @param listener listener that should be notified; null to clear
     */
    void setListener(BirthdayViewListener listener);
}
