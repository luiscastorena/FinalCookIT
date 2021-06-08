package com.example.finalcookit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class EditarDatos extends AppCompatActivity {
    private String nombre,apellidos,correo;
    private String nombreVN,apellidosVN,correoVN;
    private TextInputEditText editTextnombre,editTextapellidos,editTextcorreo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_datos);

        Intent editardatos = getIntent();
        nombre=editardatos.getExtras().getString("nombre");
        apellidos=editardatos.getExtras().getString("apellido");
        correo=editardatos.getExtras().getString("correo");

        editTextnombre=findViewById(R.id.editarnombre);
        editTextapellidos=findViewById(R.id.editarapellidos);
        editTextcorreo=findViewById(R.id.editarcorreo);

        editTextnombre.setText(nombre);
        editTextapellidos.setText(apellidos);
        editTextcorreo.setText(correo);


    }
    /*public void EditarDatos(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    Toast.makeText(EditarDatos.this, "Datos actualizados", Toast.LENGTH_SHORT).show();
                }else if(response.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Hubo un error. Vuelva a intentar.", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("nombre",nombre);
                parametros.put("apellido",apellidos);
                parametros.put("correo",correo);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }*/
    }
