package com.example.comupncorteztejada.Repositories;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.comupncorteztejada.Entities.Duelista;

import java.util.List;

@Dao
public interface DuelistaRepository {

    @Query("SELECT * FROM Duelistas")
    List<Duelista> getAllDuelistas();

    @Insert
    void create(Duelista duelista);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Duelista> duelista);

    @Update
    void updateDuelista(Duelista duelista);

    @Query("SELECT MAX(id) FROM Duelistas")
    int getLastId();

    @Query("SELECT * FROM Duelistas WHERE id = :duelistaId")
    Duelista findDuelistaById(int duelistaId);

    @Query("SELECT * FROM Duelistas WHERE sincro = 0")
    List<Duelista> getUnsyncedDuelista();



}
