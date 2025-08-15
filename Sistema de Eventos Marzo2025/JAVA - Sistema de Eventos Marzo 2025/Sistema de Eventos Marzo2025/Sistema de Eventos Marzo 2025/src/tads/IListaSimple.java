/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

public interface IListaSimple<T> {
    
    public void agregarInicio(T x);
    public boolean existeElemento(T x);
    boolean eliminar(T x);
    public void mostrar();
    public void agregarOrdenado(T x);
    public boolean esVacia();
  
    
    
}
