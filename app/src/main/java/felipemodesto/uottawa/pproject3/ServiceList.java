package felipemodesto.uottawa.project;
/**
 * Created by Miguel Garzón on 2017-05-09.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ServiceList extends ArrayAdapter<Services> {
    private Activity context;
    List<Services> Services;

    public ServiceList(Activity context, List<Services> products) {
        super(context, R.layout.layout_product_list, products);
        this.context = context;
        this.Services = products;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_product_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.textViewPrice);
        TextView textViewEmploeename = (TextView) listViewItem.findViewById(R.id.emploeeName);
        TextView textViewEmploeerole = (TextView) listViewItem.findViewById(R.id.Emploeerole);
        Services Service = Services.get(position);
        textViewName.setText(Service.getServicesName());
        textViewPrice.setText(String.valueOf(Service.getPrice()));
        textViewEmploeename.setText(String.valueOf(Service.get_emploeename()));
        textViewEmploeerole.setText(String.valueOf(Service.get_emploeerole()));
        return listViewItem;
    }
}

