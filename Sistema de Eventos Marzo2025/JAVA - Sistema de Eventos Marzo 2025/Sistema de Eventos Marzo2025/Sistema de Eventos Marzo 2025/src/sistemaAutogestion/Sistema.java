package sistemaAutogestion;

import dominio.Cliente;
import dominio.Evento;
import dominio.Sala;
import java.time.LocalDate;
import tads.ListaNodos;

public class Sistema implements IObligatorio {

    private ListaNodos<Sala> lista;
    private ListaNodos<Cliente> listaClientes;
    private ListaNodos<Evento> listaEventos;

    @Override
    public Retorno crearSistemaDeGestion() {
        lista = new ListaNodos();
        listaClientes = new ListaNodos();
        listaEventos = new ListaNodos();

        return Retorno.ok();

    }

    @Override
    public Retorno esVacia() {
        if (lista == null);
        return Retorno.ok();
    }

    @Override
    public Retorno Vaciar() {
        lista = null;
        return Retorno.ok();
    }

    @Override
    public Retorno registrarSala(String nombre, int capacidad) {
        Sala s1 = new Sala(nombre, capacidad);
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        // capacidad
        if (capacidad <= 0) {
            r.resultado = Retorno.Resultado.ERROR_2;
        } else if (lista.existeElemento(s1)) {
            r.resultado = Retorno.Resultado.ERROR_1;
        } else {
            lista.agregarInicio(s1);
            r.resultado = Retorno.Resultado.OK;
        }

        return r;
    }
    
 
    @Override
    public Retorno eliminarSala(String nombre) {
        Sala s1 = new Sala(nombre);
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        if (lista.eliminar(s1)) {
            r.resultado = Retorno.Resultado.OK;
        } else {
            r.resultado = Retorno.Resultado.ERROR_1;
        }

        return r;
    }

    @Override
    public Retorno registrarCliente(String cedula, String nombre) {

        Cliente c1 = new Cliente(cedula, nombre);
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        // cedula
        if (cedula == null || cedula.length() != 8) {
            r.resultado = Retorno.Resultado.ERROR_1;
        } else if (listaClientes.existeElemento(c1)) {
            r.resultado = Retorno.Resultado.ERROR_2;
        } else {
            listaClientes.agregarOrdenado(c1);//ACA MODIFICO POR AGREGAR ORDENADO
            r.resultado = Retorno.Resultado.OK;
        }
        return r;

    }

    @Override
    public Retorno registrarEvento(String codigo, String descripcion, int aforoNecesario, LocalDate fecha) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        // Verificar aforo
        if (aforoNecesario <= 0) {
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        }

        // Buscar una sala disponible
        Sala salaDisponible = lista.buscarSalaDisponible(fecha, aforoNecesario);
        if (salaDisponible == null) {
            r.resultado = Retorno.Resultado.ERROR_3;
            return r;
        }

        // Verificar si el evento ya existe
        Evento evento = new Evento(codigo, descripcion, aforoNecesario, fecha, salaDisponible);
        if (listaEventos.existeElemento(evento)) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }

        //agregar evento
        listaEventos.agregarOrdenado(evento);
        salaDisponible.agregarEvento(evento);
        System.out.println("Evento registrado en: " + salaDisponible.getNombre() + " en fecha " + fecha);
        System.out.println("¿Está disponible en esa fecha luego de registrar?: " + salaDisponible.estaDisponible(fecha));

        r.resultado = Retorno.Resultado.OK;

        return r;
    }

    @Override
    public Retorno listarSalas() {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        lista.mostrar();
        r.resultado = Retorno.Resultado.OK;

        return r;
    }

    @Override
    public Retorno listarEventos() {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        listaEventos.mostrar();
        r.resultado = Retorno.Resultado.OK;

        return r;
    }

    @Override
    public Retorno listarClientes() {

        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        listaClientes.mostrar();
        r.resultado = Retorno.Resultado.OK;

        return r;
    }

    @Override
    public Retorno esSalaOptima(String[][] vistaSala) {
        try {
            String resultado = Sala.esSalaOptima(vistaSala);
            return new Retorno(Retorno.Resultado.OK, resultado);
        } catch (Exception e) {
            return new Retorno(Retorno.Resultado.ERROR_1, "Error al evaluar la sala");
        }
    }

    @Override
    public Retorno comprarEntrada(String cedula, String codigoEvento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno eliminarEvento(String codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno devolverEntrada(String cedula, String codigoEvento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno calificarEvento(String cedula, String codigoEvento, int puntaje, String comentario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno listarClientesDeEvento(String código, int n) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno listarEsperaEvento() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno deshacerUtimasCompras(int n) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno eventoMejorPuntuado() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno comprasDeCliente(String cedula) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno comprasXDia(int mes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
