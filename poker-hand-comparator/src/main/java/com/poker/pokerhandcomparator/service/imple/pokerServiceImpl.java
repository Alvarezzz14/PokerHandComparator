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

import static com.poker.pokerhandcomparator.utils.EvaluadorCartas.esEscalera;

@Service
public class pokerServiceImpl implements IPokerService {

    @Override
    public ResultadoComparacion compararManos(String mano1Str, String mano2Str) {

        //Convertimos las cadenas de texto a objetos "Mano"
        Mano mano1 = convertirCadenaAMano(mano1Str);
        Mano mano2 = convertirCadenaAMano(mano2Str);




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
            List<String> resultadoFormateado = PokerUtils.ordenarFullHouse(mano1).stream()
                    .map(carta -> PokerUtils.convertirPaloANombreCompleto(carta.getPalo())  + " & " + carta.getValor() )
                    .toList();
            String[] resultadoArray = resultadoFormateado.toArray(new String[0]);
            return new ResultadoComparacion("hand1", "FullHouse", resultadoArray);
        }
        if (EvaluadorCartas.esFullHouse(mano2)) {
            List<String> resultadoFormateado = PokerUtils.ordenarFullHouse(mano2).stream()
                    .map(carta -> carta.getValor() + " & " + PokerUtils.convertirPaloANombreCompleto(carta.getPalo()))
                    .toList();
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
        return compararCartaAlta(mano1, mano2);
    }


    //Convertir cadena a un OBJETO Mano
    private Mano convertirCadenaAMano(String manoStr){
        List<Carta> cartas = Arrays.stream(manoStr.split(" "))
                .map(this::crearCarta)
                .collect(Collectors.toList());
        return new Mano(cartas);
    }

    //Crear un objeto Carta apartir de una cadena
    private Carta crearCarta(String cartaStr) {
        String valor = cartaStr.substring(0, cartaStr.length() -1);
        String palo = cartaStr.substring(cartaStr.length() -1);
        return new Carta(valor, palo);
    }

    //Comparar Dos manos por carta alta //HighCard
    private ResultadoComparacion compararCartaAlta(Mano mano1, Mano mano2) {
        List<Carta> cartasMano1 = mano1.getCartas().stream()
                .sorted((c1, c2) -> Integer.compare(CartaUtils.convertirValorAEntero(c2.getValor()), CartaUtils.convertirValorAEntero(c1.getValor())))
                .toList();

        List<Carta> cartasMano2 = mano2.getCartas().stream()
                .sorted((c1, c2) -> Integer.compare(CartaUtils.convertirValorAEntero(c2.getValor()), CartaUtils.convertirValorAEntero(c1.getValor())))
                .toList();

        for (int i = 0; i < 5; i++) {
            int valorMano1 = CartaUtils.convertirValorAEntero(cartasMano1.get(i).getValor());
            int valorMano2 = CartaUtils.convertirValorAEntero(cartasMano2.get(i).getValor());

            if(valorMano1 > valorMano2) {
                return new ResultadoComparacion("hand1", "HighCard", PokerUtils.convertirCartasFormatoSoloValorAbreviado(cartasMano1));
            } else if (valorMano2 > valorMano1) {
                return new ResultadoComparacion("hand2", "HighCard", PokerUtils.convertirCartasFormatoSoloValorAbreviado(cartasMano2));

            }
        }

        return new ResultadoComparacion("Empate", "HighCard", PokerUtils.convertirCartasFormatoSoloValor(mano1.getCartas(),false));

    }

}
