package com.rent.entidades;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class Devolucion {
    private int multa;
    private Prestamo prestamo;

    public Devolucion(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public void ingresarDevolucion() {
        Libro libro = getPrestamo().getLibro();
        Prestamo prestamo = getPrestamo();
        if(LocalDate.now().isAfter(prestamo.getFechaDevolver())) {
            System.out.println("La devolucion tiene atraso");
            int days = (int) prestamo.getFechaDevolver().until(LocalDate.now(), ChronoUnit.DAYS);
            this.multa = 1000 * days;
            System.out.println("Tiene una multa de $" + this.multa + " pesos");
        }
        prestamo.getUsuario().setHabilitadoPrestamo("0");
        libro.setCantidadDisponible(libro.getCantidadDisponible() + 1);
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }
}
