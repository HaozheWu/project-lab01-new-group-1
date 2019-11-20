package felipemodesto.uottawa.project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManageAccount extends AppCompatActivity {
    EditText email;

    FirebaseDatabase mDatabase;
    DatabaseReference mReference;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private String currentusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);
        email = (EditText) findViewById(R.id.Emails);

    }
    public void activity_logIn(View view) {
        dialog();}
        public void delete(){
        final String Email = email.getText().toString();
        if (Email.equals("")) {
            Toast.makeText(ManageAccount.this, "Email/Password is empty", Toast.LENGTH_LONG).show();
        } else if (!(Patterns.EMAIL_ADDRESS.matcher(Email).matches())) {
            Toast.makeText(ManageAccount.this, "Email is invalid", Toast.LENGTH_LONG).show();
        }else if(Email.equals("admin@admin.ca")){
            Toast.makeText(ManageAccount.this, "You do not have right", Toast.LENGTH_LONG).show();
        }
        else {

            mReference = FirebaseDatabase.getInstance().getReference("Users");
            mReference.addValueEventListener(new ValueEventListener() {
                boolean success = false;
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot postdataSnapshot : dataSnapshot.getChildren()) {
                        String UserEmail = (String) postdataSnapshot.child("email").getValue();
                        if (UserEmail.equals(Email)) {
                            success=true;
                            String ID=(String)postdataSnapshot.child("id").getValue();
                            FirebaseDatabase.getInstance().getReference("Users").child(ID).removeValue();
                        }
                    }
                    if(success){
                        Toast.makeText(ManageAccount.this, "Remove Success", Toast.LENGTH_LONG).show();
                        openAdmin();
                    }
                    else{
                        Toast.makeText(ManageAccount.this, "Remove Fail, checking if the account valid", Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(ManageAccount.this, "Remove fail", Toast.LENGTH_LONG).show();
                }
            });

        }}

        protected void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ManageAccount.this);
        builder.setMessage("Are you sure you want to delete this account？");
        builder.setTitle(email.getText().toString()+"Deleting Process");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                    delete();
                }});
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
            });
            builder.create().show();
        }
    public void openAdmin() {
        Intent intent = new Intent(getApplicationContext(), welcomeadmin.class);
        startActivity(intent);
    }

    public void back(View view){
        finish();
    }
}
