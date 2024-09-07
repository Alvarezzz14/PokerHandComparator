package com.poker.pokerhandcomparator.utils;

import com.poker.pokerhandcomparator.model.Carta;
import com.poker.pokerhandcomparator.model.Mano;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PokerUtils {

    //Converiete un solo palo a su nombre completo
    public static String convertirPaloANombreCompleto(String palo) {
        switch (palo){
            case "H":
                return "Heart";
            case "D":
                return "Diamond";
            case "C":
                return "Club";
            case "S":
                return "Spade";
            default:
                throw new IllegalArgumentException("Palo no Válido: " + palo);
        }
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


}
