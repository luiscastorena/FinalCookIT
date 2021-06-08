package com.example.finalcookit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegistrarUsuario extends AppCompatActivity {
    TextInputEditText nombre;
    TextInputEditText apellidos;
    TextInputEditText correo;
    TextInputEditText contraseña;
    String str_nombre,str_apellidos,str_correo,str_contraseña;
    Button botonRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        nombre= findViewById(R.id.nombreinput);
        apellidos=findViewById(R.id.apellidosinput);
        correo= findViewById(R.id.correoinput);
        contraseña=findViewById(R.id.passwordinput);
        botonRegistrar= findViewById(R.id.buttonRegistrar);
        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registrar("http://192.168.100.111/xampp/ConexionAndroid/Insertar.php");

            }
        });


    }
    public void moveToLogin(View view){
        Intent intent = new Intent(getApplicationContext(),LoginS.class);
        finish();
    }
    public boolean validarEmail(String correo){
        Pattern pattern= Patterns.EMAIL_ADDRESS;
        return pattern.matcher(correo).matches();
    }
    public void Registrar(String url){
         ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Porfavor espere...");
        if(nombre.getText().toString().isEmpty()){
            Toast.makeText(this, "Porfavor ingrese el nombre.", Toast.LENGTH_SHORT).show();
        }
        else if(apellidos.getText().toString().isEmpty()){
            Toast.makeText(this, "Porfavor ingrese sus apellidos", Toast.LENGTH_SHORT).show();
        }else if(correo.getText().toString().isEmpty()){
            Toast.makeText(this, "Porfavor ingrese un correo", Toast.LENGTH_SHORT).show();
        }else if(!validarEmail(correo.getText().toString())){
            Toast.makeText(this, "Ingrese un correo válido", Toast.LENGTH_SHORT).show();
        }
        else if(contraseña.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingrese contraseña.", Toast.LENGTH_SHORT).show();
        } else{

            progressDialog.show();
            str_nombre=nombre.getText().toString().trim();
            str_apellidos=apellidos.getText().toString().trim();
            str_correo=correo.getText().toString().trim();
            str_contraseña=contraseña.getText().toString().trim();


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(!response.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Usuario registrado.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),LoginS.class);
                        startActivity(intent);
                    }else if (response.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Hubo un error. Vuelva a intentar.", Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                    nombre.setText("");
                    apellidos.setText("");
                    correo.setText("");
                    contraseña.setText("");
                    }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    //Toast.makeText(RegistrarUsuario.this, "Error en el registro. Vuelva a intentar."+error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ){
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> parametros = new HashMap<String,String>();
                    parametros.put("nombre",str_nombre);
                    parametros.put("apellido",str_apellidos);
                    parametros.put("correo",str_correo);
                    parametros.put("contraseña",str_contraseña);
                    return parametros;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);
        }

    }
}