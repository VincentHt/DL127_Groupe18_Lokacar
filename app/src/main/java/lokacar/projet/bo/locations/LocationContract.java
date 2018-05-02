package lokacar.projet.bo.locations;

public abstract class LocationContract {

    public final static String TABLE_NAME = "LOCATIONS";
    public final static String COL_ID = "ID";
    public final static String COL_ID_VEHICULE = "ID_VEHICULE";
    public final static String COL_ID_CLIENT = "ID_CLIENT";
    public final static String COL_DATE_DEBUT = "DATE_DEBUT";
    public final static String COL_DATE_FIN = "DATE_FIN";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS "
                    + TABLE_NAME + " ("
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_ID_VEHICULE + " INTEGER, "
                    + COL_ID_CLIENT + " INTEGER, "
                    + COL_DATE_DEBUT + " TEXT, "
                    + COL_DATE_FIN + " TEXT);" ;

    public static final String SQL_DROP_TABLE  =
            " DROP TABLE IF EXISTS " + TABLE_NAME;
}
