package nerianachum.com.happybirthday.birthday;

import com.squareup.picasso.RequestCreator;

import nerianachum.com.happybirthday.MvpView;

public interface BirthdayView extends MvpView {

    void setNameLabelText(CharSequence text);
    void setBackground(RequestCreator requestCreator);
    void setProfilePicture(RequestCreator requestCreator);
}
