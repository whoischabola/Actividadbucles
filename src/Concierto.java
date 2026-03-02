import Exceptions.CeroEntradasException;

import java.util.ArrayList;
import java.util.Objects;

public class Concierto {
    private String artista;
    private String ciudad;
    private double precioBase;
    public int aforoMaximo;
    public ArrayList<Entrada> entradasVendidas;
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
        if (this == o) return true; // Buena práctica
        if (o == null || getClass() != o.getClass()) return false;
        Concierto concierto = (Concierto) o;
        // Quitamos la comparación de 'entradasVendidas'
        return Double.compare(precioBase, concierto.precioBase) == 0 &&
                aforoMaximo == concierto.aforoMaximo &&
                activo == concierto.activo &&
                Objects.equals(artista, concierto.artista) &&
                Objects.equals(ciudad, concierto.ciudad);
    }

    @Override
    public int hashCode() {
        // Quitamos 'entradasVendidas' de aquí
        return Objects.hash(artista, ciudad, precioBase, aforoMaximo, activo);
    }


    public double calcularRecaudacion() {
        double total = 0;
        for (Entrada e : this.entradasVendidas()) {
            total += e.getPrecioTotal(); // Llama al método de la clase Entrada
        }
        return total;
    }

    private Entrada[] entradasVendidas() {
        return null;
    }

    public double calcularPrecioMedio() {
        // 1. Verificamos si la lista está vacía para evitar dividir por cero
        if (entradasVendidas.size() == 0) {
            throw new CeroEntradasException("Error: el cocnierto no tiene entradas disponibles");

        }
        double recaudacionTotal = calcularRecaudacion(); // Obtenemos el dinero total recaudado.
        return recaudacionTotal / entradasVendidas.size(); // Lo dividimos entre las entradas vendidas.


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










