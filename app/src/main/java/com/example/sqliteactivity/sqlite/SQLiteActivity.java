package com.example.sqliteactivity.sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.sqliteactivity.R;
import com.example.sqliteactivity.databinding.ActivitySQLiteBinding;
import com.example.sqliteactivity.sqlite.adapter.ContactosAdapter;
import com.example.sqliteactivity.sqlite.db.DbContactos;
import com.example.sqliteactivity.sqlite.modelo.Contacto;

import java.util.ArrayList;

public class SQLiteActivity extends AppCompatActivity {
    ActivitySQLiteBinding activitySQLiteBinding;
    public ArrayList<Contacto> listaContactos;
    ContactosAdapter contactosAdapter;
    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySQLiteBinding = ActivitySQLiteBinding.inflate((getLayoutInflater()));
        setContentView(activitySQLiteBinding.getRoot());
        setClickListeners();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_nuevo:
            startActivity(new Intent(getApplicationContext(), AgregandoDatosActivity.class));
            return true;

            case R.id.prueba:
                Toast.makeText(getApplicationContext(), "holaaaaa", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setClickListeners() {
    }

    private void setRecyclerView()
    {
        DbContactos dbContactos = new DbContactos(getApplicationContext());
        listaContactos = dbContactos.obtenerContactos();
        contactosAdapter = new ContactosAdapter(listaContactos);
        activitySQLiteBinding.rvContactos.setAdapter(contactosAdapter);
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        setRecyclerView();

    }
}