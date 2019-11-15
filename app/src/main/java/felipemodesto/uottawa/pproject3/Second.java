package felipemodesto.uottawa.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< Updated upstream:app/src/main/java/felipemodesto/uottawa/pproject3/Second.java
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
=======
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
>>>>>>> Stashed changes:app/src/main/java/felipemodesto/uottawa/project/Second.java

public class Second extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private EditText Gender;
    public Services S;
    private static final String TAG = "Second";
    FirebaseDatabase mDatabase;
    DatabaseReference DatabaseUser;
<<<<<<< Updated upstream:app/src/main/java/felipemodesto/uottawa/pproject3/Second.java
    public User result;
    public static String idforsignup;
=======
    boolean check= true;
    public static String idforsignup;


>>>>>>> Stashed changes:app/src/main/java/felipemodesto/uottawa/project/Second.java
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mDatabase = FirebaseDatabase.getInstance();
        DatabaseUser=FirebaseDatabase.getInstance().getReference("Users");
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
        int b =R.id.check_male;
        int c =R.id.check_female;
        if(pos==b){
            return "male";
        }else if(pos==c){
            return "female";
        }
        return "Empty";
    }
    public String getS(){
        return null;
    }

    public void openAdmin() {
        Intent intent = new Intent(getApplicationContext(), welcomeadmin.class);
        startActivity(intent);
    }

    public void openProfile(){
        Intent intent = new Intent(getApplicationContext(), EmployeeProfiles.class);
        startActivity(intent);
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
        String passward = getUserPass();
        String username = getUsername();
        String status = getStatus();
        String gender = getGender();
<<<<<<< Updated upstream:app/src/main/java/felipemodesto/uottawa/pproject3/Second.java
        if (status.equals("Empty")) {
=======
        if (email.equals("")) {
            Toast.makeText(Second.this, "Email is empty", Toast.LENGTH_LONG).show();
        } else if (status.equals("Empty")) {
>>>>>>> Stashed changes:app/src/main/java/felipemodesto/uottawa/project/Second.java
            Toast.makeText(Second.this, "Status Should be selected", Toast.LENGTH_LONG).show();
        }else if(test(username)==false){
            Toast.makeText(Second.this, "Correct user name should be input", Toast.LENGTH_LONG).show();
        }else if (email.equals("")) {
            Toast.makeText(Second.this, "Email/Password is empty", Toast.LENGTH_LONG).show();
        } else if (!(Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            Toast.makeText(Second.this, "Email is invalid", Toast.LENGTH_LONG).show();
        } else if (passward.equals("")) {
            Toast.makeText(Second.this, "Email/Password is empty", Toast.LENGTH_LONG).show();
        } else if (username.equals("")) {
            Toast.makeText(Second.this, "Username is empty", Toast.LENGTH_LONG).show();
        } else if (gender.equals("Empty")) {
            Toast.makeText(Second.this, "Gender should be selected", Toast.LENGTH_LONG).show();
<<<<<<< Updated upstream:app/src/main/java/felipemodesto/uottawa/pproject3/Second.java
        }
        else {
            String id = DatabaseUser.push().getKey();
            idforsignup=id;
            result  = new User(id,email,passward,gender, username, status);
                        DatabaseUser.child(id).setValue(result);
=======
        } else if (test(username) == false) {
            Toast.makeText(Second.this, "Correct user name should be input", Toast.LENGTH_LONG).show();
        } else {
            String id = DatabaseUser.push().getKey();
            final User result = new User(id, email, passward, gender, username, status);
            idforsignup = id;
            DatabaseUser.child(id).setValue(result);
            Toast.makeText(Second.this, "Registered successfully", Toast.LENGTH_LONG).show();

            if (status.equals("Employee")) {
                openProfile();
            } else {
                back();
            }
        }
    }
        public void openProfile () {
            Intent intent = new Intent(getApplicationContext(), emploeeprofiles.class);
            startActivity(intent);
        }
    }
>>>>>>> Stashed changes:app/src/main/java/felipemodesto/uottawa/project/Second.java

                    }
        if(status=="Emploee"){
            openProfile();
        }


                }
        }


