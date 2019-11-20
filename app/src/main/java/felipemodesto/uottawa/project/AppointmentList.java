package felipemodesto.uottawa.project;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;
public class AppointmentList extends ArrayAdapter<appointment> {
    private Activity context;
    List<appointment> appointments;

    public AppointmentList(@NonNull Activity context, List<appointment>  appointments) {
        super(context, R.layout.layout_appointment, appointments);
        this.context = context;
        this.appointments=appointments;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_appointment, null, true);
        TextView textday = (TextView) listViewItem.findViewById(R.id.textView40);
        TextView textstarthour = (TextView) listViewItem.findViewById(R.id.textView41);
        TextView email = (TextView) listViewItem.findViewById(R.id.textView44);
        TextView gender = (TextView) listViewItem.findViewById(R.id.textView45);

        appointment a = appointments.get(position);
        textday.setText("You got an Appointment With: "+a.getEmploee());
        textstarthour.setText("You may need to wait approximately "+a.getWaitingtime()+" Minutes");
        email.setText(a.getEmail());
        gender.setText(a.getGender());
        return listViewItem;
    }
}
