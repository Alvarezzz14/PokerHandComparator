package com.poker.pokerhandcomparator.service.imple;

import com.poker.pokerhandcomparator.model.Carta;
import com.poker.pokerhandcomparator.model.Mano;
import com.poker.pokerhandcomparator.model.ResultadoComparacion;
import com.poker.pokerhandcomparator.service.IPokerService;
import com.poker.pokerhandcomparator.utils.CartaUtils;
import com.poker.pokerhandcomparator.utils.EvaluadorCartas;
import com.poker.pokerhandcomparator.utils.PokerUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class pokerServiceImpl implements IPokerService {

    @Override
    public ResultadoComparacion compararManos(String mano1Str, String mano2Str) {

        //Convertimos las cadenas de texto a objetos "Mano"
        Mano mano1 = CartaUtils.convertirCadenaAMano(mano1Str);
        Mano mano2 = CartaUtils.convertirCadenaAMano(mano2Str);


        // Comprobar Escalera Real //RoyalFlush
        if (EvaluadorCartas.esEscaleraReal(mano1)) {
            return new ResultadoComparacion("hand1", "RoyalFlush", PokerUtils.convertirCartasFormatoSoloPalo(mano1.getCartas()));
        }
        if (EvaluadorCartas.esEscaleraReal(mano2)) {
            return new ResultadoComparacion("hand2", "RoyalFlush", PokerUtils.convertirCartasFormatoSoloPalo(mano2.getCartas()));
        }

        //Comprobar Escalera Color //StraightFlush
        if (EvaluadorCartas.esEscaleraColor(mano1)) {
            return new ResultadoComparacion("hand1", "StraightFlush", PokerUtils.convertirCartasFormatoSoloPalo(mano1.getCartas()));
        }
        if (EvaluadorCartas.esEscaleraColor(mano2)) {
            return new ResultadoComparacion("hand2", "StraightFlush", PokerUtils.convertirCartasFormatoSoloPalo(mano2.getCartas()));
        }

        // Comprobar Full Poker (Four of a Kind) FourOfAKind
        if (EvaluadorCartas.esPoker(mano1)) {
            return new ResultadoComparacion("hand1", "FourOfAKind", PokerUtils.convertirCartasFormatoSoloValor(mano1.getCartas(), true));
        }
        if (EvaluadorCartas.esPoker(mano2)) {
            return new ResultadoComparacion("hand2", "FourOfAKind", PokerUtils.convertirCartasFormatoSoloValor(mano2.getCartas(), true));
        }

        //Comprobar FUll House //FullHouse
        if (EvaluadorCartas.esFullHouse(mano1)) {
            List<String> resultadoFormateado = PokerUtils.ordenarFullHouse(mano1);
            String[] resultadoArray = resultadoFormateado.toArray(new String[0]);
            return new ResultadoComparacion("hand1", "FullHouse", resultadoArray);
        }
        if (EvaluadorCartas.esFullHouse(mano2)) {
            List<String> resultadoFormateado = PokerUtils.ordenarFullHouse(mano1);
            String[] resultadoArray = resultadoFormateado.toArray(new String[0]);

            return new ResultadoComparacion("hand2", "FullHouse", resultadoArray);
        }

        //Comprobar si es Color //Flush
        if (EvaluadorCartas.esColor(mano1)) {
            return new ResultadoComparacion("hand1", "Flush", PokerUtils.convertirCartasFormatoSoloPalo(mano1.getCartas()));
        }
        if (EvaluadorCartas.esColor(mano2)) {
            return new ResultadoComparacion("hand2", "Flush", PokerUtils.convertirCartasFormatoSoloPalo(mano2.getCartas()));
        }

        ////Comprobar Escalera Straight
        if (EvaluadorCartas.esEscaleraStraight(mano1)) {
            return new ResultadoComparacion("hand1", "Straight", PokerUtils.convertirCartasFormatoSoloValor(mano1.getCartas(), true));
        }
        if (EvaluadorCartas.esEscaleraStraight(mano2)) {
            return new ResultadoComparacion("hand2", "Straight", PokerUtils.convertirCartasFormatoSoloValor(mano2.getCartas(), true));
        }

        // Comprobar Three of a Kind
        if (EvaluadorCartas.esThreeOfAKind(mano1)) {
            List<String> resultadoFormateado = PokerUtils.ordenarThreeOfAKind(mano1);
            String[] resultadoArray = resultadoFormateado.toArray(new String[0]);
            return new ResultadoComparacion("hand1", "ThreeOfAKind", resultadoArray);
        }
        if (EvaluadorCartas.esThreeOfAKind(mano2)) {
            List<String> resultadoFormateado = PokerUtils.ordenarThreeOfAKind(mano2);
            String[] resultadoArray = resultadoFormateado.toArray(new String[0]);
            return new ResultadoComparacion("hand2", "ThreeOfAKind", resultadoArray);
        }


        // Comprobar Two Pair
        if (EvaluadorCartas.esTwoPair(mano1)) {
            List<String> resultadoFormateado = PokerUtils.ordenarTwoPair(mano1);
            String[] resultadoArray = resultadoFormateado.toArray(new String[0]); // Convertir la lista a un array
            return new ResultadoComparacion("hand1", "TwoPair", resultadoArray);
        }
        if (EvaluadorCartas.esTwoPair(mano2)) {
            List<String> resultadoFormateado = PokerUtils.ordenarTwoPair(mano2);
            String[] resultadoArray = resultadoFormateado.toArray(new String[0]); // Convertir la lista a un array
            return new ResultadoComparacion("hand2", "TwoPair", resultadoArray);
        }

        //COmprobar OnePair
        if (EvaluadorCartas.esOnePair(mano1)) {
            List<String> resultadoFormateado = PokerUtils.ordenarOnePair(mano1);
            String[] resultadoArray = resultadoFormateado.toArray(new String[0]);
            return new ResultadoComparacion("hand1", "OnePair", resultadoArray);
        }

        if (EvaluadorCartas.esOnePair(mano2)) {
            List<String> resultadoFormateado = PokerUtils.ordenarOnePair(mano2);
            String[] resultadoArray = resultadoFormateado.toArray(new String[0]);
            return new ResultadoComparacion("hand2", "OnePair", resultadoArray);
        }

        //Si ninguna de las manos tiene una de las categorias anteriores, compara por la Carta Alta
        return PokerUtils.compararCartaAlta(mano1, mano2);
    }



}
