package com.example.comupncorteztejada.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Cartas")
public class Cartas {

    @PrimaryKey()
    public int id;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "puntosAtaque")
    private int puntosAtaque;

    @ColumnInfo(name = "puntosDefensa")
    private int puntosDefensa;

    @ColumnInfo(name = "imagen")
    private String imagen;

    @ColumnInfo(name = "lati")
    private String lati;

    @ColumnInfo(name = "longi")
    private String longi;

    @ColumnInfo(name = "duelistaid")
    private String duelistaid;

    @ColumnInfo(name = "sincro")
    private boolean sincro;




}
