package lokacar.projet.bo.vehicules;

import android.provider.BaseColumns;

public class VehiculeContract {


    public static final class VehiculeEntry implements BaseColumns {
        public static final String TABLE_NAME = "vehicule";
        public static final String COLUMN_VEHICULE_MARQUE = "marque";
        public static final String COLUMN_VEHICULE_MODELE = "modele";
        public static final String COLUMN_VEHICULE_IMMATRICULATION = "immatriculation";
        public static final String COLUMN_VEHICULE_FRAIS_LICATION_JOUR = "frais_location_jour";
        public static final String COLUMN_VEHICULE_TYPE = "type";
    }

}
