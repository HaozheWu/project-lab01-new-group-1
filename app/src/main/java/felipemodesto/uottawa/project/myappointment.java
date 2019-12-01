package felipemodesto.uottawa.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class myappointment extends AppCompatActivity {
    ListView listViews;
    List<appointment> appointmentli;
    DatabaseReference databaseServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseServices= FirebaseDatabase.getInstance().getReference("Users").child(MainActivity.id).child("Appointment");
        setContentView(R.layout.activity_myappointment);
        listViews = (ListView) findViewById(R.id.aaa);
        appointmentli=new ArrayList<>();
        listViews.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            appointment a = appointmentli.get(i);
            showUpdateDeleteDialog(a);
            return true;
        }
        });
    }
    protected void onStart() {
            super.onStart();
            databaseServices.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    appointmentli.clear();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        appointment a = postSnapshot.getValue(appointment.class);
                        appointmentli.add(a);
                    }
                    AppointmentList ServicesAdapter = new AppointmentList(myappointment.this, appointmentli);
                    listViews.setAdapter(ServicesAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        public void showUpdateDeleteDialog(appointment a){
        dialog(a);
        }
    protected void dialog(final appointment a){
        AlertDialog.Builder builder = new AlertDialog.Builder(myappointment.this);
        builder.setMessage("Are you sure this appointment is no longer useful？");
        builder.setTitle("Delete/check in the appointment");
        builder.setPositiveButton("Yes, Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                delete(a);
            }});
        builder.setNegativeButton("NO, keep it", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public void delete(final appointment a){
        FirebaseDatabase.getInstance().getReference("Users").child(MainActivity.id).child("Appointment").child(a.getAppointmentid()).removeValue();
        FirebaseDatabase.getInstance().getReference("Users").child(a.getEmploeeid()).child("Appointment").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postdataSnapshot : dataSnapshot.getChildren()) {
                    if(postdataSnapshot.child("appointmentid").getValue().equals(a.getAppointmentid())){
                        postdataSnapshot.getRef().removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });}

        public void back(View view){
            finish();
        }

}
