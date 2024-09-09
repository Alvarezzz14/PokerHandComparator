package com.poker.pokerhandcomparator;

import com.poker.pokerhandcomparator.controller.PokerController;
import com.poker.pokerhandcomparator.model.Carta;
import com.poker.pokerhandcomparator.model.Mano;
import com.poker.pokerhandcomparator.model.ResultadoComparacion;
import com.poker.pokerhandcomparator.service.IPokerService;
import com.poker.pokerhandcomparator.utils.CartaUtils;
import com.poker.pokerhandcomparator.utils.EvaluadorCartas;
import com.poker.pokerhandcomparator.utils.PokerUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(PokerController.class)
public class Test_PokerServiceImpl {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private IPokerService pokerService;

    @Test
    public void test_CompararManos() throws Exception {
        ResultadoComparacion resultado = new ResultadoComparacion("hand1", "RoyalFlush", new String[]{"HEART"});

        when(pokerService.compararManos(anyString(), anyString())).thenReturn(resultado);

        mockMvc.perform(post("/poker/validation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"hand1\":\"10H JH QH KH AH\", \"hand2\":\"2C 3D 4S 8C AH\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.winnerHand").value("hand1"))
                .andExpect(jsonPath("$.winnerHandType").value("RoyalFlush"))
                .andExpect(jsonPath("$.compositionWinnerHand").value("HEART"));

    }

    @Test
    public void testCompararManos() {
        Mano mano1 = new Mano(Arrays.asList(
                new Carta("10", "H"),
                new Carta("J", "H"),
                new Carta("Q", "H"),
                new Carta("K", "H"),
                new Carta("A", "H")
        ));

        Mano mano2 = new Mano(Arrays.asList(
                new Carta("2", "D"),
                new Carta("3", "D"),
                new Carta("4", "D"),
                new Carta("5", "D"),
                new Carta("6", "D")
        ));

        String manoStr1 = Arrays.toString(PokerUtils.convertirCartasFormatoSoloValor(mano1.getCartas(), true));
        String manoStr2 = Arrays.toString(PokerUtils.convertirCartasFormatoSoloValor(mano2.getCartas(), true));



        ResultadoComparacion  resultado = pokerService.compararManos(manoStr1,manoStr2);
        assertEquals("hand1", resultado.getWinnerHand());
        assertEquals("RoyalFlush", resultado.getWinnerHandType());
    }


    @Test
    public void testEsEscalera() throws Exception {
        Mano manoEscalera = new Mano(Arrays.asList(
                new Carta("2", "H"),
                new Carta("3", "H"),
                new Carta("4", "H"),
                new Carta("5", "H"),
                new Carta("6", "H")
        ));

        assertTrue(EvaluadorCartas.esEscalera(manoEscalera));
    }


}
