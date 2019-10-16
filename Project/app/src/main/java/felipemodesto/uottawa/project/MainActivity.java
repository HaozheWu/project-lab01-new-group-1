package felipemodesto.uottawa.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

import felipemodesto.uottawa.project.R;
import felipemodesto.uottawa.project.Second;

public class MainActivity extends AppCompatActivity {
    private EditText user;
    private EditText passward;
    private FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    DatabaseReference mReference;
    private FirebaseUser mUser;
    private String Administration = "admin";
    private String AdministrationPassward = "5T5ptQ";
    public User currentUser;
    public static String currentusername;


    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
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

    public void SignupOnclick(View view) {
        String username = user.getText().toString();
        String Passward = passward.getText().toString();
        if (username.equals("(Please Enter your User Name)") || Passward.equals("") || username.equals("") || passward.equals("Passward")) {
            Toast.makeText(MainActivity.this, "Email/Password is empty", Toast.LENGTH_LONG).show();
        } else if ((username.equals(Administration)) && (Passward).equals(AdministrationPassward)) {
            openAdmin();
        } else {
            mAuth.signInWithEmailAndPassword(username, Passward)

                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override

                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                mAuth = FirebaseAuth.getInstance();
                                mUser = mAuth.getCurrentUser();
                                mReference = FirebaseDatabase.getInstance().getReference();
                                mReference.child("Users")
                                        .child(mUser.getUid())
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                currentUser = dataSnapshot.getValue(User.class);
                                                String emploee = currentUser.getEmploee();
                                                currentusername = currentUser.getUsername();
                                                if (emploee.equals("YES")) {
                                                    openAdmin();
                                                }
                                                else{
                                                }
                                                    openRegister();
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                            } else {
                                Toast.makeText(MainActivity.this, "Authentication failed :(", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    public void OnclickRegister(View v) {
        openRegister();
    }
}