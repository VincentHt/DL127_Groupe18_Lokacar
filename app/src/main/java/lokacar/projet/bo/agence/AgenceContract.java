package lokacar.projet.bo.agence;

import android.provider.BaseColumns;

public class AgenceContract {
    public static final class AgenceEntry implements BaseColumns {
        public static final String TABLE_NAME = "agence_code_nom";
        public static final String COLUMN_NOM_AGENCE = "nom_agence";
        public static final String COLUMN_PASSE_AGENCE = "code_agence";
    }
}
