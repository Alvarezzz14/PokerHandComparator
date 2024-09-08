package com.poker.pokerhandcomparator.utils;

import com.poker.pokerhandcomparator.exceptions.utils.InvalidCardFormatException;
import com.poker.pokerhandcomparator.exceptions.utils.InvalidCardValueException;
import com.poker.pokerhandcomparator.exceptions.utils.InvalidHandSizeException;
import com.poker.pokerhandcomparator.exceptions.utils.ManoSizeUtils;
import com.poker.pokerhandcomparator.model.Carta;
import com.poker.pokerhandcomparator.model.Mano;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Convertir Valores de cartas
public class CartaUtils {

    // Convertir valor de carta a número
    public static int convertirValorAEntero(String valor) {


        return switch (valor) {
            case "A", "Ace" -> 14;
            case "K", "King" -> 13;
            case "Q", "Queen" -> 12;
            case "J", "Jack" -> 11;
            default -> {
                try {
                    yield Integer.parseInt(valor); // Números (2-10)
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Valor de carta inválido: " + valor);
                }
            }
        };
    }


    // Convierte un número entero al valor textual de la carta
    public static String convertirEnteroAValor(int valor) {
        return switch (valor) {
            case 14 -> "A";
            case 13 -> "K";
            case 12 -> "Q";
            case 11 -> "J";
            case 2,3,4,5,6,7,8,9,10 -> String.valueOf(valor);
            default -> throw new InvalidCardValueException("Valor de carta inválido: " + valor);
        };
    }

    //COnvertir valor a Aberviatura
    public static String convertirValorAAbreviatura(String valor) {
        return switch (valor) {
            case "A" -> "As";
            case "King" -> "K";
            case "Queen" -> "Q";
            case "Jack" -> "J";
            case "2","3","4","5","6","7","8","9","10" -> valor;
            default -> throw new InvalidCardValueException("Valor de carta inválido: " + valor);
        };
    }
    public static String convertirAbreviaturaAValor(String valor) {
        return switch (valor) {
            case "A" -> "A";
            case "K" -> "King";
            case "Q" -> "Queen";
            case "J" -> "Jack";
            case "2","3","4","5","6","7","8","9","10" -> valor;
            default -> throw new InvalidCardValueException("Valor de carta inválido: " + valor);
        };
    }

    //Convertir cadena a un OBJETO Mano
    public static Mano convertirCadenaAMano(String manoStr){
        try {
            List<Carta> cartas = Arrays.stream(manoStr.split(" "))
                    .map(CartaUtils::crearCarta)
                    .collect(Collectors.toList());

            ManoSizeUtils.validarTamanoMano(cartas);

            return new Mano(cartas);

        } catch (IllegalArgumentException e) {
            throw new InvalidHandSizeException("Formato de mano inválido: " + manoStr);
        }

    }


    //Crear un objeto Carta apartir de una cadena
    public static Carta crearCarta(String cartaStr) {

        if (cartaStr == null || cartaStr.length() < 2) {
            throw new InvalidCardFormatException("Formato de Carta Inválido: " + cartaStr);
        }

        String valor = cartaStr.substring(0, cartaStr.length() -1);
        String palo = cartaStr.substring(cartaStr.length() -1);

        if(!esValorValido(valor)) {
            throw new InvalidCardFormatException("Valor de carta inválido: " + valor);
        }

        if(!esPaloValido(palo)) {
            throw new InvalidCardFormatException("Palo de carta inválido: " +palo);
        }
        return new Carta(valor, palo);
    }

    //Verificar si es un valor valido
    private static boolean esPaloValido(String palo) {
        return palo.matches("[CDHS]");
    }
    //Verificar si es un palo valido
    private static boolean esValorValido(String valor) {
        return valor.matches("^(10|[2-9]|[JQKA])$");
    }

}
