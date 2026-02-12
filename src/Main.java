//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList; //Import

 public class Main {
     public static void main(String[] args) {

         // --- 1. CREACIÓN DE CONCIERTOS ---
         Concierto c1 = new Concierto("Coldplay", "Madrid", 100.0, 50000, new ArrayList<>(), true);
         Concierto c2 = new Concierto("Estopa", "Barcelona", 50.0, 20000, new ArrayList<>(), true);
         Concierto c3 = new Concierto("Taylor Swift", "Lisboa", 150.0, 60000, new ArrayList<>(), false); // Este está inactivo para probar

         // --- 2. CREACIÓN DE USUARIOS ---
         Usuario u1 = new Usuario();
         u1.setNombre("Ana");
         u1.setEdad(25);

         Usuario u2 = new Usuario();
         u2.setNombre("Carlos");
         u2.setEdad(30);

         Usuario u3 = new Usuario();
         u3.setNombre("Lucía");
         u3.setEdad(22);

         // --- 3. COMPRA DE ENTRADAS (2 por usuario) ---
         System.out.println("--- PROCESO DE COMPRA ---");

         // Ana compra para Coldplay (Pista) y Estopa (Grada)
         u1.comprarEntrada(c1, Entrada.TipoEntrada.Pista);
         u1.comprarEntrada(c2, Entrada.TipoEntrada.Grada);

         // Carlos intenta comprar para Taylor Swift (Dará ERROR porque está inactivo)
         u2.comprarEntrada(c3, Entrada.TipoEntrada.VIP);
         // Carlos compra para Coldplay (VIP) y Estopa (Pista)
         u2.comprarEntrada(c1, Entrada.TipoEntrada.VIP);
         u2.comprarEntrada(c2, Entrada.TipoEntrada.Pista);

         // Lucía compra para Coldplay y intenta comprar OTRA para Coldplay (Dará ERROR por el HashSet)
         u3.comprarEntrada(c1, Entrada.TipoEntrada.Grada);
         u3.comprarEntrada(c1, Entrada.TipoEntrada.Pista); // Error: Ya ha asistido según el HashSet

         System.out.println("\n--- PROCESO DE VALORACIÓN ---");

         // --- 4. VALORACIONES (1 por usuario) ---
         u1.valorar(c1, 9);
         u2.valorar(c2, 11);
         u2.valorar(c2, 8);
         u3.valorar(c3, 5);

         // --- 5. COMPROBACIÓN FINAL ---
         System.out.println("--- RESUMEN FINAL ---");
         System.out.println(u1.toString());
         System.out.println(u2.toString());
         System.out.println(u3.toString());

         // Comprobamos la recaudación de un concierto para ver si los recargos funcionan
         // Coldplay (c1): 1 Pista (110€) + 1 VIP (120€) + 1 Grada (100€) = 330€
         System.out.println("Recaudación total de " + c1.getArtista() + ": " + c1.calcularRecaudacion() + " €");
     }
 }




