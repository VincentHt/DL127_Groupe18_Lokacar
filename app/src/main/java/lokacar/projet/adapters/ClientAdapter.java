package lokacar.projet.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import lokacar.projet.R;
import lokacar.projet.bo.client.Client;

public class ClientAdapter extends ArrayAdapter<Client> {

    private final List<Client> listClients;
    private final Context context;
    private int resourceId;
 //   private final Resources resources;

    public ClientAdapter(@NonNull Context context, int resource, List<Client> listClients) {
        super(context, resource, listClients);
        this.listClients = listClients;
        this.context = context;
     //   this.resourceId = resourceId;
      //  this.resources = resources;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent){

        ViewHolder holder;

        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){

            holder = new ViewHolder();

            convertView = inflater.inflate(resourceId, parent, false);

            holder.nom = (TextView) convertView.findViewById(R.id.ListClientNom);
            holder.test = (TextView) convertView.findViewById(R.id.Jesaispas);


            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        Client client = listClients.get(position);

        holder.nom.setText(client.getNom());


        return convertView;

    }

    static class ViewHolder {

        TextView nom;
        TextView test;
    }
}
