package felipemodesto.uottawa.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static felipemodesto.uottawa.project.MainActivity.id;
import static felipemodesto.uottawa.project.MainActivity.currentusername;

public class Serviceforemployee extends AppCompatActivity {
    ListView listViewService;
    List<Services> Services;
    DatabaseReference databaseServices;
    static Services a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceforemployee);
        Services = new ArrayList<>();
        listViewService = (ListView) findViewById(R.id.listViewServices);
        databaseServices = FirebaseDatabase.getInstance().getReference("Services");
        listViewService.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Services Service = Services.get(i);

               showUpdateDeleteDialog(Service.getId(), Service.getServicesName());

                return true;

            }

        });

    }

    private void showUpdateDeleteDialog(final String servicesId, String productName) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_employee_service, null);
        dialogBuilder.setView(dialogView);
        TextView username = (TextView) dialogView.findViewById(R.id.employeeService);
        username.setText(currentusername);
        final EditText Emploeerole = (EditText) dialogView.findViewById(R.id.emploeerole);
        final Button buttonReturn = (Button) dialogView.findViewById(R.id.buttonReturn2);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.addto);
        final AlertDialog b = dialogBuilder.create();
        String emploeerole;
        b.show();
        buttonDelete.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                String emploeerole = Emploeerole.getText().toString().trim();
                if (emploeerole.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "fill the information", Toast.LENGTH_LONG).show();
                } else {
                    changetomyprofile(servicesId,emploeerole);
                    b.dismiss();
                }
            }


        });
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                returntoList();
            }

        });
    }

    private void changetomyprofile(final String ids, final String emploeerole) {
        System.out.println(ids);
        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                a = postSnapshot.getValue(Services.class);
                if (a.getId().equals(ids)) {
                    a = postSnapshot.getValue(Services.class);
                    a.set_emploeename(currentusername);
                    a.set_emploeerole(emploeerole);
                    a.setId(MainActivity.id);
                    String newidd= FirebaseDatabase.getInstance().getReference("EmploeeServices").push().getKey();
                    a.set_publicid(newidd);
                    FirebaseDatabase.getInstance().getReference("EmploeeServices").child(newidd).setValue(a);
                    String newid=FirebaseDatabase.getInstance().getReference("Users").child(MainActivity.id).child("Services").push().getKey();
                    a.setId(newid);
                    FirebaseDatabase.getInstance().getReference("Users").child(MainActivity.id).child("Services").child(newid).setValue(a);
                }
            }
                databaseServices.child(ids).removeValue();



            }
                @Override
                public void onCancelled (@NonNull DatabaseError databaseError){

                }
            }
            );
        Toast.makeText(getApplicationContext(), "The Service has added to your personal file", Toast.LENGTH_LONG).show();
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
                ServiceList ServicesAdapter = new ServiceList(Serviceforemployee.this, Services);
                listViewService.setAdapter(ServicesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void returntoList() {
        Intent intent = new Intent(getApplicationContext(), Serviceforemployee.class);
        startActivity(intent);
    }

    private void back(View view){
        finish();
    }



}
