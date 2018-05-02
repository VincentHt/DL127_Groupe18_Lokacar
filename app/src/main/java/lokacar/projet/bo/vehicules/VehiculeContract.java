package lokacar.projet.bo.vehicules;

public abstract class VehiculeContract {

    public final static String TABLE_NAME = "VEHICULES";
    public final static String COL_ID = "ID";
    public final static String COL_CNIT = "CNIT";
    public final static String COL_MARQUE = "MARQUE";
    public final static String COL_MODELE = "MODELE";
    public final static String COL_ETAT = "ETAT";
    public final static String COL_DESCRIPTION = "DESCRIPTION";
    public final static String COL_ID_AGENCE = "ID_AGENCE";
    public final static String COL_ID_PHOTO = "ID_PHOTO";


    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS "
                    + TABLE_NAME + " ("
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_MARQUE + " TEXT, "
                    + COL_MODELE + " TEXT, "
                    + COL_CNIT + " TEXT, "
                    + COL_ID_AGENCE + " INTEGER, "
                    + COL_ID_PHOTO + " INTEGER, "
                    + COL_DESCRIPTION + " TEXT, "
                    + COL_ETAT + " INTEGER);" ;

    public static final String SQL_DROP_TABLE  =
            " DROP TABLE IF EXISTS " + TABLE_NAME;
}
