package nerianachum.com.happybirthday;

import app.App;

public interface MvpPresenter {
    App getApp();
    void hideKeyboard();
    void setDismissKeyboardOnFocusChangeListener();
}
