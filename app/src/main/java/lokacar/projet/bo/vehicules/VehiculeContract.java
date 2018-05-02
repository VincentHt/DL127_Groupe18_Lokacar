package lokacar.projet.bo.vehicules;

public abstract class VehiculeContract {

    private final static String TABLE_NAME = "VEHICULES";
    private final static String COL_ID = "ID";
    private final static String COL_CNIT = "CNIT";
    private final static String COL_MARQUE = "MARQUE";
    private final static String COL_MODELE = "MODELE";
    private final static String COL_ETAT = "ETAT";
    private final static String COL_DESCRIPTION = "DESCRIPTION";
    private final static String COL_ID_AGENCE = "ID_AGENCE";
    private final static String COL_ID_PHOTO = "ID_PHOTO";


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
