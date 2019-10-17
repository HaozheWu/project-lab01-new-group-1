package felipemodesto.uottawa.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import felipemodesto.uottawa.project.R;

public class Second extends AppCompatActivity {
    private EditText email;
    private EditText passward;
    private FirebaseAuth mAuth;
    private static final String TAG = "Second";
    FirebaseDatabase mDatabase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
    }
    public void back() {
        Intent register = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(register);
    }
    public String getUserEmail() {
        EditText et1 = (EditText) findViewById(R.id.email);
        String email = et1.getText().toString();
        return email;
    }

    public String getUserPass() {
        EditText et2 = (EditText) findViewById(R.id.passward);
        String password = et2.getText().toString();
        return password;
    }

    public String getUsername() {
        EditText et1 = (EditText) findViewById(R.id.username);
        String email = et1.getText().toString();
        return email;
    }

    public String getStatus() {
            RadioGroup status = (RadioGroup) findViewById(R.id.statusSelect);
            int pos = status.getCheckedRadioButtonId();
            if(pos==0){
                return "employee";
            }else{
                return "patient";
            }

    }

    public void openAdmin() {
        Intent intent = new Intent(getApplicationContext(), welcomeadmin.class);
        startActivity(intent);
    }


    public void Onclick_signUp(View v) {
        String email = getUserEmail();
        String passward = getUserPass();
        String username = getUsername();
        String status =  getStatus();
        if(status==null){
            Toast.makeText(Second.this, "Invalid user Status Input, Input Yes or No", Toast.LENGTH_LONG).show();
        }
        else if (email.equals("")) {
            Toast.makeText(Second.this, "Email/Password is empty", Toast.LENGTH_LONG).show();
        } else if ((Patterns.EMAIL_ADDRESS.matcher(email).matches()) == false) {
            Toast.makeText(Second.this, "Email is invalid", Toast.LENGTH_LONG).show();
        } else if(passward.length()<6){
            Toast.makeText(Second.this, "Password is too short", Toast.LENGTH_LONG).show();
        } else if (passward.equals("")) {
            Toast.makeText(Second.this, "Email/Password is empty", Toast.LENGTH_LONG).show();
        } else if (username.equals("")) {
            Toast.makeText(Second.this, "Username is empty", Toast.LENGTH_LONG).show();
        } else {
            final User oneuser = new User(email, passward, username, status);
            openAdmin();
            mAuth.createUserWithEmailAndPassword(email, passward).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        mDatabase.getReference("Users")
                                .child(mAuth.getCurrentUser().getUid()).setValue(oneuser);
                        back();
                    }


                }
            });
        }

    }}
