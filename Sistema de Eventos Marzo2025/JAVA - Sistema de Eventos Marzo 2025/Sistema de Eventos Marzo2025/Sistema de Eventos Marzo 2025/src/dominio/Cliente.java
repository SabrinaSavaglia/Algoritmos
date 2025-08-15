package dominio;

public class Cliente implements Comparable<Cliente> {

    public String cedula;
    public String nombre;

    public Cliente(String cedula, String nombre) {
        this.cedula = cedula.trim();
        this.nombre = nombre.trim();
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object obj) {
        Cliente c = (Cliente) obj;
        return c.cedula.equals(this.cedula);
    }

    @Override
    public String toString() {
        return this.cedula.replace(" ", "") + "-" + this.nombre.replace(" ", "") + "#";
    }

    @Override
    public int compareTo(Cliente o) {
        return this.cedula.trim().compareTo(o.getCedula().trim());
    }

}
