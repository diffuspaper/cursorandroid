package com.example.sistemas.basededatos;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import database.AgendaContacto;
import database.AgendaContract;
import database.Contacto;


public class ListaActivity extends ListActivity {

    private AgendaContacto agendaContacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Button btnNuevo = (Button)findViewById(R.id.btnNuevo);
        agendaContacto = new AgendaContacto(this);
        agendaContacto.openDatabase();
        ArrayList<Contacto> contactos = agendaContacto.getAllContactos();
        MyArrayAdapter adapter = new MyArrayAdapter(this, R.layout.layout_contacto, contactos);
        setListAdapter(adapter);

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

    class MyArrayAdapter extends ArrayAdapter<Contacto> {

        Context context;
        int textViewResourceId;
        ArrayList<Contacto> objects;
        public MyArrayAdapter(Context context, int textViewResourceId, ArrayList<Contacto> objects){
            super(context, textViewResourceId, objects);
            this.context = context;
            this.textViewResourceId = textViewResourceId;
            this.objects = objects;
        }

        public View getView(int position, View convertView, ViewGroup viewGroup){
            LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(this.textViewResourceId, null);
            TextView lblNombre = (TextView)view.findViewById(R.id.nombre_contacto);
            TextView lblTelefono = (TextView)view.findViewById(R.id.telefono_contacto);
            lblNombre.setText(objects.get(position).getNombre());
            lblTelefono.setText(objects.get(position).getTelefono1());
            return view;
        }
    }
}