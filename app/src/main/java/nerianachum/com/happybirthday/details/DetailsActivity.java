package nerianachum.com.happybirthday.details;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.format.DateFormat;
import android.view.LayoutInflater;

import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.Locale;

import nerianachum.com.happybirthday.BasePresenter;
import nerianachum.com.happybirthday.R;
import nerianachum.com.happybirthday.birthday.BirthdayActivity;
import pojos.User;

public class DetailsActivity extends BasePresenter
        implements DetailsView.DetailsViewListener,
        DatePickerDialog.OnDateSetListener {

    private DetailsView detailsView;

    private Uri selectedImageUri;
    private Calendar selectedDateOfBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detailsView = new DetailsViewImpl(LayoutInflater.from(this), null, this);
        detailsView.setListener(this);

        setDismissKeyboardOnFocusChangeListener();

        if (savedInstanceState != null) {
            String restoredSelectedImageUri = savedInstanceState.getString(getString(R.string.profile_picture_uri));
            if (restoredSelectedImageUri != null) {
                selectedImageUri = Uri.parse(restoredSelectedImageUri);
                detailsView.setProfilePicture(Picasso.with(this).load(selectedImageUri));
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(getString(R.string.profile_picture_uri),
                selectedImageUri == null ? null : selectedImageUri.toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    //region DetailsViewListener
    @Override
    public void onFullNameChanged(CharSequence fullName) {
        boolean fullNameEmpty = fullName == null || fullName.length() == 0;
        boolean dateOfBirthEmpty = detailsView.getDateOfBirthInput() == null
                || detailsView.getDateOfBirthInput().length() == 0;
        detailsView.setShowBirthdayScreenButtonEnabled(!fullNameEmpty && !dateOfBirthEmpty);
    }

    @Override
    public void onDateOfBirthChanged(CharSequence dateOfBirth) {
        boolean fullNameEmpty = detailsView.getFullNameInput() == null
                || detailsView.getFullNameInput().length() == 0;
        boolean dateOfBirthEmpty = dateOfBirth == null || dateOfBirth.length() == 0;
        detailsView.setShowBirthdayScreenButtonEnabled(!fullNameEmpty && !dateOfBirthEmpty);
    }

    @Override
    public void onShowBirthdayScreenClicked() {
        Intent intent = new Intent(DetailsActivity.this, BirthdayActivity.class);
        User user = new User(detailsView.getFullNameInput().toString().trim(), selectedDateOfBirth, selectedImageUri);
        intent.putExtra(getString(R.string.user), user);
        startActivity(intent);
    }

    @Override
    public void onProfilePictureEditClicked() {
        CropImage.startPickImageActivity(this);
    }

    @Override
    public void onDateOfBirthClicked() {
        DatePickerDialog dpd = DatePickerDialog.newInstance(DetailsActivity.this,
                selectedDateOfBirth != null ? selectedDateOfBirth : Calendar.getInstance()
        );
        dpd.vibrate(false);
        dpd.setVersion(DatePickerDialog.Version.VERSION_1);
        dpd.showYearPickerFirst(true);
        dpd.setTitle("Date of birth");

        dpd.show(getFragmentManager(), getString(R.string.date_of_birth_date_picker));
    }
    //endregion

    //region Permissions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (requestCode == CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (selectedImageUri != null) {
                    CropImage.activity(selectedImageUri).setGuidelines(CropImageView.Guidelines.ON)
                            .setAspectRatio(1, 1)
                            .start(DetailsActivity.this);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE:
                    this.selectedImageUri = CropImage.getPickImageResultUri(this, data);

                    // For API >= 23 we need to ensure that we have permissions to read external storage.
                    if (CropImage.isReadExternalStoragePermissionsRequired(this, selectedImageUri)) {
                        // Request permissions and handle the result in onRequestPermissionsResult()
                        ActivityCompat.requestPermissions(DetailsActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
                    } else {
                        // No permissions required or already granted, can start crop image activity
                        CropImage.activity(selectedImageUri).setGuidelines(CropImageView.Guidelines.ON)
                                .setAspectRatio(1, 1)
                                .start(DetailsActivity.this);
                    }
                    break;
                case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                    this.selectedImageUri = CropImage.getActivityResult(data).getUri();
                    detailsView.setProfilePicture(Picasso.with(this).load(selectedImageUri));
                    break;
            }
        } else if (resultCode == RESULT_CANCELED) {
            switch (requestCode) {
                case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                    selectedImageUri = null;
                    break;
            }
        }
    }
    //endregion

    //region DatePickerDialog.OnDateSetListener
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        selectedDateOfBirth = Calendar.getInstance();
        selectedDateOfBirth.set(year, monthOfYear, dayOfMonth);

        String dateOdBirthText = !DateFormat.is24HourFormat(getApplicationContext())
                ? String.format(Locale.getDefault(), "%02d/%02d/%d", monthOfYear + 1, dayOfMonth, year)
                : String.format(Locale.getDefault(), "%02d/%02d/%d", dayOfMonth, monthOfYear + 1, year);
        detailsView.setDateOfBirthLabelText(dateOdBirthText);
    }
    //endregion
}
