package com.example.finalcookit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Bebidas extends AppCompatActivity implements  SearchView.OnQueryTextListener{

    private RecyclerView mBlogList;
    private FirebaseFirestore mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entradas);

        mDatabase=FirebaseFirestore.getInstance();
        mBlogList=findViewById(R.id.rv);



        SearchView searchView;
        searchView = findViewById(R.id.buscar);
        searchView.setOnQueryTextListener(this);
        BottomNavigationView bottomNavigationView;
        bottomNavigationView= findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.Bebidas);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Entradas:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Bebidas:
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        Query query = mDatabase.collection("SmartOrder");
        //FirestoreRecyclerOptions<ClaseBebidas> options = new FirebaseRecyclerOptions.Builder<ClaseBebidas>().setQuery(query,ClaseBebidas.class).build();



    }


    public static class  BebidasViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public BebidasViewHolder(View itemView){
            super(itemView);
            mView=itemView;
        }
        public void setTitulo(String titulo){
            TextView tv_titulo=(TextView)mView.findViewById(R.id.titulo_receta);
            tv_titulo.setText(titulo);
        }
        public void setImagen(Context context, String imagen){
            ImageView imagenIV = (ImageView)mView.findViewById(R.id.IV_imagen);
            Picasso.get().load(imagen).into(imagenIV);
        }
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //courseAdapter.filtrado(newText);
        return false;
    }
}