package com.example.comupncorteztejada.BD;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.comupncorteztejada.Entities.Cartas;
import com.example.comupncorteztejada.Entities.Duelista;
import com.example.comupncorteztejada.Repositories.CartasRepository;
import com.example.comupncorteztejada.Repositories.DuelistaRepository;

@Database(entities = {Duelista.class, Cartas.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DuelistaRepository duelistaRepository();
    public abstract CartasRepository cartasRepository();

    public static AppDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "DuelistaDB")
                .allowMainThreadQueries()
                .build();
    }

}
