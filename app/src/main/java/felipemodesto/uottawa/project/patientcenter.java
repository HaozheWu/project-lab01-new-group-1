package felipemodesto.uottawa.project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class patientcenter extends AppCompatActivity {
    static String day,method;
    static TextView a,b,c,d,e;
    private AlertDialog alertDialog1,alertDialog2;
    static List<String> emploees;
    DatabaseReference databaseServices,databaseServices2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientcenter);
        a = (EditText) findViewById(R.id.hour);
       emploees = new ArrayList<>();
        c= (TextView) findViewById(R.id.textView9);
        d = (EditText) findViewById(R.id.editText);
        e = (TextView) findViewById(R.id.textView22);

    }

    public void showDialog(View view) {
        final String[] items = {"Monday", "Tuesday", "Wednessday", "Thursday", "Friday", "Satuarday", "Sunday"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Choose The Day of week you are working");
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                e.setText(items[i]);
                day = items[i];
            }
        });

        alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog2.dismiss();
            }
        });
        alertDialog2 = alertBuilder.create();
        alertDialog2.show();
    }
    public void showtDialog2(View view) {
        final String[] items = {"Search by Address", "Search by Service", "Search by Time"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Choose The Serach method you want to use");
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                c.setText(items[i]);
                method= items[i];
            }
        });

        alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog1.dismiss();
            }
        });
        alertDialog1 = alertBuilder.create();
        alertDialog1.show();
    }
    public void confirm(View view){
       if(method==null){
           Toast.makeText(patientcenter.this, "Please Select a Search method", Toast.LENGTH_LONG).show();
       }
       else if((method.equals("Search by Address")||method.equals("Search by Service"))&&(d.getText().toString().equals(""))){
           Toast.makeText(patientcenter.this, "Please filled the information to search", Toast.LENGTH_LONG).show();
       }
       else if((method.equals("Search by Time"))&&((a.getText().toString()==null)||(day==null))){
            Toast.makeText(patientcenter.this, "Please filled the information to search", Toast.LENGTH_LONG).show();
        }
       else if(method.equals("Search by Time")){
            searchonstarttime();
        }
        else if(method.equals("Search by Address")){
            searchbyadd();
        }
        else if(method.equals("Search by Service")){
            searchbyservice();
       }
    }
    private void searchonstarttime() {
        databaseServices = FirebaseDatabase.getInstance().getReference("TimeforEmploee");
        databaseServices.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postdataSnapshot : dataSnapshot.getChildren()) {

                    if((a.getText().toString()).equals((String) postdataSnapshot.child("starthour").getValue())&&(day.equals((String) postdataSnapshot.child("weekday").getValue()))){
                        emploees.add(postdataSnapshot.child("id").getValue().toString());
                    } }
                System.out.println(emploees);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        continuego();
    }
    private void searchbyservice() {
        databaseServices = FirebaseDatabase.getInstance().getReference("EmploeeServices");
        databaseServices.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postdataSnapshot : dataSnapshot.getChildren()) {
                    if((d.getText().toString()).equals((String) postdataSnapshot.child("servicesName").getValue())){
                        emploees.add(postdataSnapshot.child("id").getValue().toString());
                    } }
                System.out.println(emploees);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        continuego();
  }
    public void searchbyadd(){
        databaseServices = FirebaseDatabase.getInstance().getReference("Emploeeinfo");
        databaseServices.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postdataSnapshot : dataSnapshot.getChildren()) {

                            if((d.getText().toString()).equals((String) postdataSnapshot.child("address").getValue())){
                                emploees.add(postdataSnapshot.child("id").getValue().toString());
                            } } }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        continuego();
    }
    public void continuego(){
        Intent intent = new Intent(getApplicationContext(), searchresult.class);
        startActivity(intent);
    }
    public void openpatient() {
        Intent intent = new Intent(getApplicationContext(),welcomepatient.class);
        startActivity(intent);
    }
}
