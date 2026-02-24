package modelo;

public class Cuenta {
    private int id;
    private int idUsuario;
    private double saldo;

    public Cuenta() {}

    // Constructor usado en tests y simulaciones
    public Cuenta(int id, int idUsuario, double saldo) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.saldo = saldo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdUsuario() { return idUsuario; } // Usado en la GUI (cliente)
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
}
