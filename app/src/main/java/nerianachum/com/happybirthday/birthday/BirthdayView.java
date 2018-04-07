package nerianachum.com.happybirthday.birthday;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.squareup.picasso.RequestCreator;

import nerianachum.com.happybirthday.MvpView;

public interface BirthdayView extends MvpView {

    void setNameLabelText(CharSequence text);
    void setBackground(@NonNull RequestCreator requestCreator);
    void setProfilePicture(@NonNull RequestCreator requestCreator);
    void setAgeImages(@Nullable RequestCreator digit1RequestCreator, @NonNull RequestCreator digit2RequestCreator);
    void setAgeUnitLabelText(CharSequence text);
}
