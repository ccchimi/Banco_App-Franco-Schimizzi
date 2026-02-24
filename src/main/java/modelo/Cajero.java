package modelo;

public class Cajero {
    private int id;
    private double cantidadDisponible;

    public Cajero() {}

    public Cajero(int id, double cantidadDisponible) {
        this.id = id;
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getCantidadDisponible() { return cantidadDisponible; }
    public void setCantidadDisponible(double cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
}