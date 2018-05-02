package lokacar.projet.bo.photos;

public abstract class PhotoEDLContract {

    public final static String TABLE_NAME = "PHOTO_EDL";
    public final static String COL_ID = "ID";
    public final static String COL_EMPLACEMENT = "EMPLACEMENT";
    public final static String COL_ID_EDL = "ID_EDL";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS "
                    + TABLE_NAME + " ("
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_ID_EDL + " INTEGER, "
                    + COL_EMPLACEMENT + " TEXT);" ;

    public static final String SQL_DROP_TABLE  =
            " DROP TABLE IF EXISTS " + TABLE_NAME;

}
