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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntosAtaque() {
        return puntosAtaque;
    }

    public void setPuntosAtaque(int puntosAtaque) {
        this.puntosAtaque = puntosAtaque;
    }

    public int getPuntosDefensa() {
        return puntosDefensa;
    }

    public void setPuntosDefensa(int puntosDefensa) {
        this.puntosDefensa = puntosDefensa;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getLati() {
        return lati;
    }

    public void setLati(String lati) {
        this.lati = lati;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getDuelistaid() {
        return duelistaid;
    }

    public void setDuelistaid(String duelistaid) {
        this.duelistaid = duelistaid;
    }

    public boolean isSincro() {
        return sincro;
    }

    public void setSincro(boolean sincro) {
        this.sincro = sincro;
    }
}
