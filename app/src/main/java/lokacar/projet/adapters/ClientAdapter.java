package lokacar.projet.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import lokacar.projet.R;
import lokacar.projet.bo.client.Client;

public class ClientAdapter extends ArrayAdapter<Client> {

    private int resourceId;

    public ClientAdapter(@NonNull Context context, int resource, List<Client> listClients) {
        super(context, resource, listClients);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull final ViewGroup parent){

        ViewHolder holder;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            holder = new ViewHolder();

            convertView = inflater.inflate(resourceId, parent, false);

            holder.nom = convertView.findViewById(R.id.clientItemNom);
            holder.prenom = convertView.findViewById(R.id.clientItemPrenom);
            holder.tel =  convertView.findViewById(R.id.clientItemTel);

            convertView.setTag(holder);
            Log.i("TEST" , "converstview NULL");
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
            Log.i("TEST" , "converstview NOT NULL");
        }
       Client client = (Client) getItem(position);

        holder.nom.setText(client.getNom());
        holder.prenom.setText(client.getPrenom());
        holder.tel.setText(client.getTel());

        return convertView;

    }

    static class ViewHolder {
        TextView nom;
        TextView prenom;
        TextView tel;
    }
}
