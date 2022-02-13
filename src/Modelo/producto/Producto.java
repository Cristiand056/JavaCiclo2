package Modelo.producto;

public class Producto {
    private int id;
    private String nombre;
    private int precio;
    private String descripcion;
    private String fecha_vemcimiento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha_vemcimiento() {
        return fecha_vemcimiento;
    }

    public void setFecha_vemcimiento(String fecha_vemcimiento) {
        this.fecha_vemcimiento = fecha_vemcimiento;
    }
    
    
}
