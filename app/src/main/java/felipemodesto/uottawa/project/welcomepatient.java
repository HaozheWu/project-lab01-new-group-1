package felipemodesto.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class welcomepatient extends AppCompatActivity {

    private TextView UserNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepatient);
        UserNameTextView=(TextView)findViewById(R.id.name);
        UserNameTextView.setText(MainActivity.currentusername);
    }
    public void center(View view) {
        Intent intent = new Intent(getApplicationContext(),patientcenter.class);
        startActivity(intent);
    }
    public void back(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
    public void check(View view) {
        Intent intent = new Intent(getApplicationContext(),myappointment.class);
        startActivity(intent);
    }


}