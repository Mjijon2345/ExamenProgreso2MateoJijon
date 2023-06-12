import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Ordenamiento {
    public static void ordenarPorNombre(ArrayList<Plato> platos) {
        int n = platos.size();
        boolean intercambio;

        for (int i = 0; i < n - 1; i++) {
            intercambio = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (platos.get(j).getNombre().compareToIgnoreCase(platos.get(j + 1).getNombre()) > 0) {

                    Plato temp = platos.get(j);
                    platos.set(j, platos.get(j + 1));
                    platos.set(j + 1, temp);
                    intercambio = true;
                }
            }

            if (!intercambio) {
                break;
            }
        }
    }

    public static void ordenarPorPrecio(ArrayList<Plato> platos) {
        int n = platos.size();
        boolean intercambio;

        for (int i = 0; i < n - 1; i++) {
            intercambio = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (platos.get(j).getPrecio() > platos.get(j + 1).getPrecio()) {

                    Plato temp = platos.get(j);
                    platos.set(j, platos.get(j + 1));
                    platos.set(j + 1, temp);
                    intercambio = true;
                }
            }

            if (!intercambio) {
                break;
            }
        }
    }

    public static void ordenarPorCalorias(ArrayList<Plato> platos) {
        int n = platos.size();

        for (int i = 1; i < n; i++) {
            Plato key = platos.get(i);
            int j = i - 1;

            while (j >= 0 && platos.get(j).getCalorias() > key.getCalorias()) {
                platos.set(j + 1, platos.get(j));
                j = j - 1;
            }

            platos.set(j + 1, key);
        }
    }

    public static void ordenarPorTiempoPreparacion(ArrayList<Plato> platos) {
        int n = platos.size();

        for (int i = 1; i < n; i++) {
            Plato key = platos.get(i);
            int j = i - 1;

            while (j >= 0 && platos.get(j).getTiempoPreparacion() > key.getTiempoPreparacion()) {
                platos.set(j + 1, platos.get(j));
                j = j - 1;
            }

            platos.set(j + 1, key);
        }
    }

    public static int busquedaBinaria(ArrayList<Plato> platos, String nombrePlato) {
        int inicio = 0;
        int fin = platos.size() - 1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            Plato platoMedio = platos.get(medio);
            int comparacion = platoMedio.getNombre().compareToIgnoreCase(nombrePlato);

            if (comparacion == 0) {
                return medio;
            }

            if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }

        return -1; //
    }

}
