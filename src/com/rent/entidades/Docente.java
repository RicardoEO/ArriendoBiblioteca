package com.rent.entidades;

import java.util.ArrayList;

public class Docente extends Usuario{

    private ArrayList<String> grados;
    private String profesion;

    public Docente(String nom, String RUN, String genero, ArrayList<String> grados, String profesion) {
        super(nom, RUN, genero);
        this.grados = new ArrayList<>();
        this.profesion = profesion;
    }



}
