package com.example.comupncorteztejada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.comupncorteztejada.BD.AppDatabase;
import com.example.comupncorteztejada.Entities.Cartas;
import com.example.comupncorteztejada.Entities.Duelista;
import com.example.comupncorteztejada.Repositories.CartasRepository;
import com.example.comupncorteztejada.Repositories.DuelistaRepository;
import com.example.comupncorteztejada.Service.DuelistaService;
import com.example.comupncorteztejada.Utilities.RetrofitU;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetalleDuelistaActivity extends AppCompatActivity {

    Duelista duelista;
    Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_duelista);

        mRetrofit = RetrofitU.build();

        int position = getIntent().getIntExtra("position", 0);

        AppDatabase db = AppDatabase.getInstance(this);
        DuelistaRepository repository = db.duelistaRepository();
        Duelista duelista1 = repository.findDuelistaById(position);

        CartasRepository repositoryM = db.cartasRepository();
        List<Cartas> movimientos = repositoryM.getCartasByDuelistaId(position);


        TextView tvNombre = findViewById(R.id.tvNombreDuelista);
        Button bttnRegistrar = findViewById(R.id.DbtnRegistrar);
        Button bttnVerM = findViewById(R.id.DbtnVerCarta);
        Button bttnSincro = findViewById(R.id.DbtnSincro);

        tvNombre.setText(duelista1.getNombre());

        bttnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(DetalleDuelistaActivity.this, RegistroCartasActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        bttnVerM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(DetalleDuelistaActivity.this, ListaCartaActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        bttnSincro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!duelista1.isSincro()) {
                    DuelistaService service = mRetrofit.create(DuelistaService.class);
                    Duelista cuenta = new Duelista();
                    cuenta.setNombre(tvNombre.getText().toString());
                    cuenta.setSincro(true);

                    Call<Duelista> call = service.create(cuenta);

                    call.enqueue(new Callback<Duelista>() {
                        @Override
                        public void onResponse(Call<Duelista> call, Response<Duelista> response) {
                            Log.i("MAIN_APP",  String.valueOf(response.code()));

                            Intent intent =  new Intent(DetalleDuelistaActivity.this, ListaDuelistaActivity.class);
                            startActivity(intent);
                            finish();

                        }

                        @Override
                        public void onFailure(Call<Duelista> call, Throwable t) {

                        }
                    });
                }
            }
        });

    }
}