package felipemodesto.uottawa.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import static felipemodesto.uottawa.project.MainActivity.currentusername;

import static felipemodesto.uottawa.project.MainActivity.id;
public class employeecenter extends AppCompatActivity {
    FirebaseDatabase mDatabase;
    DatabaseReference mReference;
    static String Address;
    static String Phone;
    static String Company;
    static String Licenced;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeecenter);

        mReference=FirebaseDatabase.getInstance().getReference("Users").child(id).child("Information");
        mReference.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postdataSnapshot : dataSnapshot.getChildren()) {
                    TextView b = (TextView) findViewById(R.id.Eaddress);
                    b.setText((String)postdataSnapshot.child("address").getValue());
                    TextView c = (TextView) findViewById(R.id.elocation);
                    c.setText((String)postdataSnapshot.child("company").getValue());
                    TextView d = (TextView) findViewById(R.id.editText11);
                    d.setText((String)postdataSnapshot.child("phone").getValue());
                    TextView e = (TextView) findViewById(R.id.editText10);
                    e.setText((String)postdataSnapshot.child("licenced").getValue());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        TextView a = (TextView) findViewById(R.id.ename);
        a.setText(currentusername);
    }
    public void openmanagement(View view) {
        gotomarket();
    }
    public void gotomarket() {
        Intent intent = new Intent(getApplicationContext(),Serviceforemployee.class);
        startActivity(intent);
    }
    public void Onclick_signUp(View v) {
        Intent register = new Intent(getApplicationContext(),myservices.class);
        startActivity(register);
    }
    public void back(View v) {
        finish();
    }
    public void time(View v) {
        Intent register = new Intent(getApplicationContext(),timecontrol.class);
        startActivity(register);
    }

}