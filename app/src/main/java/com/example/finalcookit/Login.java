package com.example.finalcookit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    String hola_usuario, apellido, correo;
    String FB_usuario, FB_correo, FB_foto;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView foto_perfil;
        TextView inicio, mis_datos, acerca, eliminar, hola;
        Intent intent = getIntent();
        inicio = findViewById(R.id.inicio);
        mis_datos = findViewById(R.id.misdatos);
        acerca = findViewById(R.id.aboutus);
        eliminar = findViewById(R.id.eliminarcuenta);
        button = findViewById(R.id.cerrarsesion);
        hola = findViewById(R.id.hola);
        foto_perfil = findViewById(R.id.imageView);
        apellido = intent.getExtras().getString("apellido");
        correo = intent.getExtras().getString("correo");
        FB_usuario = intent.getExtras().getString("nombreFB");
        FB_correo = intent.getExtras().getString("correoFB");
        FB_foto = intent.getExtras().getString("fotoFB");
        hola_usuario = intent.getExtras().getString("usuario");

        if(FB_usuario!=null){
            hola.setText("Hola " + FB_usuario + " Bienvenido");
            FB_foto = FB_foto + "?type=large";
            Picasso.get().load(FB_foto).centerCrop().resize(250, 250).into(foto_perfil);
            mis_datos.setVisibility(View.INVISIBLE);
            eliminar.setVisibility(View.INVISIBLE);
        }else{
            hola.setText("¡Hola " + hola_usuario + "!");
            mis_datos.setVisibility(View.INVISIBLE);
            eliminar.setVisibility(View.VISIBLE);
            Picasso.get().load("https://d500.epimg.net/cincodias/imagenes/2016/07/04/lifestyle/1467646262_522853_1467646344_noticia_normal.jpg").into(foto_perfil);
        }
        mis_datos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,ModificarDatos.class);
                startActivity(intent);

            }
        });
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        acerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AcercaNosotros.class);
                startActivity(intent);
            }
        });


        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setTitle("Eliminación de cuenta");
                builder.setMessage("Estás seguro?");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Login.this, "Dijo Si :(", Toast.LENGTH_SHORT).show();
                        eliminarcuenta();
                        Intent inicio = new Intent(Login.this,Sesion.class);
                        startActivity(inicio);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Login.this, "Dijo que no :)", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                builder.show();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FB_usuario != null) {
                    FirebaseAuth.getInstance().signOut();
                    LoginManager.getInstance().logOut();
                    Toast.makeText(Login.this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Sesion.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Sesión cerrada ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Sesion.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.Sesion);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Inicio:
                        startActivity(new Intent(Login.this, MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Recetas:
                        startActivity(new Intent(Login.this, Recetas.class));
                        overridePendingTransition(0, 0);
                    case R.id.Categorias:
                        startActivity(new Intent(Login.this, Categorias.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Sesion:
                        startActivity(new Intent(Login.this, Sesion.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

    }

    private void eliminarcuenta() {
        String URL = "http://192.168.100.111/xampp/ConexionAndroid/eliminar.php";
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (!response.isEmpty()) {

                    Toast.makeText(Login.this, "Cuenta eliminada", Toast.LENGTH_SHORT).show();

                } else if (response.isEmpty()) {
                    Toast.makeText(Login.this, "Vuelva a intentar.", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("correo", correo);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
        requestQueue.add(request);
    }
}