import Exceptions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    static void main(String[] args) {

        // --- 1. CREACIÓN DE CONCIERTOS ---
        Concierto c1 = new Concierto("Coldplay", "Madrid", 100.0, 50000, new ArrayList<>(), true);
        Concierto c2 = new Concierto("Estopa", "Barcelona", 50.0, 20000, new ArrayList<>(), true);
        Concierto c3 = new Concierto("Taylor Swift", "Lisboa", 150.0, 60000, new ArrayList<>(), false);

        // --- 2. CREACIÓN DE USUARIOS ---
        Usuario u1 = new Usuario("Ana", 25, new ArrayList<>(), new HashSet<>(), new HashMap<>());
        Usuario u2 = new Usuario("Carlos", 30, new ArrayList<>(), new HashSet<>(), new HashMap<>());
        Usuario u3 = new Usuario("Lucia", 22, new ArrayList<>(), new HashSet<>(), new HashMap<>());

        // --- 3. COMPRA DE ENTRADAS ---
        System.out.println("--- PROCESO DE COMPRA ---");

        try { u1.comprarEntrada(c1, Entrada.TipoEntrada.Pista); }
        catch (ConciertoInactivoException | ConciertoYaAsistidoException | AforoCompletoException e) { System.out.println(e.getMessage()); }

        try { u1.comprarEntrada(c2, Entrada.TipoEntrada.Grada); }
        catch (ConciertoInactivoException | ConciertoYaAsistidoException | AforoCompletoException e) { System.out.println(e.getMessage()); }

        try { u2.comprarEntrada(c3, Entrada.TipoEntrada.VIP); } // ConciertoInactivoException
        catch (ConciertoInactivoException | ConciertoYaAsistidoException | AforoCompletoException e) { System.out.println(e.getMessage()); }

        try { u2.comprarEntrada(c1, Entrada.TipoEntrada.VIP); }
        catch (ConciertoInactivoException | ConciertoYaAsistidoException | AforoCompletoException e) { System.out.println(e.getMessage()); }

        try { u2.comprarEntrada(c2, Entrada.TipoEntrada.Pista); }
        catch (ConciertoInactivoException | ConciertoYaAsistidoException | AforoCompletoException e) { System.out.println(e.getMessage()); }

        try { u3.comprarEntrada(c1, Entrada.TipoEntrada.Grada); }
        catch (ConciertoInactivoException | ConciertoYaAsistidoException | AforoCompletoException e) { System.out.println(e.getMessage()); }

        try { u3.comprarEntrada(c1, Entrada.TipoEntrada.Pista); } // ConciertoYaAsistidoException
        catch (ConciertoInactivoException | ConciertoYaAsistidoException | AforoCompletoException e) { System.out.println(e.getMessage()); }

        // --- 4. PRECIO MEDIO ---
        try {
            System.out.println("Precio medio c1: " + c1.calcularPrecioMedio() + "€");
            System.out.println("Precio medio c3: " + c3.calcularPrecioMedio() + "€"); // CeroEntradasException
        } catch (CeroEntradasException e) {
            System.out.println(e.getMessage());
        }

        // --- 5. VALORACIONES ---
        System.out.println("\n--- PROCESO DE VALORACIÓN ---");

        try { u1.valorar(c1, 9); }
        catch (ConciertoNoAsistidoException | ValoracionIncorrectaException e) { System.out.println(e.getMessage()); }

        try { u2.valorar(c2, 11); } // ValoracionIncorrectaException
        catch (ConciertoNoAsistidoException | ValoracionIncorrectaException e) { System.out.println(e.getMessage()); }

        try { u2.valorar(c2, 8); }
        catch (ConciertoNoAsistidoException | ValoracionIncorrectaException e) { System.out.println(e.getMessage()); }

        try { u3.valorar(c3, 5); } // ConciertoNoAsistidoException
        catch (ConciertoNoAsistidoException | ValoracionIncorrectaException e) { System.out.println(e.getMessage()); }

        // --- 6. RESUMEN FINAL ---
        System.out.println("\n--- RESUMEN FINAL ---");
        System.out.println(u1.toString());
        System.out.println(u2.toString());
        System.out.println(u3.toString());

        System.out.println("Recaudación total de " + c1.getArtista() + ": " + c1.calcularRecaudacion() + " €");
    }
}