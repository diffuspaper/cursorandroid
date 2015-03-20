package database;

import android.os.Environment;
import android.provider.BaseColumns;

/**
 * Created by Sistemas on 07/03/2015.
 */
public class AgendaContract {


    public static class Contacto implements BaseColumns{
        public static final String TABLE_NAME = "contactos";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_DIRECCION = "direccion";
        public static final String COLUMN_TELEFONO1 = "telefono1";
        public static final String COLUMN_TELEFONO2 = "telefono2";
        public static final String COLUMN_NOTAS = "notas";
        public static final String COLUMN_FAVORITO = "favorito";
    }
}
