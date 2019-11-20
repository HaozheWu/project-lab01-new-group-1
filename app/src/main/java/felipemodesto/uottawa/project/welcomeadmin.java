package felipemodesto.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class welcomeadmin extends AppCompatActivity {
    private TextView UserNameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomeadmin);

        //edit

    }
    public void openmanagement(View view) {
        openManagement();
    }

    public void openManagement() {
        Intent intent = new Intent(getApplicationContext(), ManageAccount.class);
        startActivity(intent);
    }

    public void openService(View view) {
        openService();
    }

    public void logout() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void logout(View view) {
        logout();
    }

    public void openService() {
        Intent intent = new Intent(getApplicationContext(), service.class);
        startActivity(intent);
    }


}
