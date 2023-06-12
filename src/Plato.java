public class Plato {
    private String nombre;
    private double precio;
    private int calorias;
    private int tiempoPreparacion;

    public Plato(String nombre, double precio, int calorias, int tiempoPreparacion) {
        this.nombre = nombre;
        this.precio = precio;
        this.calorias = calorias;
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCalorias() {
        return calorias;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                ", Precio: " + precio +
                ", Calorias: " + calorias +
                ", Tiempo de preparacion: " + tiempoPreparacion;
    }
}
