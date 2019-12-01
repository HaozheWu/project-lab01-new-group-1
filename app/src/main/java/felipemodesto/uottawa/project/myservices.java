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


public class myservices extends AppCompatActivity {
    ListView listViewService;
    List<Services> Services;
    DatabaseReference databaseServices;
    Services aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myservices);
        Services = new ArrayList<>();

        databaseServices = FirebaseDatabase.getInstance().getReference("Users").child(MainActivity.id).child("Services");
        listViewService = (ListView) findViewById(R.id.listViewServices);
        listViewService.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Services Service = Services.get(i);
                dialog(Service.getId());
                return true;
            }

        });

    }
    protected void onStart() {
        super.onStart();
        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Services.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Services a = postSnapshot.getValue(Services.class);
                    Services.add(a);
                }
                ServiceList ServicesAdapter = new ServiceList(myservices.this, Services);
                listViewService.setAdapter(ServicesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    protected void dialog(final String a){
        AlertDialog.Builder builder = new AlertDialog.Builder(myservices.this);
        builder.setMessage("The Choosen Service will be delete from your list and back to the market, Are you sure you want to do that");
        builder.setTitle("Warning");
        builder.setPositiveButton("Yes,Comfirm", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                delete(a);
            }});
        builder.setNegativeButton("No, Do that later", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    public void delete(final String a) {
        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    aa = postSnapshot.getValue(Services.class);
                    if (aa.getId().equals(a)){
                        aa.set_emploeerole("UNKNOWN");
                        aa.set_emploeename("UNKNOWN");
                        String newid=FirebaseDatabase.getInstance().getReference("Services").push().getKey();
                        aa.setId(newid);
                        FirebaseDatabase.getInstance().getReference("Services").child(newid).setValue(aa);
                    }
                }
                databaseServices.child(a).removeValue();
                Toast.makeText(getApplicationContext(), "The Service has Removed from your personal file", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }
    public void back(View v) {
        finish();
    }
}