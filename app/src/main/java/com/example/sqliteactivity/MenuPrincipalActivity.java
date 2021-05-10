package com.example.sqliteactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sqliteactivity.databinding.ActivityMainBinding;
import com.example.sqliteactivity.sqlite.SQLiteActivity;

public class MenuPrincipalActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        setClickListeners();

    }

    private void setClickListeners()
    {
        activityMainBinding.cvSqlite.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), SQLiteActivity.class ));
            }
        });
        activityMainBinding.cvMaps.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            }
        });
    }


}