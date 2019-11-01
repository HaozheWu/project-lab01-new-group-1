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
    private EditText passward;
    private FirebaseAuth mAuth;
    private passwordEncryption a;

    FirebaseDatabase mDatabase;
    DatabaseReference mReference;

    private FirebaseUser mUser;
    public User currentUser;
    public static String currentusername;


    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance();
        user = (EditText) findViewById(R.id.fieldEmail);
        passward = (EditText) findViewById(R.id.fieldPassword);
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
        Intent intent = new Intent(getApplicationContext(), Welcomepatients.class);
        startActivity(intent);
    }

    public void activity_logIn(View view) {
        final String username = user.getText().toString();
        final String Passwards =passward.getText().toString();
        if (Passwards.equals("") || username.equals("")) {
            Toast.makeText(MainActivity.this, "Email or Password is empty", Toast.LENGTH_LONG).show();
        } else {
            a=new passwordEncryption();
            final String hashpass=a.passwordEncryption(Passwards);
            mReference = FirebaseDatabase.getInstance().getReference("Users");
            mReference.addValueEventListener(new ValueEventListener() {
                String statuas;
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    boolean success=false;
                    for (DataSnapshot postdataSnapshot : dataSnapshot.getChildren()) {
                        String UserEmail = (String)postdataSnapshot.child("email").getValue();
                        String UserPassward =(String)postdataSnapshot.child("passward").getValue();
                        System.out.println(UserEmail);
                        System.out.println(UserPassward);
                        if (UserEmail.equals(username) && (UserPassward.equals(hashpass))) {
                            success=true;
                           statuas=(String) postdataSnapshot.child("status").getValue();
                           currentusername=(String)postdataSnapshot.child("username").getValue();
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
                            Toast.makeText(MainActivity.this, "Incorrect Passward:(", Toast.LENGTH_LONG).show();
                    }


                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Authentication failed :(", Toast.LENGTH_LONG).show();
                 }
            }
            );}}

    public void activity_signUp(View v) {
        openRegister();
    }
}