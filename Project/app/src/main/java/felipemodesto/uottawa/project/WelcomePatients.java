package felipemodesto.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class WelcomePatients extends AppCompatActivity {

    private TextView UserNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_patients);
        UserNameTextView=(TextView)findViewById(R.id.name);
        UserNameTextView.setText(MainActivity.currentusername);
    }
}
