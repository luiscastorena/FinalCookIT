package com.example.finalcookit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
//Clase principal
public class MainActivity extends AppCompatActivity implements  SearchView.OnQueryTextListener{
    private RecyclerView courseRV;
    private ArrayList<llenargridview> llenargridviews;
    CourseAdapter courseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_FinalCookIT);
        setContentView(R.layout.activity_main);
        courseRV = findViewById(R.id.rv);
        SearchView searchView;
        searchView = findViewById(R.id.buscar);
        searchView.setOnQueryTextListener(this);

        llenargridviews = new ArrayList<>();
        llenargridviews.add(new llenargridview("Tortilla de Patatas",R.drawable.tortillapatatas));
        llenargridviews.add(new llenargridview("Flan",R.drawable.flan));
        llenargridviews.add(new llenargridview("Huevos divorciados",R.drawable.huevosdivorciados));
        llenargridviews.add(new llenargridview("Hamburguesa al carbón",R.drawable.hamburguesaalcarbon));
        llenargridviews.add(new llenargridview("Pechuga Empanizada",R.drawable.pechugaempanizada));
        llenargridviews.add(new llenargridview("Chilaquiles rojos",R.drawable.chilaquilesrojos));
        llenargridviews.add(new llenargridview("Tacos Mexicanos",R.drawable.tacosmexicanos));
        llenargridviews.add(new llenargridview("Pollo a la naranja",R.drawable.pollonaranja));
        llenargridviews.add(new llenargridview("Spaguetti Carbonara",R.drawable.spaguetticarbonara));
        llenargridviews.add(new llenargridview("Banderillas",R.drawable.banderillas));
        llenargridviews.add(new llenargridview("Palitos de queso",R.drawable.palitosqueso));
        llenargridviews.add(new llenargridview("Galletas de mantequilla",R.drawable.galletasmantequilla));
        llenargridviews.add(new llenargridview("Nuggets de pollo",R.drawable.nuggets));
        llenargridviews.add(new llenargridview("Alitas Bufalo",R.drawable.bufalowings));
        llenargridviews.add(new llenargridview("Pechuga Cordon Bleu",R.drawable.pechugacordonbleu));
        llenargridviews.add(new llenargridview("Aros de Cebolla",R.drawable.aroscebolla));

        courseAdapter = new CourseAdapter(this,llenargridviews);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(courseAdapter);
        BottomNavigationView bottomNavigationView;
        bottomNavigationView= findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.Entradas);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Entradas:
                        return true;
                    case R.id.Bebidas:
                        startActivity(new Intent(getApplicationContext(), Bebidas.class));
                        overridePendingTransition(0, 0);

                        return true;
                    case R.id.Principales:
                        startActivity(new Intent(getApplicationContext(), Principales.class));
                        overridePendingTransition(0, 0);

                        return true;
                    case R.id.Postres:
                        startActivity(new Intent(getApplicationContext(), Postres.class));
                        overridePendingTransition(0, 0);

                        return true;
                    case R.id.Cuenta:
                        startActivity(new Intent(getApplicationContext(), Cuenta.class));
                        overridePendingTransition(0, 0);

                        return true;
                }
                return false;
            }
        });

       // GridView gridView = (GridView) findViewById(R.id.grid_view);
        //gridView.setAdapter(new GaleriaImagenes(getApplicationContext()));




    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        courseAdapter.filtrado(newText);
        return false;
    }
}
