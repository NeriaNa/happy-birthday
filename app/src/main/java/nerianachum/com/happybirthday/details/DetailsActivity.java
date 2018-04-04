package nerianachum.com.happybirthday.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.Toast;

import nerianachum.com.happybirthday.R;

public class DetailsActivity extends AppCompatActivity implements DetailsView.DetailsViewListener {

    private DetailsView detailsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detailsView = new DetailsViewImpl(LayoutInflater.from(this), null, this);
        detailsView.setListener(this);
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
}
