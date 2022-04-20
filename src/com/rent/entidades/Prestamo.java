package com.rent.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Prestamo {
    private LocalDate fechaPrestamo;
    private int diasPrestamo;
    private Usuario usuario;
    private Libro libro;
    private LocalDate fechaDevolver;

    public Prestamo(int diasPrestamo, Usuario usuario, Libro libro) {
        this.diasPrestamo = diasPrestamo;
        this.usuario = usuario;
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now();
        this.setFechaDevolver(this.getFechaPrestamo().plusDays(diasPrestamo));

    }

    public void generarTarjeta() {

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaDevolver(LocalDate fechaDevolver) {
        this.fechaDevolver = fechaDevolver;
    }

    public LocalDate getFechaDevolver() {
        return fechaDevolver;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "fechaPrestamo=" + fechaPrestamo +
                ", diasPrestamo=" + diasPrestamo +
                ", usuario=" + usuario +
                ", libro=" + libro +
                ", fechaDevolver=" + fechaDevolver +
                '}';
    }
}
