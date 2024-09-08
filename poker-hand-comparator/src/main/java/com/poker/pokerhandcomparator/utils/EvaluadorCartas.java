package com.poker.pokerhandcomparator.utils;

import com.poker.pokerhandcomparator.model.Carta;
import com.poker.pokerhandcomparator.model.Mano;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//clase para evaluar los diferentes tipos de manos de poker
public class EvaluadorCartas {

    //Verifica si es TwoPair
    public static boolean esTwoPair(Mano mano) {
        Map<String, Long> conteoValores = mano.getCartas().stream()
                .collect(Collectors.groupingBy(Carta::getValor, Collectors.counting()));

        // Verificamos que existan dos pares
        long pares = conteoValores.values().stream().filter(count -> count == 2).count();
        return pares == 2;
    }

    //Verifica si es ThreeOFKind
    public static boolean esThreeOfAKind(Mano mano) {
        Map<String, Long> conteoValores = mano.getCartas().stream()
                .collect(Collectors.groupingBy(Carta::getValor, Collectors.counting()));

        // Verificamos que exista un trío
        return conteoValores.containsValue(3L);
    }


    //Verifica si una mano es escalera Real (Royal FLush)
    public static boolean esEscaleraReal(Mano mano) {
        return esEscaleraColor(mano) && mano.getCartas().stream().anyMatch(carta -> carta.getValor().equals("A"));

    }

    //verifica si una mano es escalera De COlor (Straight FLush)
    public static boolean esEscaleraColor(Mano mano) {
        return esEscalera(mano) && esColor(mano);
    }

    //Verififcar si una mano es un Poker(Four of kind)
    public static boolean esPoker(Mano mano) {
        Map<String, Long> conteoValores = mano.getCartas().stream()
                .collect(Collectors.groupingBy(Carta::getValor, Collectors.counting()));
        return conteoValores.containsValue(4L);
    }

    //Verifica si una mano es Full House.
    public static boolean esFullHouse(Mano mano) {
        Map<String, Long> conteoValores = mano.getCartas().stream()
                .collect(Collectors.groupingBy(Carta::getValor, Collectors.counting()));
        return conteoValores.containsValue(3L) && conteoValores.containsValue(2L);
    }

    //Verifica si una mano es COlor (Flush)
    public static boolean esColor(Mano mano) {
        String palo = mano.getCartas().get(0).getPalo();
        return mano.getCartas().stream().allMatch(carta -> carta.getPalo().equals(palo));
    }

    //Metodo para verificar si es una Escalera
    public static boolean esEscalera(Mano mano) {
        List<String> valoresOrdenados = mano.getCartas().stream()
                .map(carta -> CartaUtils.convertirValorAEntero(carta.getValor()))
                .sorted()
                .map(CartaUtils::convertirEnteroAValor)
                .toList();

        for (int i = 0; i < valoresOrdenados.size() - 1; i++) {
            if (CartaUtils.convertirValorAEntero(valoresOrdenados.get(i)) + 1 != CartaUtils.convertirValorAEntero(valoresOrdenados.get(i +1))) {
                return false;
            }
        }
        return  true;
    }

}
