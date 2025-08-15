/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

import dominio.Sala;
import java.time.LocalDate;

public class ListaNodos<T extends Comparable> implements IListaSimple<T> {

    private NodoLista inicio;
    private int cantElementos;

    public ListaNodos() {
        this.inicio = null;
        cantElementos = 0;
    }

    public boolean esVacia() {
        return inicio == null;
    }

    @Override
    public void agregarInicio(T x) {
        if (cantElementos == 0) { // Es el primer nodo
            NodoLista nuevo = new NodoLista(x);
            nuevo.setSiguiente(null);
            inicio = nuevo;

        } else {
            NodoLista nuevo = new NodoLista(x);
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        }
        cantElementos++;
    }

    @Override
    public boolean existeElemento(T x) {
        boolean existe = false;
        NodoLista<T> aux = inicio;

        while (aux != null && !existe) {
            if (aux.getDato().equals(x)) {
                existe = true;
            }
            aux = aux.getSiguiente();
        }
        return existe;
    }

    @Override
    public boolean eliminar(T x) {
        boolean existe = false;
        NodoLista<T> aux = inicio;

        while (aux != null && !existe) {
            if (aux.getDato().equals(x)) {
                existe = true;
                NodoLista<T> sig = aux.getSiguiente();
                if (sig != null) {

                    aux.setDato(sig.getDato());

                    aux.setSiguiente(sig.getSiguiente());
                }

            }
            aux = aux.getSiguiente();
        }
        return existe;
    }

    @Override
    public void mostrar() {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            System.out.print(aux.getDato());
            aux = aux.getSiguiente();
        }
    }

    @Override
    public void agregarOrdenado(T x) {
        if (esVacia() || x.compareTo(inicio.getDato()) < 0) {
            agregarInicio(x);
        }
    }

   public Sala buscarSalaDisponible(LocalDate fecha, int aforoNecesario) {
    NodoLista<T> actual = this.inicio;

    if (actual == null) {
        System.out.println("La lista de salas está vacía.");
    }

    while (actual != null) {
        T dato = actual.getDato();
        if (dato instanceof Sala) {
            Sala sala = (Sala) dato;

            if (sala.getCapacidad() >= aforoNecesario && sala.estaDisponible(fecha)) {
                return sala;
            }
        }
        actual = actual.getSiguiente();
    }

    return null;
}
    
}
