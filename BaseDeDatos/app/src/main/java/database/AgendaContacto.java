package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Sistemas on 07/03/2015.
 */
public class AgendaContacto {

    Context context;
    AgendaDbHelper helper;
    SQLiteDatabase database;
    String[] seleccion = new String[]{
            AgendaContract.Contacto._ID,
            AgendaContract.Contacto.COLUMN_NOMBRE,
            AgendaContract.Contacto.COLUMN_DIRECCION,
            AgendaContract.Contacto.COLUMN_TELEFONO1,
            AgendaContract.Contacto.COLUMN_TELEFONO2,
            AgendaContract.Contacto.COLUMN_NOTAS,
            AgendaContract.Contacto.COLUMN_FAVORITO
    };

    public AgendaContacto(Context context) {
        this.context = context;
        helper = new AgendaDbHelper(context);
    }

    public void openDatabase(){
        database = helper.getWritableDatabase();
    }

    public void close(){
        database.close();
    }

    public long insertContacto(Contacto contacto){
        ContentValues contentValues = new ContentValues();
        contentValues.put(AgendaContract.Contacto.COLUMN_NOMBRE, contacto.getNombre());
        contentValues.put(AgendaContract.Contacto.COLUMN_DIRECCION, contacto.getDireccion());
        contentValues.put(AgendaContract.Contacto.COLUMN_TELEFONO1, contacto.getTelefono1());
        contentValues.put(AgendaContract.Contacto.COLUMN_TELEFONO2, contacto.getTelefono2());
        contentValues.put(AgendaContract.Contacto.COLUMN_NOTAS, contacto.getNotas());
        int favorito = 0;
        if(contacto.isFavorito() == true)
            favorito = 1;
        contentValues.put(AgendaContract.Contacto.COLUMN_FAVORITO, favorito);
        long id = database.insert(AgendaContract.Contacto.TABLE_NAME, null, contentValues);
        return id;
    }
    public long updateContacto(Contacto contacto){
        ContentValues contentValues = new ContentValues();
        contentValues.put(AgendaContract.Contacto.COLUMN_NOMBRE, contacto.getNombre());
        contentValues.put(AgendaContract.Contacto.COLUMN_DIRECCION, contacto.getDireccion());
        contentValues.put(AgendaContract.Contacto.COLUMN_TELEFONO1, contacto.getTelefono1());
        contentValues.put(AgendaContract.Contacto.COLUMN_TELEFONO2, contacto.getTelefono2());
        contentValues.put(AgendaContract.Contacto.COLUMN_NOTAS, contacto.getNotas());
        int favorito = 0;
        if(contacto.isFavorito() == true)
            favorito = 1;
        contentValues.put(AgendaContract.Contacto.COLUMN_FAVORITO, favorito);
        String[] argumentos = new String[]{
                String.valueOf(contacto.get_id())
        };
        long affectedRows = database.update(AgendaContract.Contacto.TABLE_NAME, contentValues, AgendaContract.Contacto._ID + " = ? ", argumentos);
        return affectedRows;
    }
    public Contacto getContacto(long id){
        SQLiteDatabase database1 = helper.getReadableDatabase();
        String[] argumentos = new String[]{
                String.valueOf(id)
        };
        Cursor cursor = database1.query(AgendaContract.Contacto.TABLE_NAME, seleccion, AgendaContract.Contacto._ID + " = ? ", argumentos, null, null, null, null);
        cursor.moveToFirst();
        Contacto contacto = cursorToContacto(cursor);
        return contacto;
    }
    public int deleteContacto(long id){
        SQLiteDatabase database1 = helper.getWritableDatabase();
        String[] argumentos = new String[]{
                String.valueOf(id)
        };

        return database1.delete(AgendaContract.Contacto.TABLE_NAME, AgendaContract.Contacto._ID + " = ? ", argumentos);
    }
    public ArrayList<Contacto> getAllContactos(){
        SQLiteDatabase database1 = helper.getReadableDatabase();
        ArrayList<Contacto> contactos = new ArrayList<>();
        Cursor cursor = database1.query(AgendaContract.Contacto.TABLE_NAME, seleccion, null, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Contacto contacto = new Contacto();
            contacto = cursorToContacto(cursor);
            contactos.add(contacto);
            cursor.moveToNext();
        }
        return contactos;
    }
    public Cursor getAllContactosCursor(){
        SQLiteDatabase database1 = helper.getReadableDatabase();
        ArrayList<Contacto> contactos = new ArrayList<>();
        Cursor cursor = database1.query(AgendaContract.Contacto.TABLE_NAME, seleccion, null, null, null, null, null, null);
        return cursor;
    }
    private Contacto cursorToContacto(Cursor cursor){
        Contacto contacto = new Contacto();

        contacto.set_id(cursor.getLong(0));
        contacto.setNombre(cursor.getString(1));
        contacto.setDireccion(cursor.getString(2));
        contacto.setTelefono1(cursor.getString(3));
        contacto.setTelefono2(cursor.getString(4));
        contacto.setNotas(cursor.getString(5));
        int favorito = cursor.getInt(6);
        boolean esFavorito = false;
        if(favorito == 1)
              esFavorito = true;
        contacto.setFavorito(esFavorito);
        return  contacto;
    }

}
