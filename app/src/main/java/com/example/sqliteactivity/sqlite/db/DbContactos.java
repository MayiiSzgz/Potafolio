package com.example.sqliteactivity.sqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.sqliteactivity.sqlite.modelo.Contacto;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.xml.transform.dom.DOMLocator;

public class DbContactos extends DbHelper
{
    Context context;

    public DbContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarContacto(String nombre, String telefono, String correo_electronico)
    {
        long id = 0;
        try
        {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono",telefono);
            values.put("correo_electronico", correo_electronico);
            id = db.insert(TABLE_CONTACTOS, null, values);
        }
        catch(Exception e)
        {
            e.printStackTrace();

        }
        return id;
    }

    public ArrayList <Contacto> obtenerContactos()
    {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Contacto> contactos = new ArrayList<>();
        Contacto contacto = null;
        Cursor cursorContactos = null;

        cursorContactos = db.rawQuery(  "SELECT * FROM " + TABLE_CONTACTOS, null);

        if(cursorContactos.moveToFirst())
        {
            do
            {
                contacto = new Contacto();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setNombre(cursorContactos.getString(1));
                contacto.setTelefono(cursorContactos.getString(2));
                contacto.setCorreo_electronico(cursorContactos.getString(3));
                contactos.add(contacto);
            }while(cursorContactos.moveToNext());
        }
        cursorContactos.close();
        return contactos;
    }


}
