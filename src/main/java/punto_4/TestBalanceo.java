package punto_4;

import punto_3.PilaGenerica;

public class TestBalanceo {
    public static boolean estaBalanceado(String s) {
        if (s == null) return false;

        PilaGenerica<Character> pila = new PilaGenerica<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // Si es un símbolo de apertura, lo guardamos en la pila
            if (c == '(' || c == '[' || c == '{') {
                pila.apilar(c);
            }
            // Si es un símbolo de cierre, verificamos el tope
            if (c == ')' || c == ']' || c == '}') {
                // Hay un cierre pero no hay ninguna apertura registrada
                if (pila.esVacia()) {
                    return false;
                }
                // Sacamos el último de apertura que habíamos guardado
                char apertura = pila.desapilar();

                // Verificamos si hacen par correcto
                if (!esParCorrecto(apertura, c)) {
                    return false;
                }
            }
        }

        // Si la pila quedó completamente vacía, todo se cerró en orden
        return pila.esVacia();
    }

    // Auxiliar para comparar la apertura con su cierre correspondiente
    private static boolean esParCorrecto(char apertura, char cierre) {
        return (apertura == '(' && cierre == ')') ||
                (apertura == '[' && cierre == ']') ||
                (apertura == '{' && cierre == '}');
    }

    public static void main(String[] args) {
        // El String a verificar puede venir por parámetro de entrada (args[0]) o probarse en el main:
        String textoPrueba = (args.length > 0) ? args[0] : "{()[()]}";

        if (estaBalanceado(textoPrueba)) {
            System.out.println("El String \"" + textoPrueba + "\" ESTÁ balanceado.");
        } else {
            System.out.println("El String \"" + textoPrueba + "\" NO está balanceado.");
        }
    }
}
