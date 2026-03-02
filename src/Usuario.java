import Exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Usuario {
    private String nombre;
    private int edad;
    public ArrayList<Entrada> entradasCompradas;
    public HashSet<Concierto> conciertosAsistidos;
    public HashMap<Concierto, Integer> valoraciones;

    // Constructor vacío

    public Usuario() {
    }
    // Constructor completo

    public Usuario(String nombre, int edad, ArrayList<Entrada> entradasCompradas, HashSet<Concierto> conciertosAsistidos, HashMap<Concierto, Integer> valoraciones) {
        this.nombre = nombre;
        this.edad = edad;
        this.entradasCompradas = entradasCompradas;
        this.conciertosAsistidos = conciertosAsistidos;
        this.valoraciones = valoraciones;
    }
    // Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ArrayList<Entrada> getEntradasCompradas() {
        return entradasCompradas;
    }

    public void setEntradasCompradas(ArrayList<Entrada> entradasCompradas) {
        this.entradasCompradas = entradasCompradas;
    }

    public HashSet<Concierto> getConciertosAsistidos() {
        return conciertosAsistidos;
    }

    public void setConciertosAsistidos(HashSet<Concierto> conciertosAsistidos) {
        this.conciertosAsistidos = conciertosAsistidos;
    }

    public HashMap<Concierto, Integer> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(HashMap<Concierto, Integer> valoraciones) {
        this.valoraciones = valoraciones;
    }
    /**
     * Gestiona la compra de una entrada realizando todas las validaciones previas.
     */
    public void comprarEntrada(Concierto concierto, Entrada.TipoEntrada tipo)
    throws ConciertoInactivoException, ConciertoYaAsistidoException, AforoCompletoException {
        // 1. Comprobar si el concierto está activo
        if (!concierto.isActivo()) {
            throw new ConciertoInactivoException("Error: El concierto de " + concierto.getArtista() + "no está activo");
        }

        // 2. Comprobar si el usuario ya ha asistido
        if (this.conciertosAsistidos.contains(concierto)) {
            throw new ConciertoYaAsistidoException("Error: Ya has asistido al cocnierto de " + concierto.getArtista() + ".");
        }

        // 3. Comprobar si hay entradas disponibles
        if (!concierto.entradasDisponibles()) {
            throw new AforoCompletoException("Error: No quedan entradas para el concierto de " + concierto.getArtista() + ".");
        }

        // 4. Crear el objeto Entrada
        Entrada nuevaEntrada = new Entrada(concierto, tipo);

        // 5. Añadir a la lista de ventas del concierto
        concierto.getEntradas().add(nuevaEntrada);

        // 6. Añadir a las entradas compradas del usuario
        this.entradasCompradas.add(nuevaEntrada);

        // 7. Añadir el concierto a la lista de asistidos (HashSet)
        this.conciertosAsistidos.add(concierto);

        System.out.println("¡Compra realizada con éxito para " + concierto.getArtista() + "!");
    }

    /**
     * Registra o modifica una valoración para un concierto asistido.
     */
    public void valorar(Concierto concierto, int valoracion)
    throws ConciertoNoAsistidoException, ValoracionIncorrectaException {
        // 1. Comprobar si ha asistido al concierto
        if (!this.conciertosAsistidos.contains(concierto)) {
            throw new ConciertoNoAsistidoException("Error: No puedes valorar un concierto al que no has asistido.");
        }

        // 2. Comprobar rango de valoración (0-10)
        if (valoracion < 0 || valoracion > 10) {
            throw new ValoracionIncorrectaException("Valoracion incorrecta");
        }

        // 3. Añadir o modificar en el HashMap
        this.valoraciones.put(concierto, valoracion);
        System.out.println("Valoración guardada: " + valoracion + " puntos para " + concierto.getArtista());
    }

    // toString
    @Override
    public String toString() {
        return nombre + " (ha asistido a " + conciertosAsistidos.size() + " conciertos asistidos)";
    }
}
