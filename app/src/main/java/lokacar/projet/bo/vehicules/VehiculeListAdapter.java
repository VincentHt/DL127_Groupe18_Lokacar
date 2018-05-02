package lokacar.projet.bo.vehicules;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lokacar.projet.R;

public class VehiculeListAdapter extends RecyclerView.Adapter<VehiculeListAdapter.VehiculeViewHolder>{
    private Cursor mCursor;
    private Context mContext;


    public VehiculeListAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public VehiculeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.vehicule_list_item, parent, false);

        return new VehiculeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VehiculeViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position))
            return;

        String MarqueVehicule = mCursor.getString(mCursor.getColumnIndex(VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_MARQUE));
        String ModeleVehicule = mCursor.getString(mCursor.getColumnIndex(VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_MODELE));
        long id = mCursor.getLong(mCursor.getColumnIndex(VehiculeContract.VehiculeEntry._ID));

        holder.mMarqueVehiculeTextView.setText(MarqueVehicule);
        holder.mModeleVehiculeTextView.setText(ModeleVehicule);
        holder.itemView.setTag(id);
    }


    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }


    public void swapCursor(Cursor newCursor) {
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    class VehiculeViewHolder extends RecyclerView.ViewHolder {

         TextView mMarqueVehiculeTextView, mModeleVehiculeTextView, mStatutVehiculeTextView;

        public VehiculeViewHolder(View itemView) {
            super(itemView);
            mMarqueVehiculeTextView = itemView.findViewById(R.id.mMarqueVehiculeTextView);
            mModeleVehiculeTextView = itemView.findViewById(R.id.mModeleVehiculeTextView);
            mStatutVehiculeTextView = itemView.findViewById(R.id.mStatutVehiculeTextView);

        }

    }
}
