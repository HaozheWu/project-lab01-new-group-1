package felipemodesto.uottawa.project;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
public class welcomepatient extends AppCompatActivity{
    private TextView UserNameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepatient);
        UserNameTextView = (TextView) findViewById(R.id.textView4);
        UserNameTextView.setText(MainActivity.currentusername);
    }
}
