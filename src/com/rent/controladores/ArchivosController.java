package com.rent.controladores;

import com.rent.entidades.Libro;
import com.rent.entidades.Usuario;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArchivosController {
    public void guardarClientes(Usuario usuario) throws IOException {
        FileWriter writer = new FileWriter("clientes.txt", true);
        writer.write(usuario.getNombreCompleto() + ";" +
                usuario.getRUN() + ";" + usuario.getGenero() + ";" +
                usuario.getHabilitadoPrestamo()
                + System.lineSeparator());
        writer.close();
    }

    public void guardarLibros(Libro libro) throws IOException {
        FileWriter writer = new FileWriter("libros.txt", true);
        writer.write(libro.getIsbn() + ";" +
                libro.getTitulo() + ";" + libro.getAutor() + ";" +
                libro.getCantidad()
                + System.lineSeparator());
        writer.close();
    }

    public boolean cargarClientes(ArrayList<Usuario> usuariosRegistrados) {
        try
        {
            String[] linea;
            File file = new File("clientes.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine())  {
                linea = sc.nextLine().split(";");
                String nombre = linea[0];
                String RUN = linea[1];
                String genero = linea[2];
                usuariosRegistrados.add(new Usuario(nombre, RUN, genero));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public boolean cargarLibros(ArrayList<Libro> librosRegistrados) {
        try
        {
            String[] linea = new String[0];
            File file = new File("libros.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine())  {
                linea = sc.nextLine().split(";");
                String isbn = linea[0];
                String titulo = linea[1];
                String autor = linea[2];
                String cantidad = linea[3];
                librosRegistrados.add(new Libro(isbn, titulo, autor, Integer.parseInt(cantidad)));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
