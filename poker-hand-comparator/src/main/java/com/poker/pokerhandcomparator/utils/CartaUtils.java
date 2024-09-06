package com.poker.pokerhandcomparator.utils;

//Convertir Vslores de cartas
public class CartaUtils {

    //Convertir valor de carta a numero
    public static int convertirValorAEntero(String valor) {
        switch (valor) {
            case "A": return 14;
            case "K": return 13;
            case "Q": return 12;
            case "J": return 11;
            default: return Integer.parseInt(valor); // NUmeros (2-10)
        }
    }

    //Convierte un numero entero al valor textual de la carta
    public static String convertirEnteroAValor(int valor) {
        switch (valor) {
            case 14: return "A";
            case 13: return "K";
            case 12: return "Q";
            case 11: return "J";
            default: return String.valueOf(valor);
        }
    }
}
