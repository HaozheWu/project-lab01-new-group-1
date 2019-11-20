package felipemodesto.uottawa.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    private EditText user;
    private EditText password;
    private FirebaseAuth mAuth;
    private passwordEncryption a;

    FirebaseDatabase mDatabase;
    DatabaseReference mReference;

    private FirebaseUser mUser;
    public User currentUser;
    public static String currentusername;
    public static String id;


    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance();
        user = (EditText) findViewById(R.id.fieldEmail);
        password = (EditText) findViewById(R.id.fieldPassword);
    }

    public void openRegister() {
        Intent intent = new Intent(getApplicationContext(), Second.class);
        startActivity(intent);
    }

    public void openAdmin() {
        Intent intent = new Intent(getApplicationContext(), welcomeadmin.class);
        startActivity(intent);
    }

    public void openEmployee() {
        Intent intent = new Intent(getApplicationContext(), welcomeemployee.class);
        startActivity(intent);
    }

    public void openpatient() {
        Intent intent = new Intent(getApplicationContext(),welcomepatient.class);
        startActivity(intent);
    }

   public void activity_logIn(View view) {
        final String username = user.getText().toString();
        final String Passwords =password.getText().toString();
        if (Passwords.equals("") || username.equals("")) {
            Toast.makeText(MainActivity.this, "Email or Password is empty", Toast.LENGTH_LONG).show();
        } else {
            if (!RegTool.isEmail(username)) {
                Toast.makeText(MainActivity.this, "The mailbox entered is illegal", Toast.LENGTH_LONG).show();
                return;
            }
            a=new passwordEncryption();
            final String hashpass=a.passwordEncryption(Passwords);
            mReference = FirebaseDatabase.getInstance().getReference("Users");
            mReference.addValueEventListener(new ValueEventListener() {
                String statuas;
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    boolean success=false;
                    for (DataSnapshot postdataSnapshot : dataSnapshot.getChildren()) {
                        String UserEmail = (String)postdataSnapshot.child("email").getValue();
                        String UserPassword =(String)postdataSnapshot.child("password").getValue();
                        if (UserEmail.equals(username) && (UserPassword.equals(hashpass))) {
                            success=true;
                            id=(String)postdataSnapshot.child("id").getValue();
                            statuas=(String) postdataSnapshot.child("status").getValue();
                           currentusername=(String)postdataSnapshot.child("username").getValue();
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                            if (statuas.equals("Admin")) {//using email:admin@admin.ca password is given by prof
                                openAdmin();
                            } else if (statuas.equals("Employee")) {
                                openEmployee();//Should create an activity Welcome Emploee here
                            } else {
                                openpatient();//Should create an activity Welcome Patient here
                            }
                        }
                    }
                    if(success==false){
                            Toast.makeText(MainActivity.this, "Incorrect Password:(", Toast.LENGTH_LONG).show();
                    }


                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Authentication failed :(", Toast.LENGTH_LONG).show();
                 }
            }
            );}}

    public void activity_signUp(View v){
        openRegister();
    }
}
