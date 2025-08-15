/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sistemaAutogestion;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Assert;

public class IObligatorioTest {

    private Sistema miSistema;

    public IObligatorioTest() {

    }

    @Before
    public void setUp() {
        miSistema = new Sistema();
        miSistema.crearSistemaDeGestion();
    }

    @Test
    public void testCrearSistemaDeGestion() {

        Retorno retorno = miSistema.crearSistemaDeGestion();
        Assert.assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    }

    @Test
    public void testRegistrarSala() {
        //OK
        Retorno resultado1 = miSistema.registrarSala("Sala 1", 50);
        assertEquals(Retorno.Resultado.OK, resultado1.getResultado());

        //Nombre duplicado
        Retorno resultado4 = miSistema.registrarSala("Sala 1", 35);
        assertEquals(Retorno.Resultado.ERROR_1, resultado4.getResultado());

        //Capacidad <= 0
        Retorno resultado2 = miSistema.registrarSala("Sala 2", 0);
        assertEquals(Retorno.Resultado.ERROR_2, resultado2.getResultado());

        //Capacidad negativa
        Retorno resultado3 = miSistema.registrarSala("Sala 3", -1);
        assertEquals(Retorno.Resultado.ERROR_2, resultado3.getResultado());

    }

    @Test
    public void testEliminarSala() {

        //Agrego Sala antes de eliminar
        Retorno resultadoRegistro = miSistema.registrarSala("Sala 1", 50);
        assertEquals(Retorno.Resultado.OK, resultadoRegistro.getResultado());

        //Elimino Sala que existe
        Retorno eliminarSala1 = miSistema.eliminarSala("Sala 1");
        assertEquals(Retorno.Resultado.OK, eliminarSala1.getResultado());

        // No elimina sala que no existe
        Retorno eliminarSala2 = miSistema.eliminarSala("Sala 2");
        assertEquals(Retorno.Resultado.ERROR_1, eliminarSala2.getResultado());

    }

    @Test
    public void testRegistrarEvento() {
        // Registrar salas con distinta capacidad
        miSistema.registrarSala("Sala A", 100);
        miSistema.registrarSala("Sala B", 50);

        // Registrar evento válido
        Retorno resultado1 = miSistema.registrarEvento("EV001", "Conferencia de Java", 50, LocalDate.of(2025, 5, 15));
        assertEquals(Retorno.Resultado.OK, resultado1.getResultado());
        
        // Intentar registrar un evento en una fecha sin salas disponibles (todas ocupadas o sin capacidad)
        miSistema.registrarEvento("EV003", "Reunión", 100, LocalDate.of(2025, 5, 15)); // ya se usó Sala A en esa fecha
        Retorno resultado4 = miSistema.registrarEvento("EV004", "Evento sin sala", 100, LocalDate.of(2025, 5, 2));
        assertEquals(Retorno.Resultado.ERROR_3, resultado4.getResultado());

        // Intentar registrar un evento con el mismo código
        Retorno resultado2 = miSistema.registrarEvento("EV001", "Curso de Power BI", 30, LocalDate.of(2025, 5, 13));
        assertEquals(Retorno.Resultado.ERROR_1, resultado2.getResultado());

        // Intentar registrar un evento con aforo <= 0
        Retorno resultado3 = miSistema.registrarEvento("EV002", "Taller de Python", -5, LocalDate.of(2025, 6, 20));
        assertEquals(Retorno.Resultado.ERROR_2, resultado3.getResultado());

        
    }

    @Test
    public void testRegistrarCliente() {
        // Cliente válido
        Retorno resultado1 = miSistema.registrarCliente("12345678", "Ana García");
        assertEquals(Retorno.Resultado.OK, resultado1.getResultado());

        // Cliente con cédula inválida (menos de 8 dígitos)
        Retorno resultado2 = miSistema.registrarCliente("1234567", "Luis Pérez");
        assertEquals(Retorno.Resultado.ERROR_1, resultado2.getResultado());

        // Cliente con cédula inválida (más de 8 dígitos)
        Retorno resultado3 = miSistema.registrarCliente("123456789", "Mario Gómez");
        assertEquals(Retorno.Resultado.ERROR_1, resultado3.getResultado());

        // Cliente duplicado (misma cédula que el primero)
        Retorno resultado5 = miSistema.registrarCliente("12345678", "Carlos Ríos");
        assertEquals(Retorno.Resultado.ERROR_2, resultado5.getResultado());
    }
    
