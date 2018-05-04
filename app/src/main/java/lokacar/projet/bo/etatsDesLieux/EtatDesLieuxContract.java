package lokacar.projet.bo.etatsDesLieux;

public abstract class EtatDesLieuxContract {

    public final static String TABLE_NAME = "ETAT_DES_LIEUX";
    public final static String COL_ID = "ID";
    public final static String COL_ID_LOCATION = "ID_LOCATION";
    public final static String COL_COMPTE_RENDU = "COMPTE_RENDU";
    public final static String COL_DEBUT_OU_FIN = "DEBUT_OU_FIN";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS "
                    + TABLE_NAME + " ("
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_ID_LOCATION + " INTEGER, "
                    + COL_DEBUT_OU_FIN + " INTEGER, "
                    + COL_COMPTE_RENDU + " TEXT);" ;

    public static final String SQL_DROP_TABLE  =
            " DROP TABLE IF EXISTS " + TABLE_NAME;

}
