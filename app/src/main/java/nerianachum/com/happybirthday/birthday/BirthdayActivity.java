package nerianachum.com.happybirthday.birthday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Random;

import nerianachum.com.happybirthday.R;

public class BirthdayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);

        Random r = new Random();
        int i1 = r.nextInt(3);
    }
}
