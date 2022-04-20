package com.rent.entidades;

public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private int cantidad;
    private int cantidadDisponible;

    public Libro(String isbn, String titulo, String autor, int cantidad) {
        // VALIDACIONES
        this.validarCantidadBiblioteca(cantidad);

        this.crearLibro(isbn, titulo, autor, cantidad);
    }

    public void crearLibro(String isbn, String titulo, String autor, int cantidad) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.cantidad = cantidad;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void validarCantidadBiblioteca(int cantidad) {
        if(cantidad<1) {
            throw new IllegalArgumentException("La cantidad en biblioteca no puede ser menor a 1");
        }
    }

    public boolean validarCantidadDisponible(int cantidad) {
        if(cantidad<1 || cantidad>getCantidad()) {
            return false;
        }
        return true;
    }

    public void eliminarLibro() {

    }

    public String getIsbn() {
        return isbn;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", cantidad=" + cantidad +
                ", cantidadDisponible=" + cantidadDisponible +
                '}';
    }
}
