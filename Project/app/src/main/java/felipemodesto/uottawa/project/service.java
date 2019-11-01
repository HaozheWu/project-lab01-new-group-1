package felipemodesto.uottawa.project;

import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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



public class service extends AppCompatActivity {

    EditText editTextName;
    EditText editTextPrice;
    EditText editTextEmploeeName;
    EditText Emploeerole;
    Button buttonAddServices;
    Button button7;
    ListView listViewService;
    DatabaseReference databaseServices;
    List<Services> Services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPrice = (EditText) findViewById(R.id.editTextPrice);
        editTextEmploeeName = (EditText) findViewById(R.id.editTextEmploeeName);
        Emploeerole = (EditText) findViewById(R.id.Emploeerole);
        listViewService = (ListView) findViewById(R.id.listViewServices);
        buttonAddServices = (Button) findViewById(R.id.addButton);
        button7=(Button) findViewById(R.id.button7);
        databaseServices = FirebaseDatabase.getInstance().getReference("Services");
        Services = new ArrayList<>();


        buttonAddServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addServices();
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), welcomeadmin.class);
                startActivity(intent);
            }
        });

        listViewService.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Services Service = Services.get(i);

                showUpdateDeleteDialog(Service.getId(), Service.getServicesName());

                return true;

            }

        });

    }


    @Override
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
                ServiceList ServicesAdapter = new ServiceList(service.this, Services);
                listViewService.setAdapter(ServicesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    private void showUpdateDeleteDialog(final String servicesId, String productName) {


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_dialog, null);

        dialogBuilder.setView(dialogView);


        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextName);

        final EditText editTextPrice = (EditText) dialogView.findViewById(R.id.editTextPrice);

        final EditText editTextEmploeeName = (EditText) dialogView.findViewById(R.id.editTextEmploeeName);

        final EditText Emploeerole = (EditText) dialogView.findViewById(R.id.Emploeerole);


        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateProduct);

        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteProduct);


        dialogBuilder.setTitle(productName);

        final AlertDialog b = dialogBuilder.create();

        b.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                String name = editTextName.getText().toString().trim();
                String prices = editTextPrice.getText().toString();
                String emploeename = editTextEmploeeName.getText().toString().trim();
                String emploeerole = Emploeerole.getText().toString().trim();
                System.out.println(editTextPrice.getText().toString());
                if (prices.isEmpty() || isNumeric(prices) == false) {
                    Toast.makeText(getApplicationContext(), "The price should be number", Toast.LENGTH_LONG).show();
                } else if ((name.isEmpty()) || (emploeename.isEmpty()) || (emploeerole.isEmpty())) {
                    Toast.makeText(getApplicationContext(), "fill  the information", Toast.LENGTH_LONG).show();
                } else {
                    double price = Double.parseDouble(String.valueOf(prices));
                    updateServices(servicesId, name, price, emploeename, emploeerole);
                    b.dismiss();
                }


            }

        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                deleteServices(servicesId);

                b.dismiss();

            }

        });

    }

    private void updateServices(String id, String name, double price, String Emploeename, String Emploeerole) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Services").child(id);
        Services services = new Services(id, name, price, Emploeename, Emploeerole);
        dR.setValue(services);
        Toast.makeText(getApplicationContext(), "Services updated", Toast.LENGTH_LONG).show();
    }

    private void deleteServices(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Services").child(id);
        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Services deleted", Toast.LENGTH_LONG).show();

    }

    private void addServices() {
        String name = editTextName.getText().toString().trim();
        String prices = editTextPrice.getText().toString();
        String emploeename = editTextEmploeeName.getText().toString().trim();
        String emploeerole = Emploeerole.getText().toString().trim();
        if (prices.isEmpty() || isNumeric(prices) == false) {
            Toast.makeText(getApplicationContext(), "The price should be number", Toast.LENGTH_LONG).show();
        } else if ((name.isEmpty()) || (emploeename.isEmpty()) || (emploeerole.isEmpty())) {
            Toast.makeText(getApplicationContext(), "fill  the information", Toast.LENGTH_LONG).show();
        } else {
            double price = Double.parseDouble(String.valueOf(prices));
            String id = databaseServices.push().getKey();
            Services product = new Services(id, name, price, emploeename, emploeerole);
            databaseServices.child(id).setValue(product);
            editTextName.setText("");
            editTextPrice.setText("");
            editTextEmploeeName.setText("");
            Emploeerole.setText("");
            Toast.makeText(this, "Services added", Toast.LENGTH_LONG).show();
        }

    }




}