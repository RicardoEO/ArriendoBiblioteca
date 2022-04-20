package com.rent;

import com.rent.controladores.ArchivosController;
import com.rent.entidades.*;
import com.rent.validaciones.Validaciones;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // LEER ARCHIVO
        Validaciones validaciones = new Validaciones();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        ArrayList<Usuario> usuariosRegistrados = new ArrayList<Usuario>();
        ArrayList<Libro> librosRegistrados = new ArrayList<Libro>();
        ArrayList<Prestamo> prestamosRegistrados = new ArrayList<Prestamo>();
        ArchivosController archivosController = new ArchivosController();

        // MENU
        do {
            System.out.println("Arriendo de Libros\n");
            System.out.print("1.) Crear Usuario \n");
            System.out.print("2.) Editar Usuario \n");
            System.out.print("3.) Eliminar Usuario \n");
            System.out.print("4.) Crear Libro \n");
            System.out.print("5.) Eliminar Libro \n");
            System.out.print("6.) Prestar Libro \n");
            System.out.print("7.) Devolver Libro \n");
            System.out.print("8.) Salir\n");
            System.out.print("\nIngrese su opcion: ");
            opcion = scanner.nextInt();
            System.out.println();

            switch(opcion) {

                case 1:
                    try {
                        System.out.print("Ingrese el nombre del usuario\n");
                        String nombre = scanner.next();
                        System.out.print("Ingrese el rut del usuario\n");
                        String rut = scanner.next();
                        if(validaciones.existeRut(usuariosRegistrados, rut)) {
                            throw new RuntimeException("El rut ya está registrado");
                        }

                        System.out.print("Ingrese el género del usuario\n");
                        String genero = scanner.next();
                        // ELEGIR TIPO DE USUARIO
                        String eleccion = null;
                        do {
                            System.out.println("Ingrese el tipo de usuario");
                            System.out.println("Docente o Estudiante");
                            eleccion = scanner.next().toUpperCase();
                            System.out.println(eleccion);
                        } while ((!eleccion.equals("DOCENTE")) && (!eleccion.equals("ESTUDIANTE")));


                        if(eleccion.equals("DOCENTE")) {
                            System.out.println("Ingrese la profesion del docente");
                            String profesion = scanner.next();
                            Docente usuario = new Docente(nombre, rut, genero, null, profesion);
                            usuariosRegistrados.add(usuario);
                            archivosController.guardarClientes(usuario);
                        } else if(eleccion.equals("ESTUDIANTE")) {
                            System.out.println("Ingrese la carrera del estudiante");
                            String carrera = scanner.next();
                            Estudiante usuario = new Estudiante(nombre, rut, genero, carrera);
                            usuariosRegistrados.add(usuario);
                            archivosController.guardarClientes(usuario);
                        }
                        //Usuario usuario = new Usuario(nombre, rut, genero);

                        System.out.println("Se ha generado el nuevo usuario");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el rut del usuario a editar\n");
                    String rutEditar = scanner.next();
                    Boolean existe = false;
                    for (Usuario user : usuariosRegistrados) {
                        if(user.getRUN().equals(rutEditar)) {
                            existe = true;
                            System.out.print("Ingrese el nuevo nombre del usuario\n");
                            String nombre = scanner.next();
                            user.setNombreCompleto(nombre);
                            System.out.print("Ingrese el nuevo género del usuario\n");
                            String genero = scanner.next();
                            user.validarGenero(genero);
                            user.setGenero(genero);
                            System.out.println("Usuario modificado con éxito");
                            break;
                        }
                    }
                    if(!existe) {
                        System.out.println("No existe el rut");
                    }
                    break;
                // ELIMINAR USUARIO
                case 3:
                    System.out.print("Ingrese el rut del usuario a eliminar\n");
                    String rutEliminar = scanner.next();
                    Boolean eliminado = usuariosRegistrados.removeIf(usuario -> usuario.getRUN().equals(rutEliminar));
                    if(eliminado) {
                        System.out.println("Se elimino al cliente con rut " + rutEliminar);
                    } else {
                        System.out.println("No existe el rut ingresado");
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Ingrese el nombre del libro\n");
                        String titulo = scanner.next();
                        System.out.print("Ingrese el ISBN del libro\n");
                        String isbn = scanner.next();

                        if(validaciones.existeLibro(librosRegistrados, isbn)) {
                            throw new RuntimeException("El isbn ya está registrado");
                        }

                        System.out.print("Ingrese el autor del libro\n");
                        String autor = scanner.next();

                        System.out.print("Ingrese la cantidad de libros\n");
                        int cantidad = scanner.nextInt();

                        Libro libro = new Libro(isbn, titulo, autor, cantidad);
                        // VALIDAR CANTIDAD DISPONIBLE

                        boolean validacion;
                        do {
                            System.out.print("Ingrese la cantidad disponible de libros\n");
                            int cantidadDisponible = scanner.nextInt();
                            validacion = libro.validarCantidadDisponible(cantidadDisponible);
                            if(!validacion) {
                                System.out.println("La cantidad disponible no puede ser mayor a la cantidad en biblioteca");
                            } else {
                                libro.setCantidadDisponible(cantidadDisponible);
                            }
                        } while(!validacion);

                        librosRegistrados.add(libro);
                        archivosController.guardarLibros(libro);


                        System.out.println("Se ha generado un nuevo libro");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

                case 5:
                    System.out.print("Ingrese el ISBN del libro a eliminar\n");
                    String ISBN = scanner.next();
                    Boolean libroEliminado = librosRegistrados.removeIf(libro -> libro.getIsbn().equals(ISBN));
                    if(libroEliminado) {
                        System.out.println("Se elimino el libro con ISBN: " + ISBN);
                    } else {
                        System.out.println("No existe el libro ingresado");
                    }
                    break;
                // PRESTAR LIBRO
                case 6:
                    System.out.println("Ingrese el ISBN del libro a prestar");
                    String isbnPrestar = scanner.next();
                    Boolean existeLibro = false;
                    if(validaciones.existeLibro(librosRegistrados, isbnPrestar)) {
                        Libro libro = validaciones.getLibroByISBN(librosRegistrados, isbnPrestar);
                        if(libro.getCantidadDisponible()>=1) {
                            System.out.println("Ingrese el rut del solicitante");
                            String rutSolicitante = scanner.next();
                            // VALIDAR RUT
                            if(validaciones.existeRut(usuariosRegistrados, rutSolicitante)) {
                                // VALIDAR HABILITADO PARA PRESTAMO
                                Usuario usuarioPrestamo = validaciones.getUserByRut(usuariosRegistrados, rutSolicitante);
                                if(usuarioPrestamo.getHabilitadoPrestamo() == "0") {

                                    // CREAR PRESTAMO
                                    System.out.println("Ingrese la cantidad de dias de prestamo");
                                    int diasPrestamo = scanner.nextInt();
                                    if(validaciones.validarDias(usuarioPrestamo, diasPrestamo)) {
                                        // SETEO EL PRESTAMO
                                        usuarioPrestamo.setHabilitadoPrestamo(libro.getIsbn());
                                        libro.setCantidadDisponible(libro.getCantidadDisponible()-1);
                                        Prestamo prestamo = new Prestamo(diasPrestamo, usuarioPrestamo, libro);
                                        prestamosRegistrados.add(prestamo);
                                    } else {
                                        System.out.println("Supera la cantidad maxima de dias de prestamo segun su perfil");
                                    }

                                    // SEGUIR ACCAAA
                                } else {
                                    System.out.println("El usuario no esta habilitado para un prestamo");
                                }
                            } else {
                                System.out.println("No existe el rut");
                            }
                        } else {
                            System.out.println("No hay stock para préstamo");
                        }
                    } else {
                        System.out.println("El libro no existe");
                    }

                    break;

                case 7:
                    System.out.println("Ingrese ISBN del libro a devolver");
                    String isbnDevolver = scanner.next();
                    if(validaciones.existeLibro(librosRegistrados, isbnDevolver))  {
                        System.out.println("Ingrese el RUN del usuario");
                        String runDevolver = scanner.next();
                        if(validaciones.existeRut(usuariosRegistrados, runDevolver)) {
                            // OBTENGO EL LIBRO Y EL USUARIO
                            Usuario usuarioDevuelve = validaciones.getUserByRut(usuariosRegistrados, runDevolver);
                            Libro libroDevuelto = validaciones.getLibroByISBN(librosRegistrados, isbnDevolver);
                            if(libroDevuelto.getIsbn().equals(usuarioDevuelve.getHabilitadoPrestamo())) {
                                // HABILITAR USUARIO
                                // OBTENER PRESTAMO
                                Prestamo prestamo = validaciones.getPrestamoByUsuarioAndLibro(prestamosRegistrados, usuarioDevuelve, libroDevuelto);
                                Devolucion devolucion = new Devolucion(prestamo);
                                devolucion.ingresarDevolucion();
                                System.out.println("Libro devuelto");
                            } else {
                                System.out.println("El usuario no tiene arrendado el libro " + isbnDevolver);
                            }
                        } else {
                            System.out.println("No existe el usuario");
                        }
                    } else {
                        System.out.println("No existe el libro");
                    }
                    break;

                case 8:
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                    break;
                default:
                    System.out.println(opcion + " No es una opción valida.");
            }
        } while (opcion != 8);
    }
}
