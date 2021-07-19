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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import bolts.Task;

public class Cuenta extends AppCompatActivity {
    private static final String TAG = "Login con contraseña";
    private FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
    private EditText emailEditText,contraseñaEditText;
    private Button buttonLogin;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.cuenta_layout);
        inicioSesion();
        BottomNavigationView bottomNavigationView;
        bottomNavigationView= findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.Cuenta);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Entradas:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        return true;
                    case R.id.Bebidas:
                        startActivity(new Intent(getApplicationContext(), Bebidas.class));
                        return true;
                    case R.id.Principales:
                        startActivity(new Intent(getApplicationContext(), Principales.class));
                        return true;
                    case R.id.Postres:
                        startActivity(new Intent(getApplicationContext(), Postres.class));
                        return true;
                    case R.id.Cuenta:
                        return true;

                }
                return false;
            }
        });
    }
    public void inicioSesion(){

        emailEditText = (EditText) findViewById(R.id.usuario);
        contraseñaEditText = (EditText) findViewById(R.id.contraseña);
        buttonLogin = (Button) findViewById(R.id.buttonSesion);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!emailEditText.getText().toString().isEmpty() && !contraseñaEditText.getText().toString().isEmpty()){
                    /*mAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), contraseñaEditText.getText().toString())
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {


                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isCompleted()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        //updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getError());
                                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }
                                }
                            });*/
                }
            }
        });
    }
}