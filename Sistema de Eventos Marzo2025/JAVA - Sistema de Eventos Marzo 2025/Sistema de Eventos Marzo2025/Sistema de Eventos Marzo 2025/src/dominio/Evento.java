package dominio;

import java.time.LocalDate;

public class Evento implements Comparable<Evento> {

    private String codigo;
    private String descripcion;
    private int aforoNecesario;
    private LocalDate fecha;
    private Sala sala; // Sala asignada al evento
     

    // Constructor para crear un evento con todos los parámetros necesarios
    public Evento(String codigo, String descripcion, int aforoNecesario, LocalDate fecha, Sala sala) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.aforoNecesario = aforoNecesario;
        this.fecha = fecha;
        this.sala = sala;
    }

    // Métodos getters y setters (si es necesario)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAforoNecesario() {
        return aforoNecesario;
    }

    public void setAforoNecesario(int aforoNecesario) {
        this.aforoNecesario = aforoNecesario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public boolean equals(Object obj) {
        Evento e = (Evento) obj;
        return e.codigo.equals(this.codigo);
    }

    @Override
    public String toString() {
        return this.codigo + "-" + this.descripcion + "-" + this.sala;
    }

    @Override
    public int compareTo(Evento e) {
        return this.codigo.trim().compareTo(e.getCodigo().trim());
    }
}
