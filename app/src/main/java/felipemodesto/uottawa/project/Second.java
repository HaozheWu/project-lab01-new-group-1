package felipemodesto.uottawa.project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.KeyStore;
import java.util.List;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Second extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private EditText Gender;
    DatabaseReference mReference;
    private static final String TAG = "Second";
    FirebaseDatabase mDatabase;
    DatabaseReference DatabaseUser;
    public User result;
    public static String idforsignup;
    boolean check= true;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mDatabase = FirebaseDatabase.getInstance();
        DatabaseUser=FirebaseDatabase.getInstance().getReference("Users");
    }
    public void back() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public String getUserEmail() {
        EditText et1 = (EditText) findViewById(R.id.email);
        String email = et1.getText().toString();
        return email;
    }

        public String getUserPass() {
       EditText et2 = (EditText) findViewById(R.id.password);
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
        int b = R.id.check_male;
        int c = R.id.check_female;
        if (pos == b) {
            return "male";
        } else if (pos == c) {
            return "female";
        }
        return "Empty";
    }

    public  static   boolean   test(String   s)
    { char   c   =   s.charAt(0);
        int   i   =(int)c;
        if((i>=65&&i<=90)||(i>=97&&i<=122))
        { return   true;
        }
        else
        { return   false;
        }
    }

    public void Onclick_signUp(View v) {
        String email = getUserEmail();
        String password = getUserPass();
        String username = getUsername();
        String status = getStatus();
        String gender = getGender();

        if (email.equals("")) {
            Toast.makeText(Second.this, "Email is empty", Toast.LENGTH_LONG).show();
        }
        else if (status.equals("Empty")) {
            Toast.makeText(Second.this, "Status Should be selected", Toast.LENGTH_LONG).show();
        } else if (!(Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            Toast.makeText(Second.this, "Email is invalid", Toast.LENGTH_LONG).show();
        } else if (password.equals("")) {
            Toast.makeText(Second.this, "Password is empty", Toast.LENGTH_LONG).show();
        } else if (username.equals("")) {
            Toast.makeText(Second.this, "Username is empty", Toast.LENGTH_LONG).show();
        } else if (gender.equals("Empty")) {
            Toast.makeText(Second.this, "Gender should be selected", Toast.LENGTH_LONG).show();
        } else if (test(username) == false) {
            Toast.makeText(Second.this, "Correct user name should be input", Toast.LENGTH_LONG).show();
        } else {
            String id = DatabaseUser.push().getKey();
            final User result = new User();
            result.setEmail(email);
            result.setId(id);
            result.setGender(gender);
            result.setSatus(status);
            result.setUsername(username);
            result.setPassword(password);
            idforsignup = id;
            DatabaseUser.child(id).setValue(result);
            Toast.makeText(Second.this, "Registered successfully", Toast.LENGTH_LONG).show();
            if (status.equals("Employee")) {
                openProfile();}
            else {
                back();
            }
        }
    }
        public void openProfile () {
            Intent intent = new Intent(getApplicationContext(), emploeeprofiles.class);
            startActivity(intent);
    }
}





