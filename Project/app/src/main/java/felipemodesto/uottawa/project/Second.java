package felipemodesto.uottawa.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Second extends AppCompatActivity {
    private EditText email;
    private EditText passward;
    private FirebaseAuth mAuth;
    private EditText Gender;
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
        int b =R.id.check_employee;
        int c =R.id.check_patients;
        if(pos==b){
            return "Employee";
        }else if(pos==c){
            return "Patients";
        }
        return "Empty";
    }
    public String getGender() {
        RadioGroup status = (RadioGroup) findViewById(R.id.genderSelect);
        int pos = status.getCheckedRadioButtonId();
        int b =R.id.check_male;
        int c =R.id.check_female;
        if(pos==b){
            return "male";
        }else if(pos==c){
            return "female";
        }
        return "Empty";
    }

    public void openAdmin() {
        Intent intent = new Intent(getApplicationContext(), welcomeadmin.class);
        startActivity(intent);
    }


    public void Onclick_signUp(View v) {
        String email = getUserEmail();
        String passward = getUserPass();
        String username = getUsername();
        String status = getStatus();
        String gender = getGender();
        if (status.equals("Empty")) {
            Toast.makeText(Second.this, "Status Should be choosen", Toast.LENGTH_LONG).show();
           
        } else if (email.equals("")) {
            Toast.makeText(Second.this, "Email/Password is empty", Toast.LENGTH_LONG).show();
        } else if (!(Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            Toast.makeText(Second.this, "Email is invalid", Toast.LENGTH_LONG).show();
        } else if (passward.equals("")) {
            Toast.makeText(Second.this, "Email/Password is empty", Toast.LENGTH_LONG).show();
        } else if (username.equals("")) {
            Toast.makeText(Second.this, "Username is empty", Toast.LENGTH_LONG).show();
        } else if (gender.equals("Empty")) {
            Toast.makeText(Second.this, "Gender should be choosen", Toast.LENGTH_LONG).show();
        } else {
            final User result  = new User(gender, username, status);
            mAuth.createUserWithEmailAndPassword(email, passward).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        mDatabase.getReference("Users")
                                .child(mAuth.getCurrentUser().getUid()).setValue(result);
                        back();
                    } else {
                        Toast.makeText(Second.this, "Database Error:(", Toast.LENGTH_LONG).show();
                    }


                }
            });
        }
    }
    };
