package modelo;

import java.sql.Timestamp;

public class Transaccion {
    private int id;
    private int idCuenta;
    private String tipo; // deposito, retiro, transferencia
    private double monto;
    private Timestamp fecha;
    private String detalle;

    public Transaccion() {}

    public Transaccion(int idCuenta, String tipo, double monto, String detalle) {
        this.idCuenta = idCuenta;
        this.tipo = tipo;
        this.monto = monto;
        this.detalle = detalle;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdCuenta() { return idCuenta; }
    public void setIdCuenta(int idCuenta) { this.idCuenta = idCuenta; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public Timestamp getFecha() { return fecha; }
    public void setFecha(Timestamp fecha) { this.fecha = fecha; }

    public String getDetalle() { return detalle; }
    public void setDetalle(String detalle) { this.detalle = detalle; }
}