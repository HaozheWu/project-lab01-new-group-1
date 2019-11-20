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

public class Employeelist extends ArrayAdapter<User> {
    private Activity context;
    List<User> user;

    public Employeelist(Activity context, List<User> User) {
        super(context, R.layout.layout_emploeeshow, User);
        this.context = context;
        this.user = User;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_emploeeshow, null, true);
        TextView Username = (TextView) listViewItem.findViewById(R.id.textView36);
        TextView Useremail = (TextView) listViewItem.findViewById(R.id.textView37);
        TextView Usergender = (TextView) listViewItem.findViewById(R.id.textView38);
        User a = user.get(position);
        Username.setText(a.getUsername());
        Useremail.setText(a.getEmail());
        Usergender.setText(a.getGender());
        return listViewItem;
    }

}
