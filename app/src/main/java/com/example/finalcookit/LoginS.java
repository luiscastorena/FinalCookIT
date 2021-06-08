package com.example.finalcookit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.Image;
import android.media.MediaSync;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class LoginS extends AppCompatActivity {
    public ImageView imageView;
    public TextView hola,mis_datos,acerca_de,inicio,eliminar;
    public Button button;
    public String nombre,apellido,correo;
    public JSONArray jsonArray;
    public JSONObject jsonObject;
    public String usuario, correoFB, fotoURL;
    String correoJO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_s);

        //Aqui se trae los valores del otro activity
        Intent intent = getIntent();
        nombre=intent.getExtras().getString("usuario");
        apellido=intent.getExtras().getString("apellido");
        correo=intent.getExtras().getString("correo");
        hola.setText("¡Hola "+nombre+" Bienvenido!");

        hola=findViewById(R.id.hola);
        inicio=findViewById(R.id.inicio);
        imageView =findViewById(R.id.imageView);
        mis_datos=findViewById(R.id.misdatos);
        acerca_de=findViewById(R.id.aboutus);
        button = findViewById(R.id.cerrarsesion);
        eliminar=findViewById(R.id.eliminarcuenta);



        //Revisamos si el usuario es de Facebook
        if (usuario != null) {


            fotoURL=fotoURL+"?type=large";
            hola.setText("Hola "+usuario+" Bienvenido");
            Picasso.get().load(fotoURL).centerCrop().resize(250, 250).into(imageView);
            inicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
            mis_datos.setVisibility(View.INVISIBLE);
            acerca_de.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), AcercaNosotros.class);
                    startActivity(intent);
                }
            });
            eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    LoginManager.getInstance().logOut();
                    Toast.makeText(LoginS.this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Sesion.class);
                    startActivity(intent);
                }
            });
        }
        else {
            inicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
            mis_datos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Intent intent = new Intent(getApplicationContext(), EditarDatos.class);
                   /* intent.putExtra("nombre",nombre);
                    intent.putExtra("apellido",apellido);
                    intent.putExtra("correo",correo);*/
                   // startActivity(intent);
                }
            });
            acerca_de.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), AcercaNosotros.class);
                    startActivity(intent);
                }
            });
            eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(LoginS.this, "Sesión cerrada ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginS.this,Sesion.class);
                    startActivity(intent);
                }
            });

        }


    }
    private void traerDatos(String url){
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(LoginS.this, "Entro al onresponse", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject jsonObject = response.getJSONObject(i);
                        nombre = jsonObject.getString("nombre");
                        correoJO = jsonObject.getString("correo");
                        Toast.makeText(LoginS.this, ""+nombre, Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        Toast.makeText(LoginS.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(LoginS.this, ""+volleyError, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("correo",correoJO);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);


    }
    private void TraerDatos(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    Toast.makeText(LoginS.this, "Entro al response", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginS.this, "nel no entro", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"OnErrorResponse"+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("correo", nombre);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}