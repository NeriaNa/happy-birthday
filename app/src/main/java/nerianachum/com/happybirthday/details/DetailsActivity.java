package nerianachum.com.happybirthday.details;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import nerianachum.com.happybirthday.R;

public class DetailsActivity extends AppCompatActivity implements DetailsView.DetailsViewListener {

    private DetailsView detailsView;

    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detailsView = new DetailsViewImpl(LayoutInflater.from(this), null, this);
        detailsView.setListener(this);

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

    @Override
    public void onFullNameChanged(CharSequence fullName) {
        boolean fullNameEmpty = fullName == null || fullName.length() == 0;
        boolean dateOfBirthEmpty = detailsView.getDateOfBirthInput() == null
                || detailsView.getDateOfBirthInput().length() == 0;
        detailsView.setShowBirthdayScreenButtonEnabled(!fullNameEmpty || !dateOfBirthEmpty);
    }

    @Override
    public void onDateOfBirthChanged(CharSequence dateOfBirth) {
        boolean fullNameEmpty = detailsView.getFullNameInput() == null
                || detailsView.getFullNameInput().length() == 0;
        boolean dateOfBirthEmpty = dateOfBirth == null || dateOfBirth.length() == 0;
        detailsView.setShowBirthdayScreenButtonEnabled(!fullNameEmpty || !dateOfBirthEmpty);
    }

    @Override
    public void onShowBirthdayScreenClicked() {
        Toast.makeText(this, "hi", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProfilePictureEditClicked() {
        CropImage.startPickImageActivity(this);
    }

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
}
