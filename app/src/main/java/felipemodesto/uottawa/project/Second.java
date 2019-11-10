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

import androidx.annotation.NonNull;
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

public class Second extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private EditText Gender;
    DatabaseReference mReference;
    private static final String TAG = "Second";
    FirebaseDatabase mDatabase;
    DatabaseReference DatabaseUser;
    boolean check= true;


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

    public void openAdmin() {
        Intent intent = new Intent(getApplicationContext(), welcomeadmin.class);
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
    protected void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Second.this);
        builder.setMessage("You have input an email with account, Do you want to log in or change an accouunt");
        builder.setTitle("Account found");
        builder.setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                back();
            }});
        builder.setNegativeButton("Register", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent register = new Intent(getApplicationContext(), Second.class);
                startActivity(register);
            }
        });
        builder.create().show();
    }

  public void checkifthisemailhasaccount(final String email) {
        if (!email.equals("")) {
            mReference = FirebaseDatabase.getInstance().getReference("Users");
            mReference.addValueEventListener(new ValueEventListener() {
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot postdataSnapshot : dataSnapshot.getChildren()) {
                        String UserEmail = (String) postdataSnapshot.child("email").getValue();
                        System.out.println(UserEmail);
                        if (email.equals(UserEmail)) {
                            dialog();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Second.this, "Firebase Error Try again later", Toast.LENGTH_LONG).show();
                    EditText et1 = (EditText) findViewById(R.id.email);
                    et1.setText("");
                }
            });
        }
    }
    public void Onclick_signUp(View v) {
        String email = getUserEmail();
        String passward = getUserPass();
        String username = getUsername();
        String status = getStatus();
        String gender = getGender();
        checkifthisemailhasaccount(email);
        if (email.equals("")) {
            Toast.makeText(Second.this, "Email is empty", Toast.LENGTH_LONG).show();
        }
        else if (status.equals("Empty")) {
            Toast.makeText(Second.this, "Status Should be selected", Toast.LENGTH_LONG).show();
        } else if (!(Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            Toast.makeText(Second.this, "Email is invalid", Toast.LENGTH_LONG).show();
        } else if (passward.equals("")) {
            Toast.makeText(Second.this, "Password is empty", Toast.LENGTH_LONG).show();
        } else if (username.equals("")) {
            Toast.makeText(Second.this, "Username is empty", Toast.LENGTH_LONG).show();
        } else if (gender.equals("Empty")) {
            Toast.makeText(Second.this, "Gender should be selected", Toast.LENGTH_LONG).show();
        } else if(test(username)==false){
            Toast.makeText(Second.this, "Correct user name should be input", Toast.LENGTH_LONG).show();
        } else{
            String id = DatabaseUser.push().getKey();
            final User result  = new User(id,email,passward,gender, username, status);
                        DatabaseUser.child(id).setValue(result);
                        Toast.makeText(Second.this, "Registered successfully",Toast.LENGTH_LONG).show();
                        back();
                    }}


                }


