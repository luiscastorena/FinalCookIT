package com.example.finalcookit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_FinalCookIT);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView;
        bottomNavigationView= findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.Inicio);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Inicio:
                        return true;
                    case R.id.Recetas:
                        startActivity(new Intent(getApplicationContext(), Recetas.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Categorias:
                        startActivity(new Intent(getApplicationContext(), Categorias.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Sesion:
                        startActivity(new Intent(getApplicationContext(), Sesion.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(new GaleriaImagenes(getApplicationContext()));




    }
}
