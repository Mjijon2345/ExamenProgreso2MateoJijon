import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Menu {
    private ArrayList<Plato> platos;

    public Menu() {
        platos = new ArrayList<>();
    }

    public ArrayList<Plato> getPlatos() {
        return platos;
    }

    public void agregarPlato(Plato plato) {
        platos.add(plato);
    }

    public boolean modificarPlato(String nombrePlato, Plato platoModificado) {
        for (int i = 0; i < platos.size(); i++) {
            Plato plato = platos.get(i);
            if (plato.getNombre().equalsIgnoreCase(nombrePlato)) {
                platos.set(i, platoModificado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarPlato(String nombrePlato) {
        for (int i = 0; i < platos.size(); i++) {
            Plato plato = platos.get(i);
            if (plato.getNombre().equalsIgnoreCase(nombrePlato)) {
                platos.remove(i);
                return true;
            }
        }
        return false;
    }

    public Plato buscarPlato(String nombrePlato) {
        for (Plato plato : platos) {
            if (plato.getNombre().equalsIgnoreCase(nombrePlato)) {
                return plato;
            }
        }
        return null;
    }

    public void quemarDatosPredeterminados() {
        platos.clear();

        // Agregar platos por defecto
        platos.add(new Plato("Hamburguesa", 10.99, 500, 15));
        platos.add(new Plato("Pizza", 8.99, 800, 20));
        platos.add(new Plato("Ensalada", 6.99, 200, 10));
        platos.add(new Plato("Sopa", 5.99, 300, 12));
        platos.add(new Plato("Pasta", 9.99, 600, 18));
        platos.add(new Plato("Encebollado", 3.75, 283, 50));
    }

    public void ordenarPorNombre() {
        Collections.sort(platos, new NombreComparator());
    }

    public void ordenarPorPrecio() {
        Collections.sort(platos, new PrecioComparator());
    }

    public void ordenarPorCalorias() {
        Collections.sort(platos, new CaloriasComparator());
    }

    public void ordenarPorTiempoPreparacion() {
        Collections.sort(platos, new TiempoPreparacionComparator());
    }

    public int buscarPlatoOrden(String nombrePlato) {
        ordenarPorNombre();
        return Collections.binarySearch(platos, new Plato(nombrePlato, 0, 0, 0), new NombreComparator());
    }

    // Clase interna para comparar platos por nombre
    private static class NombreComparator implements Comparator<Plato> {
        @Override
        public int compare(Plato p1, Plato p2) {
            return p1.getNombre().compareToIgnoreCase(p2.getNombre());
        }
    }

    // Clase interna para comparar platos por precio
    private static class PrecioComparator implements Comparator<Plato> {
        @Override
        public int compare(Plato p1, Plato p2) {
            return Double.compare(p1.getPrecio(), p2.getPrecio());
        }
    }

    // Clase interna para comparar platos por calorías
    private static class CaloriasComparator implements Comparator<Plato> {
        @Override
        public int compare(Plato p1, Plato p2) {
            return Integer.compare(p1.getCalorias(), p2.getCalorias());
        }
    }

    // Clase interna para comparar platos por tiempo de preparación
    private static class TiempoPreparacionComparator implements Comparator<Plato> {
        @Override
        public int compare(Plato p1, Plato p2) {
            return Integer.compare(p1.getTiempoPreparacion(), p2.getTiempoPreparacion());
        }
    }
}
