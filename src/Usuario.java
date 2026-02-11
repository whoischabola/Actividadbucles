import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Usuario {
    private String nombre;
    private int edad;
    private ArrayList<Entrada> entradasCompradas;
    private HashSet<Concierto> conciertosAsistidos;
    private HashMap<Concierto, Integer> valoraciones;

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
    public void comprarEntrada(Concierto concierto, Entrada.TipoEntrada tipo) {
        // 1. Comprobar si el concierto está activo
        if (!concierto.isActivo()) {
            System.out.println("Error: El concierto no está activo.");
            return; // Detiene la ejecución
        }

        // 2. Comprobar si el usuario ya ha asistido (está en su HashSet)
        if (this.conciertosAsistidos.contains(concierto)) {
            System.out.println("Error: Ya has asistido a este concierto anteriormente.");
            return;
        }

        // 3. Comprobar si hay entradas disponibles
        if (!concierto.entradasDisponibles()) {
            System.out.println("Error: No quedan entradas disponibles para este concierto.");
            return;
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
    public void valorar(Concierto concierto, int valoracion) {
        // 1. Comprobar si ha asistido al concierto
        if (!this.conciertosAsistidos.contains(concierto)) {
            System.out.println("Error: No puedes valorar un concierto al que no has asistido.");
            return;
        }

        // 2. Comprobar rango de valoración (0-10)
        if (valoracion < 0 || valoracion > 10) {
            System.out.println("Error: La valoración debe estar entre 0 y 10.");
            return;
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
