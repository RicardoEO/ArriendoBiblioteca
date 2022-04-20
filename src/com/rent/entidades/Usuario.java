package com.rent.entidades;

import java.util.ArrayList;
import java.util.Locale;

public class Usuario {
    private String nombreCompleto;
    private String RUN;
    private String genero;
    private String habilitadoPrestamo;

    public Usuario(String nom, String RUN, String genero) {
        // VALIDAR GENERO
        this.validarGenero(genero);
        // VALIDAR RUT
        //
        this.crearUsuario(nom, RUN, genero);
    }

    public void crearUsuario(String nom, String RUN, String genero) {
        this.nombreCompleto = nom;
        this.RUN = RUN;
        this.genero = genero;
        this.habilitadoPrestamo = "0";
    }

    public void editarUsuario() {

    }

    public String getHabilitadoPrestamo() {
        return habilitadoPrestamo;
    }

    public void setHabilitadoPrestamo(String habilitadoPrestamo) {
        this.habilitadoPrestamo = habilitadoPrestamo;
    }

    public String getGenero() {
        return genero;
    }

    public boolean validarGenero(String genero) {
        if(genero.toUpperCase().equals("M") || genero.toUpperCase().equals("F")) {
            return true;
        } else {
            throw new IllegalArgumentException("El género ingresado no esta permitido");
        }
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setRUN(String RUN) {
        this.RUN = RUN;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRUN() {
        return RUN;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", RUN='" + RUN + '\'' +
                ", genero='" + genero + '\'' +
                ", habilitadoPrestamo=" + habilitadoPrestamo +
                '}';
    }
}
