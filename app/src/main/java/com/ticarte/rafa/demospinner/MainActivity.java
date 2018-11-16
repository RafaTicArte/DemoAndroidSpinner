package com.ticarte.rafa.demospinner;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context myContext;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Contexto de la Actividad para utilizarlo en las subclases
        myContext = this;

        // Definición del layout
        setContentView(R.layout.activity_main);

        //Definición del Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Definición de la lista de opciones
        ArrayList<String> items = new ArrayList<String>();
        items.add("Opción 1");
        items.add("Opción 2");
        items.add("Opción 3");
        items.add("Opción 4");

        // Definición del Adaptador que contiene la lista de opciones
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        // Definición del Spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        // Definición de la acción del botón
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            // Recuperación de la vista del AlertDialog a partir del layout de la Actividad
            LayoutInflater layoutActivity = LayoutInflater.from(myContext);
            View viewAlertDialog = layoutActivity.inflate(R.layout.alert_dialog, null);

            // Definición del AlertDialog
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(myContext);

            // Asignación del AlertDialog a su vista
            alertDialog.setView(viewAlertDialog);

            // Recuperación del EditText del AlertDialog
            final EditText dialogInput = (EditText) viewAlertDialog.findViewById(R.id.dialogInput);

            // Configuración del AlertDialog
            alertDialog
                .setCancelable(false)
                // Botón Añadir
                .setPositiveButton(getResources().getString(R.string.add),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogBox, int id) {
                            adapter.add(dialogInput.getText().toString());
                            spinner.setSelection(adapter.getPosition(dialogInput.getText().toString()));
                        }
                    })
                // Botón Cancelar
                .setNegativeButton(getResources().getString(R.string.cancel),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogBox, int id) {
                            dialogBox.cancel();
                        }
                    })
                .create()
                .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
