package com.example.adolfo.agenda;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    private SqliteControl admin;
    SQLiteDatabase bd;
    EditText nombre;
    EditText telefono;
    EditText direccion;
    EditText email;
    EditText web;
    EditText foto;
    Button alta;
    ListView listview;
    static Adaptador a;
    static ArrayList <Elemento> arrayList = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(android.R.id.list);

        AppCompatCallback callback = new AppCompatCallback() {
            @Override
            public void onSupportActionModeStarted(ActionMode actionMode) {
            }

            @Override
            public void onSupportActionModeFinished(ActionMode actionMode) {
            }

            @Nullable
            @Override
            public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
                return null;
            }
        };

        AppCompatDelegate delegate = AppCompatDelegate.create(this,callback);
        delegate.onCreate(savedInstanceState);
        delegate.setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        delegate.setSupportActionBar(toolbar);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView adapter, View view, int position, long arg)
            {
// getItem devuelve un Object
                Elemento el = (Elemento) listview.getAdapter().getItem(position);
// hacer algo
// Intent it = new Intent(this, xxxxx.class);
// it.putExtra("objeto", el);
// it.putExtra("posicion", pos);
// startActivity(it);
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
        if (id == R.id.añadir) {
            setContentView(R.layout.nuevocontacto);
        }


        return super.onOptionsItemSelected(item);
    }

    public long alta(View view){
        long resultado = -1;
        admin = new SqliteControl(this);
        bd = admin.getWritableDatabase();
        ContentValues valor = new ContentValues();

        nombre = (EditText)findViewById(R.id.tfNombre);
        telefono = (EditText)findViewById(R.id.tfTelefono);
        direccion = (EditText)findViewById(R.id.tfDireccion);
        email = (EditText)findViewById(R.id.tfEmail);
        web = (EditText)findViewById(R.id.tfWeb);
        foto = (EditText)findViewById(R.id.tfFoto);

        alta = (Button)findViewById(R.id.bAlta);

        valor.put("nombre", nombre.getText().toString());
        valor.put("direccion", direccion.getText().toString());
        valor.put("email", email.getText().toString());
        valor.put("Webblog", web.getText().toString());
        bd.insert("contactos", null, valor);

        valor.clear();

        valor.put("telefono", telefono.getText().toString());
        bd.insert("telefonos", null, valor);


        valor.clear();

        valor.put("nomFichero", foto.getText().toString());
        bd.insert("fotos", null, valor);


        nombre.setText("");
        direccion.setText("");
        email.setText("");
        web.setText("");
        telefono.setText("");
        foto.setText("");

        Toast toast1 = Toast.makeText(getApplicationContext(),"Contacto añadido correctamente", Toast.LENGTH_SHORT);
        toast1.show();

        admin.close();
        return resultado;
    }


}
