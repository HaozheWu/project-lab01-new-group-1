package felipemodesto.uottawa.project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TimeList extends ArrayAdapter<Time> {
    private Activity context;
    List<Time> times;
    public TimeList(Activity context, List<Time> time) {
        super(context, R.layout.layout_time_list, time);
        this.context = context;
        this.times = time;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_time_list, null, true);

        TextView textday = (TextView) listViewItem.findViewById(R.id.textView22);
        TextView textstarthour = (TextView) listViewItem.findViewById(R.id.textView12);
        TextView textViewstartmin = (TextView) listViewItem.findViewById(R.id.textView35);
        TextView textViewendhour = (TextView) listViewItem.findViewById(R.id.textView33);
        TextView textViewendmin = (TextView) listViewItem.findViewById(R.id.textView34);
       Time time = times.get(position);
        textday.setText("Every "+time.getWeekday());
        textstarthour.setText("From "+time.getStarthour()+" : ");
        textViewstartmin.setText(time.getStartminute());
        textViewendhour.setText(" "+time.getEndhour()+":");
        textViewendmin.setText(time.getEndminute());
        return listViewItem;
    }
}
