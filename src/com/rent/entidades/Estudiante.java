package com.rent.entidades;

public class Estudiante extends Usuario{

    private String carrera;

    public Estudiante(String nom, String RUN, String genero, String carrera) {

        super(nom, RUN, genero);
        this.carrera = carrera;
    }

    public String getCarrera() {
        return carrera;
    }
}
