package com.example.sqliteactivity.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.sqliteactivity.R;
import com.example.sqliteactivity.databinding.ActivityConsultaBinding;
import com.example.sqliteactivity.sqlite.db.DbContactos;
import com.example.sqliteactivity.sqlite.modelo.Contacto;

public class ConsultaActivity extends AppCompatActivity {
    int id = 0;
    Contacto contacto;
    ActivityConsultaBinding activityConsultaBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityConsultaBinding = ActivityConsultaBinding.inflate(getLayoutInflater());
        setContentView(activityConsultaBinding.getRoot());

        if(savedInstanceState == null)
        {
            Bundle bundle = getIntent().getExtras();

            if(bundle == null)
            {
                id = Integer.parseInt(null);
            }
            else
            {
                id = bundle.getInt("ID");
            }
        }
        else
        {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbContactos dbContactos = new DbContactos(ConsultaActivity.this);
        contacto = dbContactos.obtenerContactoPorId(id);

        if(contacto != null) {
            activityConsultaBinding.txtNombre.setText(contacto.getNombre());
            activityConsultaBinding.txtCorreoElectronico.setText(contacto.getCorreo_electronico());
            activityConsultaBinding.txtTelefono.setText(contacto.getTelefono());

            activityConsultaBinding.txtNombre.setInputType(InputType.TYPE_NULL);
            activityConsultaBinding.txtTelefono.setInputType(InputType.TYPE_NULL);
            activityConsultaBinding.txtCorreoElectronico.setInputType(InputType.TYPE_NULL);
        }

        activityConsultaBinding.fabEditar.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent editarActivityIntent = new Intent(getApplicationContext(), EditarActivity.class).putExtra("ID", id);
                startActivity(editarActivityIntent);
                Toast.makeText(getApplicationContext(), "editar", Toast.LENGTH_SHORT).show();

            }
        });

        activityConsultaBinding.fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(ConsultaActivity.this);
                builder.setMessage("¿Desea eliminar este contacto").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i)
                    {
                        if (dbContactos.eliminarContacto(id))
                        {
                            startActivity(new Intent(getApplicationContext(), SQLiteActivity.class));
                        }

                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                    }
                }).show();

            }
        });

    }
}