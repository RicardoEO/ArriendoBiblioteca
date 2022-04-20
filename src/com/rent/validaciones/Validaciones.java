package com.rent.validaciones;

import com.rent.entidades.*;

import java.util.ArrayList;

public class Validaciones {

    public boolean existeRut(ArrayList<Usuario> usuariosRegistrados, String rut) {
        Boolean existe = false;
        for (Usuario user : usuariosRegistrados) {
            if(user.getRUN().equals(rut)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeLibro(ArrayList<Libro> librosRegistrados, String ISBN) {
        Boolean existe = false;
        for (Libro libro : librosRegistrados) {
            if(libro.getIsbn().equals(ISBN)) {
                return true;
            }
        }
        return false;
    }

    public Usuario getUserByRut(ArrayList<Usuario> usuariosRegistrados, String rut) {
        Usuario usuarioByRut = null;
        for (Usuario user : usuariosRegistrados) {
            if(user.getRUN().equals(rut)) {
                usuarioByRut = user;
            }
        }

        return usuarioByRut;
    }


    public Libro getLibroByISBN(ArrayList<Libro> librosRegistrados, String ISBN) {
        Libro libroByRut = null;
        for (Libro libro : librosRegistrados) {
            if(libro.getIsbn().equals(ISBN)) {
                libroByRut = libro;
            }
        }

        return libroByRut;
    }

    public Prestamo getPrestamoByUsuarioAndLibro(ArrayList<Prestamo> prestamosRegistrados, Usuario usuario, Libro libro) {
        Prestamo prestamo = null;
        for (Prestamo prestamoIt : prestamosRegistrados) {
            if(prestamoIt.getUsuario().equals(usuario) && prestamoIt.getLibro().equals(libro)) {
                prestamo = prestamoIt;
            }
        }

        return prestamo;
    }

    public boolean validarDias(Usuario usuario, int dias) {
        if(usuario instanceof Docente) {
            if(dias > 20) {
                return false;
            }
        }
        if(usuario instanceof Estudiante) {
            if(dias > 10) {
                return false;
            }
        }
        return true;
    }

}
