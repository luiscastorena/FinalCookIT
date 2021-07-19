package com.example.finalcookit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Sesion extends AppCompatActivity {
    private CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth;
    private LoginButton loginButton;
    private FirebaseAuth.AuthStateListener authStateListener;
    private final static String TAG = "Facebook Authentication";
    private AccessTokenTracker accessTokenTracker;
    private ImageView fotoUsuario;
    public EditText usuario, contraseña;
    public Button botonsesion;
    public TextView registrar;
    public JSONArray jsonArray;
    public String valorUsuario,valorContraseña;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);
        firebaseAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");
        usuario=findViewById(R.id.usuario);
        contraseña=findViewById(R.id.contraseña);
        callbackManager = CallbackManager.Factory.create();
        registrar = findViewById(R.id.registrar);
        botonsesion=findViewById(R.id.buttonSesion);
        botonsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidarUsuario();
                /*Intent login = new Intent(getApplicationContext(),LoginS.class);
                startActivity(login);*/
            }
        });

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "Login exitoso" + loginResult);
                handleFacebookToken(loginResult.getAccessToken());
            }
            @Override
            public void onCancel() {
                Log.d(TAG, "Login cancelado");

            }
            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "Error en el login. Vuelva a intentar.");
            }
        });
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    updateUI(user);
                } else {
                    updateUI(null);
                }
            }
        };

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (currentAccessToken == null) {
                    firebaseAuth.signOut();
                }
            }
        };

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(getApplication(), RegistrarUsuario
                        .class);
                startActivity(registrar);
            }
        });
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.Cuenta);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Entradas:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Bebidas:
                        startActivity(new Intent(getApplicationContext(), Recetas.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Principales:
                        startActivity(new Intent(getApplicationContext(), Categorias.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Postres:
                        return true;
                    case R.id.Cuenta:
                        startActivity(new Intent(getApplicationContext(), Categorias.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

    }

    private void handleFacebookToken(AccessToken token) {
        Log.d(TAG, "HandleFaceToken" + token);
        AuthCredential credential = FacebookAuthProvider.getCredential((token.getToken()));
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Login Exitoso");
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    Log.d(TAG, "Fallo en la autenticación");

                }
            }
        });
    }
    public void validacionUsuario(String url){
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    Intent respo = new Intent(getApplicationContext(),LoginS.class);
                    startActivity(respo);
                }else{
                    Toast.makeText(Sesion.this, "Usuario o contraseña inválidos.", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Sesion.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("usuario",usuario.getText().toString());
                parametros.put("contraseña",contraseña.getText().toString());
                return parametros;
            }
        };
    }
    private void ValidarUsuario(){
        String URL= "http://192.168.100.111/xampp/ConexionAndroid/ValidarUsuario.php";
        Intent intent = new Intent(Sesion.this,Login.class);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String usuario = null,apellido = null,correo = null;
                if(response.length()>2){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        usuario=jsonObject.getString("nombre");
                        apellido=jsonObject.getString("apellido");
                        correo=jsonObject.getString("correo");
                        } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else if( response.length()<=2){
                    Toast.makeText(Sesion.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
                if(response.length()>2){
                    intent.putExtra("usuario",usuario);
                    intent.putExtra("apellido",apellido);
                    intent.putExtra("correo",correo);
                    startActivity(intent);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Sesion.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("usuario",usuario.getText().toString());
                parametros.put("contraseña",contraseña.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
    private void updateUI(FirebaseUser user){
        if(user!=null){
            String nombre,correo,foto;
            nombre = user.getDisplayName();
            correo = user.getEmail();
            foto=user.getPhotoUrl().toString();
            Intent update = new Intent(this,Login.class);
            update.putExtra("nombreFB",nombre);
            update.putExtra("correoFB",correo);
            update.putExtra("fotoFB",foto);
            startActivity(update);
        }
    }
    @Override
    protected void onStart(){
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
    @Override
    protected void onStop(){
        super.onStop();
        if(authStateListener!=null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

}