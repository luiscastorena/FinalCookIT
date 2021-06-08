package com.example.finalcookit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class ModificarDatos extends AppCompatActivity {
String nuevo_nombre,nuevo_apellido,nuevo_correo,nombre,apellido,correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_datos);
        TextView hola_usuario;
        ImageView foto_perfil;
        Button button;
        TextInputEditText editTextusuario,editTextapellido,editTextcorreo;
        hola_usuario=findViewById(R.id.hola);
        editTextusuario=findViewById(R.id.editarnombre);
        editTextapellido=findViewById(R.id.editarapellidos);
        editTextcorreo=findViewById(R.id.editarcorreo);
        foto_perfil=findViewById(R.id.imageView);
        Picasso.get().load("https://d500.epimg.net/cincodias/imagenes/2016/07/04/lifestyle/1467646262_522853_1467646344_noticia_normal.jpg").into(foto_perfil);
        Intent extra = getIntent();
        if(extra!=null){
            Toast.makeText(this, "Antes de editar", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, ""+extra.getExtras().getString("usuario"), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, ""+extra.getExtras().getString("apellido"), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, ""+extra.getExtras().getString("correo"), Toast.LENGTH_SHORT).show();
            hola_usuario.setText("Hola "+extra.getExtras().getString("usuario"));
            editTextusuario.setText(extra.getExtras().getString("usuario"));
            editTextapellido.setText(extra.getExtras().getString("apellido"));
            editTextcorreo.setText(extra.getExtras().getString("correo"));
            nombre=extra.getExtras().getString("usuario");
            apellido=extra.getExtras().getString("apellido");
            correo=extra.getExtras().getString("correo");
        }
        button=findViewById(R.id.editardatos);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nuevo_nombre=editTextusuario.getText().toString();
                nuevo_apellido=editTextapellido.getText().toString();
                nuevo_correo=editTextcorreo.getText().toString();
                Toast.makeText(ModificarDatos.this, "Despues de editar", Toast.LENGTH_SHORT).show();
                Toast.makeText(ModificarDatos.this, ""+nuevo_nombre, Toast.LENGTH_SHORT).show();
                Toast.makeText(ModificarDatos.this, ""+nuevo_apellido, Toast.LENGTH_SHORT).show();
                Toast.makeText(ModificarDatos.this, ""+nuevo_correo, Toast.LENGTH_SHORT).show();
            }
        });



        BottomNavigationView bottomNavigationView;
        bottomNavigationView= findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.Sesion);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Inicio:
                        startActivity(new Intent(ModificarDatos.this, MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Recetas:
                        startActivity(new Intent(ModificarDatos.this, Recetas.class));
                        overridePendingTransition(0, 0);
                    case R.id.Categorias:
                        startActivity(new Intent(ModificarDatos.this, Categorias.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Sesion:
                        startActivity(new Intent(ModificarDatos.this, Sesion.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

}