package felipemodesto.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static felipemodesto.uottawa.project.MainActivity.currentusername;

public class welcomeemployee extends AppCompatActivity {
    private TextView UserNameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomeemployee);
        UserNameTextView=(TextView)findViewById(R.id.name);
        UserNameTextView.setText(currentusername);
        //edit

    }
    public void Onclick_signUp(View v) {
        Intent register = new Intent(getApplicationContext(),employeecenter.class);
        startActivity(register);
    }
    public void back(View v) {
        Intent register = new Intent(getApplicationContext(), MainActivity.class);
        register.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(register);
    }

}