    @Test
    public void testListarSalas() {
        // Registro salas
        miSistema.registrarSala("Sala A", 10);
        miSistema.registrarSala("Sala B", 20);
        miSistema.registrarSala("Sala C", 30);
        Retorno resultado3 = miSistema.listarSalas();
        assertEquals(Retorno.Resultado.OK, resultado3.getResultado());

    }

    @Test
    public void testListarEventos() {
        // Registrar varias salas
        miSistema.registrarSala("Sala A", 100);
        miSistema.registrarSala("Sala B", 50);
        miSistema.registrarSala("Sala C", 30);

        // Registrar eventos en diferentes fechas o con salas disponibles
        miSistema.registrarEvento("E001", "Charla UX", 10, LocalDate.of(2025, 5, 10));  // Sala A
        miSistema.registrarEvento("K002", "Workshop", 10, LocalDate.of(2025, 5, 15));   // Sala B
        miSistema.registrarEvento("Z002", "Seminario", 10, LocalDate.of(2050, 5, 15));  // Sala C

        // Ejecutar y validar el listado
        Retorno resultado = miSistema.listarEventos();
        assertEquals(Retorno.Resultado.OK, resultado.getResultado());

    }

    @Test
    public void testListarClientes() {

        // Registro de más clientes
        miSistema.registrarCliente("12345678", "Ana García");
        miSistema.registrarCliente("98765432", "Luis Pérez");
        miSistema.registrarCliente("11223344", "Carlos Ríos");
        Retorno resultado3 = miSistema.listarClientes();
        assertEquals(Retorno.Resultado.OK, resultado3.getResultado());
    }

    @Test
    public void testEsSalaOptima() {
        // Caso óptimo
        String[][] salaOptima = {
            {"#", "#", "#", "#", "#", "#", "#"},
            {"#", "#", "X", "X", "X", "X", "#"},
            {"#", "O", "O", "X", "X", "X", "#"},
            {"#", "O", "O", "O", "O", "X", "#"},
            {"#", "O", "O", "X", "O", "O", "#"},
            {"#", "O", "O", "O", "O", "O", "#"},
            {"#", "X", "X", "O", "O", "O", "O"},
            {"#", "X", "X", "O", "O", "O", "X"},
            {"#", "X", "X", "O", "X", "X", "#"},
            {"#", "X", "X", "O", "X", "X", "#"},
            {"#", "#", "#", "O", "#", "#", "#"},
            {"#", "#", "#", "O", "#", "#", "#"}
        };
        Retorno resultado1 = miSistema.esSalaOptima(salaOptima);
        assertEquals(Retorno.Resultado.OK, resultado1.getResultado());

        // Caso no óptimo
        String[][] salaNoOptima = {
            {"X", "X", "O"},
            {"O", "X", "O"},
            {"X", "O", "X"},
            {"X", "X", "O"}
        };
        Retorno resultado2 = miSistema.esSalaOptima(salaNoOptima);
        assertEquals(Retorno.Resultado.OK, resultado2.getResultado());

        // Matriz vacía
        String[][] vacia = new String[0][0];
        Retorno resultado3 = miSistema.esSalaOptima(vacia);
        assertEquals(Retorno.Resultado.ERROR_1, resultado3.getResultado());

        // Matriz nula
        String[][] nula = null;
        Retorno resultado4 = miSistema.esSalaOptima(nula);
        assertEquals(Retorno.Resultado.ERROR_1, resultado4.getResultado());

        // Justo dos columnas óptimas
        String[][] justoDos = {
            {"O", "O", "X"},
            {"O", "O", "X"},
            {"O", "X", "O"},
            {"O", "X", "O"},
            {"O", "O", "O"},
            {"O", "O", "X"}
        };
        Retorno resultado5 = miSistema.esSalaOptima(justoDos);
        assertEquals(Retorno.Resultado.OK, resultado5.getResultado());
    }

}
