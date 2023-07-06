package com.example.comupncorteztejada;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.comupncorteztejada.Adapters.DuelistaAdapter;
import com.example.comupncorteztejada.BD.AppDatabase;
import com.example.comupncorteztejada.Entities.Duelista;
import com.example.comupncorteztejada.Repositories.DuelistaRepository;
import com.example.comupncorteztejada.Service.DuelistaService;
import com.example.comupncorteztejada.Utilities.RetrofitU;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListaDuelistaActivity extends AppCompatActivity {

    RecyclerView mRvListaDuel;
    boolean mIsLoading = false;
    int mPage = 1;
    List<Duelista> mdata = new ArrayList<>();
    DuelistaAdapter mAdapter = new DuelistaAdapter(mdata, this);
    Context context = this;
    String currentFilter = "";
    Retrofit mRetrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_duelista);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRvListaDuel =  findViewById(R.id.rvListaDuelistas);
        mRvListaDuel.setLayoutManager(layoutManager);
        mRvListaDuel.setAdapter(mAdapter);

        Button btnActualizar = findViewById(R.id.bttnActualizarD);
        Button btnRegistro = findViewById(R.id.bttnRegistroD);

        mRvListaDuel.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!mIsLoading) {

                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == mdata.size() - 1) {
                        mPage++;
                        loadMore(mPage);
                    }
                }

            }
        });
        //Manda la lista desde base de datos para mostrarse
        AppDatabase db = AppDatabase.getInstance(context);
        DuelistaRepository repository = db.duelistaRepository();
        List<Duelista> duelistas = repository.getAllDuelistas();
        Log.i("MAIN_APP: DB", new Gson().toJson(duelistas));
        mAdapter.setDuelistas(duelistas);
        mAdapter.notifyDataSetChanged();

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadToWebService(currentFilter, mPage);
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(ListaDuelistaActivity.this, RegistroDuelistaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mRvListaDuel.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!mIsLoading) {

                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == mdata.size() - 1) {
                        mPage++;
                        loadMore(mPage);
                    }
                }

            }
        });

    }

    private void uploadToWebService(String filter, int nextPage) {

        AppDatabase db = AppDatabase.getInstance(context);
        db.clearAllTables();

        mRetrofit = RetrofitU.build();

        DuelistaService service = mRetrofit.create(DuelistaService.class);
        service.getAllDuelista(50, nextPage).enqueue(new Callback<List<Duelista>>() {
            @Override
            public void onResponse(Call<List<Duelista>> call, Response<List<Duelista>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Inserta los datos en la base de datos
                    AppDatabase db = AppDatabase.getInstance(ListaDuelistaActivity.this);
                    DuelistaRepository repository = db.duelistaRepository();
                    repository.insertAll(response.body());

                    // Actualiza los datos en el adaptador y notifica los cambios
                    List<Duelista> newData = repository.getAllDuelistas();
                    mAdapter.setDuelistas(newData);
                    mAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<Duelista>> call, Throwable t) {
                // Maneja el error de la llamada al servicio MockAPI
            }
        });
    }


    private void loadMore(int nextPage) {
        mIsLoading = true;

        mdata.add(null);
        mAdapter.notifyItemInserted(mdata.size() - 1);

        DuelistaService service = mRetrofit.create(DuelistaService.class);
        Log.i("MAIN_APP  Page:", String.valueOf(nextPage));
        service.getAllDuelista(100, nextPage).enqueue(new Callback<List<Duelista>>() { // Cambia el número de registros por página según tus necesidades
            @Override
            public void onResponse(Call<List<Duelista>> call, Response<List<Duelista>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mdata.remove(mdata.size() - 1);
                    mAdapter.notifyItemRemoved(mdata.size() - 1);

                    mdata.addAll(response.body());
                    mAdapter.notifyDataSetChanged();
                    mIsLoading = false;

                    // Si hay más registros disponibles, cargar la siguiente página
                    if (response.body().size() >= 100) { // Cambia el número de registros por página según tus necesidades
                        loadMore(nextPage + 1);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Duelista>> call, Throwable t) {
                // Manejar error de la llamada al servicio MockAPI
            }
        });
    }
}