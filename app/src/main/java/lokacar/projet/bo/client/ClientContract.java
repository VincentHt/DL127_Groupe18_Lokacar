package lokacar.projet.bo.client;

public class ClientContract {

    public static final String TABLE_NAME = "CLIENTS";
    public static final String COL_ID = "ID";
    public static final String COL_NOM = "NOM";
    public static final String COL_PRENOM = "PRENOM";
    public static final String COL_ADRESSE = "ADRESSE";
    public static final String COL_EMAIL = "EMAIL";
    public static final String COL_TEL = "TEL";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS "
                    + TABLE_NAME + " ("
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_NOM + " TEXT, "
                    + COL_PRENOM + " TEXT, "
                    + COL_ADRESSE + " TEXT, "
                    + COL_EMAIL + " TEXT, "
                    + COL_TEL + " TEXT )";

    public static final String SQL_DROP_TABLE  =
            " DROP TABLE IF EXISTS " + TABLE_NAME;

}
