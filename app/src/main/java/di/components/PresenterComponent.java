package di.components;

import dagger.Subcomponent;
import di.scopes.PresenterScope;
import nerianachum.com.happybirthday.birthday.BirthdayActivity;

@PresenterScope
@Subcomponent
public interface PresenterComponent {

    void inject(BirthdayActivity birthdayActivity);
}