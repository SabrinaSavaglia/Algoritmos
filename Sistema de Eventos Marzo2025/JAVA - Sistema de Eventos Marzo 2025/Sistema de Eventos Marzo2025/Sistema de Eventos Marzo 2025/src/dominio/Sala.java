package dominio;

import java.time.LocalDate;
import tads.NodoLista;


public class Sala implements Comparable<Sala> {

    private String nombre;
    private int capacidad;
    private NodoLista<Evento> eventos;

    public Sala(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.eventos = null;
    }

    public Sala(String nombre) {
        this.nombre = nombre;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public NodoLista<Evento> getEventos() {
        return eventos;
    }

    @Override
    public boolean equals(Object obj) {
        Sala s = (Sala) obj;
        return s.nombre.equals(this.nombre);
    }

    @Override
    public String toString() {
        return this.nombre + "-" + this.capacidad + "#";
    }

    @Override
    public int compareTo(Sala o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

      public void agregarEvento(Evento evento) {
        // Usamos el constructor de NodoLista que recibe el evento y el siguiente nodo (eventos)
        NodoLista<Evento> nuevoEvento = new NodoLista<>(evento, eventos); // eventos es el inicio de la lista
        eventos = nuevoEvento; // Actualizamos el inicio de la lista para que apunte al nuevo nodo
    }

    // Método para comprobar si la sala está disponible en una fecha específica
    public boolean estaDisponible(LocalDate fecha) {
        if (eventos == null || eventos.getInicio() == null) {
            return true; // No hay eventos, por lo tanto está disponible
        }

        NodoLista<Evento> actual = eventos.getInicio();
        while (actual != null) {
            if (actual.getDato().getFecha().equals(fecha)) {
                return false; // Ya hay un evento en esa fecha
            }
            actual = actual.getSiguiente();
        }
        return true; // No hay eventos en esa fecha, está disponible
    }

    public static String esSalaOptima(String[][] vistaSala) {
        int columnas = vistaSala[0].length;
        int filas = vistaSala.length;
        int columnasOptimas = 0;

        for (int col = 0; col < columnas; col++) {
            int maxOcupadosConsecutivos = 0;
            int ocupadosConsecutivosActual = 0;
            int libres = 0;

            for (int fila = 0; fila < filas; fila++) {
                String valor = vistaSala[fila][col];

                if (valor.equals("O")) {
                    ocupadosConsecutivosActual++;
                    maxOcupadosConsecutivos = Math.max(maxOcupadosConsecutivos, ocupadosConsecutivosActual);
                } else {
                    ocupadosConsecutivosActual = 0;
                    if (valor.equals("X")) {
                        libres++;
                    }
                }
            }

            if (maxOcupadosConsecutivos > libres) {
                columnasOptimas++;
            }
        }

        return columnasOptimas >= 2 ? "Es óptimo" : "No es óptimo";
    }
}
