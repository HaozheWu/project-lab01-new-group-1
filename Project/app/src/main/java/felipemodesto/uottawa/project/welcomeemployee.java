package felipemodesto.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class welcomeemployee extends AppCompatActivity {
    private TextView UserNameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomeadmin);
        UserNameTextView=(TextView)findViewById(R.id.textView2);
        UserNameTextView.setText(MainActivity.currentusername);
        //edit

    }
}
