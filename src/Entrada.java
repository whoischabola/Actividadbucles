import java.util.Objects;

public class Entrada {
    public enum TipoEntrada {Pista, Grada, VIP}

    private Concierto concierto;
    private TipoEntrada tipo;

    // Constructor vacío
    public Entrada() {
    }

    // Constructor completo
    public Entrada(Concierto concierto, TipoEntrada tipo) {
        this.concierto = concierto;
        this.tipo = tipo;
    }
    // Getters y Setters

    public Concierto getConcierto() {
        return concierto;
    }

    public void setConcierto(Concierto concierto) {
        this.concierto = concierto;
    }

    public TipoEntrada getTipo() {
        return tipo;
    }

    public void setTipo(TipoEntrada tipo) {
        this.tipo = tipo;
    }
    // Equals y HashCode

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Entrada entrada = (Entrada) o;
        return Objects.equals(concierto, entrada.concierto) && tipo == entrada.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(concierto, tipo);
    } // Métodos: Obtener el precio total de cada entrada en base al tipo
    // y con el recargo añadido correspondiente en cada caso.

    public double getPrecioTotal() {
        if (concierto == null) return 0.0;
        double precioBase = concierto.getPrecioBase(); // Llamamos al atributo de la clase concierto necesario para esta operación.
        switch (tipo) {
            case Pista:
                // Precio base + 10%
                return precioBase * 1.10;
            case VIP:
                // Precio base + 20%
                return precioBase * 1.20;
            default:
                // Precio base sin recargos
                return precioBase;

        }


    } // toString para finalizar.

    @Override
    public String toString() {
        return "Entrada de " + getPrecioTotal() + " €";
    }
}
