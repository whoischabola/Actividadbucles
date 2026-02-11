import java.util.ArrayList;
import java.util.Objects;

public class Concierto {
    private String artista;
    private String ciudad;
    private double precioBase;
    private int aforoMaximo;
    private ArrayList<Entrada> entradasVendidas;
    private boolean activo;

    // Constructor vacio
    public Concierto() {
    }

    // Constructor completo
    public Concierto(String artista, String ciudad, double precioBase, int aforoMaximo, ArrayList<Entrada> entradas, boolean activo) {
        this.artista = artista;
        this.ciudad = ciudad;
        this.precioBase = precioBase;
        this.aforoMaximo = aforoMaximo;
        this.entradasVendidas = entradas;
        this.activo = activo;
    }

    // Getters y Setters
    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public int getAforoMaximo() {
        return aforoMaximo;
    }

    public void setAforoMaximo(int aforoMaximo) {
        this.aforoMaximo = aforoMaximo;
    }

    public ArrayList<Entrada> getEntradas() {
        return entradasVendidas;
    }

    public void setEntradas(ArrayList<Entrada> entradas) {
        this.entradasVendidas = entradas;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Concierto concierto = (Concierto) o;
        return Double.compare(precioBase, concierto.precioBase) == 0 && aforoMaximo == concierto.aforoMaximo && activo == concierto.activo && Objects.equals(artista, concierto.artista) && Objects.equals(ciudad, concierto.ciudad) && Objects.equals(entradasVendidas, concierto.entradasVendidas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artista, ciudad, precioBase, aforoMaximo, entradasVendidas, activo);
    }

    public double calcularRecaudacion() {
        double total = 0;
        for (Entrada e : entradasVendidas) {
            total += e.getPrecioTotal(); // Llama al método de la clase Entrada
        }
        return total;
    }
    public double calcularPrecioMedio() {
        // 1. Verificamos si la lista está vacía para evitar dividir por cero
        // Si no te detecta .isEmpty(), usa .size() == 0
        if (entradasVendidas.size() == 0) {
            return 0.0;
        }

        // 2. Obtenemos el dinero total (que ya contempla los distintos precios)
        double recaudacionTotal = calcularRecaudacion();

        // 3. Dividimos entre el número de entradas vendidas
        return recaudacionTotal / entradasVendidas.size();
    }


        public boolean entradasDisponibles() {
        // Si el número de entradas en la lista es menor al tope permitido...
        if (this.entradasVendidas.size() < this.aforoMaximo) {
            return true;  // ¡Hay sitio!
        } else {
            return false; // Está lleno.
        }
    }
    @Override
    public String toString() {
        return "Concierto de " + artista + " en " + ciudad;
    }

        }








