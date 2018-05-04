package lokacar.projet.bo.agence;

public abstract class AgenceContract {

    public static final String TABLE_NAME = "AGENCE";
    public static final String COLUMN_NOM_AGENCE = "NOM_AGENCE";
    public static final String COLUMN_PASSE_AGENCE = "PASSWORD_AGENCE";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS "
                    + TABLE_NAME + "("
                    + COLUMN_NOM_AGENCE + " TEXT, "
                    + COLUMN_PASSE_AGENCE + " TEXT);";

    public static final String SQL_DROP_TABLE  =
            " DROP TABLE IF EXISTS " + TABLE_NAME;
}
