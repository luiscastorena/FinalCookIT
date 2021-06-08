package com.example.finalcookit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Categorias extends AppCompatActivity {
    private RecyclerView courseRV;

    // Arraylist for storing data
    private CategoriasAdapter categoriasAdapter;
    private ArrayList<llenarcategorias> llenarcategorias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        courseRV = findViewById(R.id.rv);
        llenarcategorias = new ArrayList<>();
        llenarcategorias.add(new llenarcategorias("Vegana",R.drawable.categoriavegana));
        llenarcategorias.add(new llenarcategorias("Mexicana",R.drawable.categoriamexicana));
        llenarcategorias.add(new llenarcategorias("Saludable",R.drawable.categoriasaludable));
        llenarcategorias.add(new llenarcategorias("Postres",R.drawable.categoriapostres));
        llenarcategorias.add(new llenarcategorias("Desayunps",R.drawable.comida));
        llenarcategorias.add(new llenarcategorias("Cena",R.drawable.cena));
        categoriasAdapter = new CategoriasAdapter(this, llenarcategorias);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(categoriasAdapter);

        BottomNavigationView bottomNavigationView;
        bottomNavigationView= findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.Categorias);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Inicio:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Recetas:
                        startActivity(new Intent(getApplicationContext(), Recetas.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Categorias:
                        return true;
                    case R.id.Sesion:
                        startActivity(new Intent(getApplicationContext(), Sesion.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}