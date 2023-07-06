package com.example.comupncorteztejada.Repositories;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.comupncorteztejada.Entities.Cartas;

import java.util.List;

@Dao
public interface CartasRepository {

    @Query("SELECT * FROM Cartas")
    List<Cartas> getAllCartas();

    @Insert
    void create(Cartas cartas);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Cartas> cartas);

    @Update
    void updateCarta(Cartas cartas);

    @Query("SELECT MAX(id) FROM Cartas")
    int getLastId();

    @Query("SELECT * FROM Cartas WHERE id = :cartaId")
    Cartas findCartasById(int cartaId);

    @Query("SELECT * FROM Cartas WHERE sincro = 0")
    List<Cartas> getUnsyncedCartas();

    @Query("SELECT * FROM Cartas WHERE duelistaid = :duelistaid")
    List<Cartas> getCartasByDuelistaId(int duelistaid);
}
