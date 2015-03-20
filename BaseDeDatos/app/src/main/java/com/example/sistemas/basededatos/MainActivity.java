package com.example.sistemas.basededatos;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import database.AgendaContacto;
import database.Contacto;

public class MainActivity extends Activity {

    EditText edtNombre, edtTelefono, edtTelefono2, edtDireccion, edtNotas;
    CheckBox chkFavorito;
    Contacto savedContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNombre = (EditText)findViewById(R.id.edtNombre);
        edtTelefono = (EditText)findViewById(R.id.edtTelefono1);
        edtTelefono2 = (EditText)findViewById(R.id.edtTelefono2);
        edtDireccion = (EditText)findViewById(R.id.edtDireccion);
        edtNotas = (EditText)findViewById(R.id.edtNotas);
        chkFavorito = (CheckBox)findViewById(R.id.chkFavorito);
        Button btnGuardar = (Button)findViewById(R.id.btnGuardar);
        Button btnListar = (Button)findViewById(R.id.btnListar);
        Button btnLimpiar = (Button)findViewById(R.id.btnLimpiar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgendaContacto source = new AgendaContacto(MainActivity.this);
                source.openDatabase();
                
                Contacto nContacto = new Contacto();
                nContacto.setNombre(edtNombre.getText().toString());
                nContacto.setTelefono1(edtTelefono.getText().toString());
                nContacto.setTelefono2(edtTelefono2.getText().toString());
                nContacto.setDireccion(edtDireccion.getText().toString());
                nContacto.setNotas(edtNotas.getText().toString());
                nContacto.setFavorito(false);

                if(chkFavorito.isChecked()){
                    nContacto.setFavorito(true);
                }

                Toast.makeText(MainActivity.this, R.string.mensaje, Toast.LENGTH_SHORT).show();

                long id = source.insertContacto(nContacto);
                source.close();
                limpiar();
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListaActivity.class);
                startActivityForResult(i, 0);
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                limpiar();
            }
        });


    }

    public void limpiar(){
        savedContact = null;
        edtNombre.setText("");
        edtTelefono.setText("");
        edtTelefono2.setText("");
        edtDireccion.setText("");
        edtNotas.setText("");
        chkFavorito.setChecked(false);
    }

}