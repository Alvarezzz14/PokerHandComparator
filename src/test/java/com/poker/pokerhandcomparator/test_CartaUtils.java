package com.poker.pokerhandcomparator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.poker.pokerhandcomparator.model.Carta;
import com.poker.pokerhandcomparator.utils.CartaUtils;
import java.util.List;

public class test_CartaUtils {

    @Test
    public void testConvertirCadenaAMano() {
        String manoStr = "10H JH QH KH AH";
        List<Carta> cartas = CartaUtils.convertirCadenaAMano(manoStr).getCartas();

        assertEquals(5, cartas.size());
        assertEquals("10", cartas.get(0).getValor());
        assertEquals("H", cartas.get(0).getPalo());
        assertEquals("A", cartas.get(4).getValor());
        assertEquals("H", cartas.get(4).getPalo());
    }
}
