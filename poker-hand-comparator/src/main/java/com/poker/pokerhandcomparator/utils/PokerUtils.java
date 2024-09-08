package com.poker.pokerhandcomparator.utils;

import com.poker.pokerhandcomparator.model.Carta;
import com.poker.pokerhandcomparator.model.Mano;

import java.util.ArrayList;
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
    public static String[] convertirCartasFormatoSoloValor(List<Carta> cartas, boolean ordenAscendente) {
        if (ordenAscendente) {
            return cartas.stream()
                    .sorted((carta1, carta2) -> Integer.compare(
                            CartaUtils.convertirValorAEntero(carta1.getValor()),
                            CartaUtils.convertirValorAEntero(carta2.getValor())))
                    .map(Carta::getValor) // Solo valor de la carta
                    .toArray(String[]::new);
        } else {
            return cartas.stream()
                    .sorted((carta1, carta2) -> Integer.compare(
                            CartaUtils.convertirValorAEntero(carta2.getValor()),
                            CartaUtils.convertirValorAEntero(carta1.getValor())))
                    .map(Carta::getValor) // Solo valor de la carta
                    .toArray(String[]::new);
        }

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
                .sorted((carta1,carta2) -> Integer.compare(
                        CartaUtils.convertirValorAEntero(carta2.getValor()),
                        CartaUtils.convertirValorAEntero(carta1.getValor())))
                .map(carta -> carta.getValor() + carta.getPalo()) //combinamos el valor y el valo en una sola cadena
                .toArray(String[]::new); //COnvierte la lisata de cadenas en un array de strings
    }

    //
    public static String[] convertirCartasFormatoSoloValorAbreviado(List<Carta> cartas) {
        return cartas.stream()
                .map(carta -> CartaUtils.convertirValorAAbreviatura(carta.getValor())) // Convertir a abreviatura
                .toArray(String[]::new);
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
                .map(Carta::getValor)
                .filter(valor -> conteoValores.get(valor) == 2)
                .sorted((v1, v2) -> Integer.compare(CartaUtils.convertirValorAEntero(v2), CartaUtils.convertirValorAEntero(v1))) // Ordenar por valor descendente
                .map(CartaUtils::convertirAbreviaturaAValor)
                .toList();

        List<String> restantes = mano.getCartas().stream()
                .map(Carta::getValor)
                .filter(valor -> conteoValores.get(valor) != 2)
                .sorted((v1, v2) -> Integer.compare(CartaUtils.convertirValorAEntero(v2), CartaUtils.convertirValorAEntero(v1))) // Ordenar por valor descendente
                .map(CartaUtils::convertirAbreviaturaAValor)
                .toList();

        List<String> resultadoFinal = new ArrayList<>();
        resultadoFinal.add(pares.get(0));
        resultadoFinal.add(pares.get(2));
        resultadoFinal.add(pares.get(3));
        resultadoFinal.add(pares.get(1));
        resultadoFinal.addAll(restantes);

        return resultadoFinal;

    }

    // Ordenar Three of a Kind
    public static List<String> ordenarThreeOfAKind(Mano mano) {
        // Agrupar las cartas por su valor y contar cuántas veces aparece cada valor
        Map<String, Long> conteoValores = mano.getCartas().stream()
                .collect(Collectors.groupingBy(Carta::getValor, Collectors.counting()));

        // Obtener las cartas que forman el "Three of a Kind"
        List<String> trio = new java.util.ArrayList<>(mano.getCartas().stream()
                .map(Carta::getValor)
                .filter(valor -> conteoValores.get(valor) == 3)
                .map(CartaUtils::convertirAbreviaturaAValor) // Convertir a "King", "Queen", etc.
                .toList());

        // Obtener las cartas restantes
        List<String> restantes = mano.getCartas().stream()
                .map(Carta::getValor)
                .filter(valor -> conteoValores.get(valor) != 3)
                .sorted((v1, v2) -> Integer.compare(CartaUtils.convertirValorAEntero(v2), CartaUtils.convertirValorAEntero(v1))) // Ordenar por valor descendente
                .map(CartaUtils::convertirAbreviaturaAValor)
                .toList();


        // Combinar el trio con las cartas restantes
        trio.addAll(restantes);
        return trio;
    }

    //Ordenar OnePair
    public static List<String> ordenarOnePair(Mano mano) {
        Map<String, Long> conteoValores = mano.getCartas().stream()
                .collect(Collectors.groupingBy(Carta::getValor, Collectors.counting()));

        //Obtenemos Cartas que forman el par
        List<String> par = mano.getCartas().stream()
                .map(Carta::getValor)
                .filter(valor-> conteoValores.get(valor) == 2)
                .map(CartaUtils::convertirAbreviaturaAValor)
                .collect(Collectors.toList());

        List<String> restantes = mano.getCartas().stream()
                .map(Carta::getValor)
                .filter(valor -> conteoValores.get(valor) != 2)
                .sorted((v1, v2) -> Integer.compare(CartaUtils.convertirValorAEntero(v2), CartaUtils.convertirValorAEntero(v1))) // Ordenar por valor descendente
                .map(CartaUtils::convertirAbreviaturaAValor)
                .toList();

        par.addAll(restantes);
        return par;

    }


}
