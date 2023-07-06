package com.example.comupncorteztejada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.comupncorteztejada.BD.AppDatabase;
import com.example.comupncorteztejada.Entities.Duelista;
import com.example.comupncorteztejada.Repositories.DuelistaRepository;
import com.google.gson.Gson;

public class RegistroDuelistaActivity extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_duelista);

        Button btnRegistrar = findViewById(R.id.bttnRegistrar);
        Button btnVolver = findViewById(R.id.bttnVolver);
        EditText etNombre = findViewById(R.id.etNombre);


        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(RegistroDuelistaActivity.this, ListaDuelistaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // llamar a retrofit
                AppDatabase database = AppDatabase.getInstance(context);
                DuelistaRepository cuentaRepository = database.duelistaRepository();

                // Obtener el último ID registrado en la base de datos
                int lastId = cuentaRepository.getLastId();

                Duelista cuenta = new Duelista();
                cuenta.setId(lastId + 1); // Asignar el nuevo ID incrementado en uno
                cuenta.setNombre(etNombre.getText().toString());
                cuenta.setSincro(false);

                cuentaRepository.create(cuenta);
                Log.i("MAIN_APP: DB", new Gson().toJson(cuenta));
                Intent intent = new Intent(RegistroDuelistaActivity.this, ListaDuelistaActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}