package felipemodesto.uottawa.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static felipemodesto.uottawa.project.MainActivity.id;

public class timecontrol extends AppCompatActivity {
    static TextView a, b, c, d, e;
    static String day, starttime, startminute, endtime, endminute, ids, publicid;
    private AlertDialog alertDialog2;
    DatabaseReference mReference;
    ListView listViewService;
    List<Time> times;
    DatabaseReference databaseServices,mReference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timecontrol);
        listViewService = (ListView) findViewById(R.id.listViewServices);
        times = new ArrayList<>();
        databaseServices = FirebaseDatabase.getInstance().getReference("Users").child(MainActivity.id).child("time");
        mReference2 =FirebaseDatabase.getInstance().getReference("TimeforEmploee");
        a = (TextView) findViewById(R.id.textView21);
        b = (TextView) findViewById(R.id.textView25);
        c = (TextView) findViewById(R.id.textView27);
        d = (TextView) findViewById(R.id.textView28);
        e = (TextView) findViewById(R.id.textView22);
        listViewService.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Time time = times.get(i);
                System.out.println(time.getId());
                showUpdateDeleteDialog(time.getId());
                return true;

            }

        });


    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String aa;
            aa = hourOfDay + "";
            starttime = aa;
            String bb;
            bb = minute + "";
            startminute = bb;
            System.out.println(aa);
            a.setText(aa);
            b.setText(bb);
        }
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public static class TimePickerFragment2 extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String cc;
            cc = hourOfDay + "";
            endtime = cc;
            String dd;
            dd = minute + "";
            endminute = dd;
            c.setText(cc);
            d.setText(dd);
        }
    }

    public void showTimePickerDialog2(View v) {
        DialogFragment newFragment = new TimePickerFragment2();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showSingleAlertDialog(View view) {
        final String[] items = {"Monday", "Tuesday", "Wednessday", "Thursday", "Friday", "Satuarday", "Sunday"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Choose The Day of week you are working");
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                e.setText(items[i]);
                System.out.println(items[i]);
                System.out.println(items[0]);
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

    public void confirm(View v) {
        if (day == null || starttime == null || startminute == null || endtime == null || endminute == null) {
            Toast.makeText(timecontrol.this, "Please fullfill the information", Toast.LENGTH_SHORT).show();
            return;
        }
        Time time = new Time();
        time.setWeekday(day);
        time.setStarthour(starttime);
        time.setStartminute(startminute);
        time.setEndhour(endtime);
        time.setEndminute(endminute);

        if (time.timeavilable()) {

            String ids= mReference2.push().getKey();
            time.setId(MainActivity.id);
            time.setPublicid(ids);
            mReference2.child(ids).setValue(time);

            mReference = FirebaseDatabase.getInstance().getReference("Users").child(id).child("time");
            String id = mReference.push().getKey();
            time.setId(id);
            mReference.child(id).setValue(time);
            Toast.makeText(timecontrol.this, "Adding Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(timecontrol.this, "Time Should be in order, checking the start and end time order", Toast.LENGTH_SHORT).show();
        }
    }

    public void back(View v) {
        finish();
    }

    protected void onStart() {
        super.onStart();
        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                times.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Time a = postSnapshot.getValue(Time.class);
                    times.add(a);
                }
                TimeList ServicesAdapter = new TimeList(timecontrol.this, times);
                listViewService.setAdapter(ServicesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showUpdateDeleteDialog(final String servicesId) {
        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Time oldtime = postSnapshot.getValue(Time.class);
                    if (oldtime.getId() == servicesId) {
                        a.setText(oldtime.getStarthour());
                        b.setText(oldtime.getStartminute());
                        c.setText(oldtime.getEndhour());
                        d.setText(oldtime.getEndminute());
                        e.setText(oldtime.getWeekday());
                        day = oldtime.getWeekday();
                        starttime = oldtime.getStarthour();
                        startminute = oldtime.getStartminute();
                        endtime = oldtime.getEndhour();
                        endminute = oldtime.getEndminute();
                        ids = oldtime.getId();
                        publicid = oldtime.getPublicid();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void update(final String ids) {
        if (ids == null) {
            Toast.makeText(timecontrol.this, "Please choose existing time first", Toast.LENGTH_SHORT).show();
        } else {
            if (day == null || starttime == null || startminute == null || endtime == null || endminute == null) {
                Toast.makeText(timecontrol.this, "Please fullfill the information", Toast.LENGTH_SHORT).show();
                return;
            }
            Time time = new Time();
            time.setWeekday(day);
            time.setStarthour(starttime);
            time.setStartminute(startminute);
            time.setEndhour(endtime);
            time.setEndminute(endminute);
            time.setId(MainActivity.id);
            time.setPublicid(publicid);
            if (time.timeavilable()) {
                mReference2.child(publicid).setValue(time);
                time.setId(ids);
                databaseServices.child(ids).setValue(time);
                Toast.makeText(timecontrol.this, "updating Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(timecontrol.this, "Logic Error，check The starting time and ending time", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                public void update(View v) {
        update(ids);
    }

    public void delete(View v) {
        if (ids == null) {
            Toast.makeText(timecontrol.this, "Please choose existing time first", Toast.LENGTH_SHORT).show();
        } else {

            databaseServices.child(ids).removeValue();
            mReference2.child(publicid).removeValue();
        }
    }




}



