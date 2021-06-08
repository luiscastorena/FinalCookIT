package com.example.finalcookit;

import android.content.Context;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.List;

public class Prueba extends AppCompatActivity {

    Button button;
   public  List<llenargridview> llenargridviewList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);


        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuarios("http://192.168.100.111/xampp/ConexionAndroid/Llenarcardview.php");

            }
        });
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.usuarios_textview, null);

        for (int i = 0; i < llenargridviewList.size(); i++) {
                    /*String correo, nombre;
                    correo = llenargridviewList.get(i).getCorreo();
                    nombre = llenargridviewList.get(i).getNombre();
                    TextView t1 = (TextView) view.findViewById(R.id.textViewL);
                    TextView t2 = (TextView) view.findViewById(R.id.textView2L);
                    t1.setText((CharSequence) llenargridviewList.get(i).getCorreo());
                    t2.setText((CharSequence) llenargridviewList.get(i).getNombre());
                    Toast.makeText(getApplicationContext(), "" + correo, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "" + nombre, Toast.LENGTH_SHORT).show();*/
            Toast.makeText(Prueba.this, "Lista: "+llenargridviewList.get(i).getNombre(), Toast.LENGTH_SHORT).show();

        }
        Toast toast = new Toast(getApplicationContext());
        toast.setView(view);
        toast.show();

    }

    public void Usuarios(String url) {
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        String nombre,correo,apellidos = null;
                        JSONObject jsonObject = response.getJSONObject(i);
                        nombre=jsonObject.getString("nombre");
                        correo=jsonObject.getString("correo");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                Toast.makeText(Prueba.this, ""+llenargridviewList.size(), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }
}
