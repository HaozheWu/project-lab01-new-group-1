package felipemodesto.uottawa.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class searchresult extends AppCompatActivity {
    ListView listViewuser;
    List<User> users;
    DatabaseReference databaseServices, mReference;
    private AlertDialog alertDialog2;
    static String a;
    User thisuser;
    static int i=0;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);
        listViewuser = (ListView) findViewById(R.id.result);
        databaseServices= FirebaseDatabase.getInstance().getReference("Users");
        users = new ArrayList<>();

        listViewuser.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

               thisuser= users.get(i);

                dialog(thisuser.getId());

                return true;
        }
    });
    }
    private void Back(View view) {
        Intent intent = new Intent(getApplicationContext(), welcomepatient.class);
        startActivity(intent);}
    protected void onStart() {
        super.onStart();
        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    for(String a:patientcenter.emploees){
                        if(postSnapshot.child("id").getValue().equals(a)){
                            User b = postSnapshot.getValue(User.class);
                            users.add(b);
                        }
                    }
                }
                Employeelist ServicesAdapter = new Employeelist(searchresult.this, users);
                listViewuser.setAdapter(ServicesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    protected void dialog(final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(searchresult.this);
        builder.setMessage("You Want to making an appointment with him or Rate him？");
        builder.setTitle("Chosen Process");
        builder.setPositiveButton("Rate Him", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Rate(id);
            }});
        builder.setNegativeButton("Making an Appointment", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialogappointment(id);


            }
        });
        builder.create().show();
    }
    protected void Rate(String id){
        showDialogofrate(id);
    }
    public void showDialogofrate(final String id) {
        final String[] items = {"Bad [*]", "Not Accpetable [**]", "Accpetable [***]", "Good [****]", "Perfect [*****]"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Choose The Day of week you are working");
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                a=(i+1)+"";
            }
        });

        alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog2.dismiss();
                if(a==null){
                    Toast.makeText(searchresult.this, "You should chose a grade for him", Toast.LENGTH_LONG).show();
                }else{
                    String position=FirebaseDatabase.getInstance().getReference("Users").child(id).child("Rate by customer").push().getKey();
                    FirebaseDatabase.getInstance().getReference("Users").child(id).child("Rate by customer").child(position).setValue(a);
                    Toast.makeText(searchresult.this, "You Have Rate him Successfully", Toast.LENGTH_LONG).show();
                }

            }
        });
        alertDialog2 = alertBuilder.create();
        alertDialog2.show();
    }
    protected void dialogappointment(final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(searchresult.this);
        builder.setMessage("To click Confirm finish making an appointment !! You can check the details, Through patient Center");
        builder.setTitle("Success");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface,int j) {
                mReference = FirebaseDatabase.getInstance().getReference("Users").child(id).child("Appointment");
                appointment a = new appointment();
                final String idforapointment = FirebaseDatabase.getInstance().getReference("Users").child(id).child("Appointment").push().getKey();
                String username = thisuser.getUsername().toString();
                final String useremail = thisuser.getEmail().toString();
                final String gender=thisuser.getGender().toString();
                a.setAppointmentid(idforapointment);
                FirebaseDatabase.getInstance().getReference("Users").child(id).child("Appointment").child(idforapointment).setValue(a);
               mReference.addValueEventListener(new ValueEventListener() {
                    String statuas;
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int i=0;
                        for (DataSnapshot postdataSnapshot : dataSnapshot.getChildren()) {
                           if (postdataSnapshot.child("appointmentid").getValue()!=idforapointment){
                               i++;
                           }else{
                               appointment a = new appointment();
                               String username = thisuser.getUsername().toString();
                               a.setEmploee(username);
                               a.setEmploeeid(id);
                               a.setCustomerid(MainActivity.id);
                               a.setAppointmentid(idforapointment);
                               a.setWaitingtime(i*15+"");
                               a.setEmail(useremail);
                               a.setGender(gender);
                               FirebaseDatabase.getInstance().getReference("Users").child(id).child("Appointment").child(idforapointment).setValue(a);
                               FirebaseDatabase.getInstance().getReference("Users").child(MainActivity.id).child("Appointment").child(idforapointment).setValue(a);
                           }
                        }
                    }


                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }

               });}});
        builder.create().show();
    }

}
