package lokacar.projet.adapters;

import android.content.Context;

import lokacar.projet.R;
import lokacar.projet.bo.locations.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class LocationAdapter extends ArrayAdapter<Location> {

    private int res;

    public LocationAdapter(@NonNull Context context, int resource, @NonNull List<Location> objects) {
        super(context, resource, objects);
        res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(res, parent, false);

            holder = new ViewHolder();
            holder.image = convertView.findViewById(R.id.imgVehicule);
            holder.marque = convertView.findViewById(R.id.txtMarque);
            holder.modele = convertView.findViewById(R.id.txtModele);
            holder.nomClient = convertView.findViewById(R.id.txtClientNom);
            holder.prenomClient = convertView.findViewById(R.id.txtClientPrenom);
            holder.dateDebut = convertView.findViewById(R.id.txtDateDebut);
            holder.dateFin = convertView.findViewById(R.id.txtDateFin);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        Location location = (Location) getItem(position);

        if(location != null) {
            holder.marque.setText(location.getVehicule().getMarque());
            holder.modele.setText(location.getVehicule().getModele());
            holder.nomClient.setText(location.getClient().getNom());
            holder.prenomClient.setText(location.getClient().getPrenom());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

            holder.dateDebut.setText(String.valueOf(sdf.format(location.getDateDebut())));
            holder.dateFin.setText(String.valueOf(sdf.format(location.getDateFin())));

        }

        return convertView;
    }

    static class ViewHolder{
        ImageView image;
        TextView marque;
        TextView modele;
        TextView nomClient;
        TextView prenomClient;
        TextView dateDebut;
        TextView dateFin;


    }
}
