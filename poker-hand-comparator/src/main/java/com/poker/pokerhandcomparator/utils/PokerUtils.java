package com.poker.pokerhandcomparator.utils;

import com.poker.pokerhandcomparator.model.Carta;
import com.poker.pokerhandcomparator.model.Mano;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PokerUtils {

    //Converiete un solo palo a su nombre completo
    public static String convertirPaloANombreCompleto(String palo) {
        return switch (palo) {
            case "H" -> "Heart";
            case "D" -> "Diamond";
            case "C" -> "Club";
            case "S" -> "Spade";
            default -> throw new IllegalArgumentException("Palo no Válido: " + palo);
        };
    }

    //COnvertir Valor a nombre Completo "K = King"
    public static String convertirValorACompleto(String valor) {
        return switch (valor) {
            case "K" -> "King";
            case "Q" -> "Queen";
            case "J" -> "Jack";
            case "A" -> "Ace";
            default -> valor; // Si no es una figura, simplemente devolvemos el valor
        };
    }


    //Metodo para convertir las cartas a un array de solo valores
    public static String[] convertirCartasFormatoSoloValor(List<Carta> cartas) {
        return cartas.stream()
                .map(Carta::getValor) // Solo vvalor de la carta
                .toArray(String[]::new);
    }

    //Metodo para convertir las cartas aun array de solo palos
    public static String[] convertirCartasFormatoSoloPalo(List<Carta> cartas) {
        return cartas.stream()
                .map(carta -> convertirPaloANombreCompleto(carta.getPalo())) //Convierte el Palo a nombre completo
                .toArray(String[]::new);
    }


    //Metodo para convertir una lista de cartas a un array de strings con formato "valor+palo"

    public static String[] convertirCartasAString(List<Carta> cartas) {
        return cartas.stream()
                .map(carta -> carta.getValor() + carta.getPalo()) //combinamos el valor y el valo en una sola cadena
                .toArray(String[]::new); //COnvierte la lisata de cadenas en un array de strings
    }

    //Ordenar Cartar FullHouse (primero Three of Kind y luego Pair
    public static List<Carta> ordenarFullHouse(Mano mano) {
        Map<String, Long> conteoValores = mano.getCartas().stream()
                .collect(Collectors.groupingBy(Carta::getValor, Collectors.counting()));

        List<Carta> threeOfAKind = new java.util.ArrayList<>(mano.getCartas().stream()
                .filter(carta -> conteoValores.get(carta.getValor()) == 3)
                .toList());

        List<Carta> pair = mano.getCartas().stream()
                .filter(carta -> conteoValores.get(carta.getValor()) == 2)
                .toList();

        //Concatenamos ambas listas
        threeOfAKind.addAll(pair);

        return threeOfAKind;
    }

    // Ordenar TwoPair
    public static List<String> ordenarTwoPair(Mano mano) {
        // Agrupar las cartas por su valor y contar cuántas veces aparece cada valor
        Map<String, Long> conteoValores = mano.getCartas().stream()
                .collect(Collectors.groupingBy(Carta::getValor, Collectors.counting()));

        // Obtener los pares (conteo de 2), eliminando duplicados y ordenándolos
        List<String> pares = mano.getCartas().stream()
                .filter(carta -> conteoValores.get(carta.getValor()) == 2)
                .map(Carta::getValor)
                .distinct() // Eliminar duplicados para tener solo un valor de cada par
                .sorted((v1, v2) -> Integer.compare(CartaUtils.convertirValorAEntero(v2), CartaUtils.convertirValorAEntero(v1))) // Ordenar por valor descendente
                .toList();

        // Convertir las abreviaturas (King -> K, Queen -> Q, etc.)
        pares = pares.stream()
                .map(CartaUtils::convertirAbreviaturaAValor)
                .toList();

        if (pares.size() >= 2) {
            return List.of(pares.get(0), pares.get(1)); // Devuelve los dos pares
        }
        return List.of();
    }




}
