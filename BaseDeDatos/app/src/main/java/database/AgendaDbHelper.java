package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sistemas on 07/03/2015.
 */
public class AgendaDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "agenda.db";
    public static final int DATABASE_VERSION = 2;

    public static final String TEXT_TYPE = " TEXT";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String ", " = ", ";

    public static final String CREATE_TABLE_CONTACTOS = "CREATE TABLE " +
            AgendaContract.Contacto.TABLE_NAME + "( " +
            AgendaContract.Contacto._ID + " INTEGER PRIMARY KEY " + ", " +
            AgendaContract.Contacto.COLUMN_NOMBRE + TEXT_TYPE + ", " +
            AgendaContract.Contacto.COLUMN_DIRECCION + TEXT_TYPE + ", " +
            AgendaContract.Contacto.COLUMN_TELEFONO1 + TEXT_TYPE + ", " +
            AgendaContract.Contacto.COLUMN_TELEFONO2 + TEXT_TYPE + ", " +
            AgendaContract.Contacto.COLUMN_NOTAS + TEXT_TYPE + ", " +
            AgendaContract.Contacto.COLUMN_FAVORITO + INTEGER_TYPE + ");";

    public static final String DROP_TABLE_CONTACTOS = "DROP TABLE IF EXISTS " + AgendaContract.Contacto.TABLE_NAME +";";


    public AgendaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_CONTACTOS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }


}
