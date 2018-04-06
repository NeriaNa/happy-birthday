package pojos;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

public class User implements Parcelable {
    //region 1. Properties
    private String fullName;
    private Calendar dateOfBirth;
    private Uri profilePicture;
    //endregion

    //region 2. Empty constructor
    public User() {

    }
    //endregion

    //region 3. Properties constructor
    public User(String fullName, Calendar dateOfBirth, Uri profilePicture) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.profilePicture = profilePicture;
    }
    //endregion

    //region 4. Copy constructor
    public User(User user) {
        this.fullName = user.getFullName();
        this.dateOfBirth = user.getDateOfBirth();
        this.profilePicture = user.getProfilePicture();
    }
    //endregion

    //region 5. Parcel constructor
    public User(Parcel in) {
        fullName = in.readString();
        dateOfBirth = (Calendar) in.readSerializable();
        profilePicture = Uri.parse(in.readString());
    }
    //endregion

    //region 6. Parcelable interface
    public final static Parcelable.Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return (new User[size]);
        }
    };

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fullName);
        dest.writeSerializable(dateOfBirth);
        dest.writeString(profilePicture.toString());
    }

    public int describeContents() {
        return 0;
    }
    //endregion

    //region 7. Getters + setters
    public synchronized String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        synchronized (this) {
            this.fullName = fullName;
        }
    }

    public synchronized Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setText(Calendar dateOfBirth) {
        synchronized (this) {
            this.dateOfBirth = dateOfBirth;
        }
    }

    public synchronized Uri getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Uri profilePicture) {
        synchronized (this) {
            this.profilePicture = profilePicture;
        }
    }
    //endregion
}
