package utils;

public class TestHash {
    public static void main(String[] args) {
        String password1 = "1234";     // Marcos Morales (cliente)
        String password2 = "admin";    // Franco Schimizzi (empleado)
        String password3 = "4567";     // Lucía Pérez (cliente)
        String password4 = "admin2";   // Diego Ríos (empleado)

        System.out.println("[Test de Hash SHA-256]");
        System.out.println("Contraseña: " + password1 + " → Hash: " + Seguridad.hashSHA256(password1));
        System.out.println("Contraseña: " + password2 + " → Hash: " + Seguridad.hashSHA256(password2));
        System.out.println("Contraseña: " + password3 + " → Hash: " + Seguridad.hashSHA256(password3));
        System.out.println("Contraseña: " + password4 + " → Hash: " + Seguridad.hashSHA256(password4));
    }
}