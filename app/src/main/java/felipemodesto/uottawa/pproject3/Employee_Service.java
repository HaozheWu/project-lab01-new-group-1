package felipemodesto.uottawa.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Employee_Service extends AppCompatActivity {
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

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee__service);
        databaseServices = FirebaseDatabase.getInstance().getReference("Services");
        mDatabase = FirebaseDatabase.getInstance();
        DatabaseUser=FirebaseDatabase.getInstance().getReference("Users");

        listViewService.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Services Service = Services.get(i);

                showAddServiceToProfile(Service.getId(), Service.getServicesName());

                return true;

            }

        });

        AddedlistViewService.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Services Service = AddedServices.get(i);

                showDeleteService(Service.getId(), Service.getServicesName());

                return true;

            }

        });
        ServiceList ServicesAdapter = new ServiceList(EmployeeProfiles.this, Services);
        listViewService.setAdapter(ServicesAdapter);

        ServiceList ServicesAdapter2 = new ServiceList(EmployeeProfiles.this, AddedServices);
        AddedlistViewService.setAdapter(ServicesAdapter);
    }
    private void showAddServiceToProfile(final String servicesId, final String productName) {


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_dialog, null);

        dialogBuilder.setView(dialogView);


//        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextName);
//
//        final EditText editTextPrice = (EditText) dialogView.findViewById(R.id.editTextPrice);
//
//        final EditText editTextEmploeeName = (EditText) dialogView.findViewById(R.id.editTextEmploeeName);
//
//        final EditText Emploeerole = (EditText) dialogView.findViewById(R.id.Emploeerole);
//
//        final Button buttonReturn= (Button) dialogView.findViewById(R.id.buttonReturn);
//
//
//        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateProduct);
//
//        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteProduct);


        dialogBuilder.setTitle(productName+"Adding to Profile processing");


        final AlertDialog b = dialogBuilder.create();

        b.show();

    }

