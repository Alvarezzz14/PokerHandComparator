package com.poker.pokerhandcomparator.controller;

import com.poker.pokerhandcomparator.exceptions.utils.InvalidCardValueException;
import com.poker.pokerhandcomparator.exceptions.utils.ManoSizeUtils;
import com.poker.pokerhandcomparator.model.Carta;
import com.poker.pokerhandcomparator.model.ResultadoComparacion;
import com.poker.pokerhandcomparator.service.IPokerService;
import com.poker.pokerhandcomparator.utils.CartaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.poker.pokerhandcomparator.dto.ManoDTO;

import java.util.List;


@RestController
@RequestMapping("/poker")
public class PokerController {

    @Autowired
    private IPokerService pokerService;

    //Endpoint
    @PostMapping("/validation")
    public ResultadoComparacion compararManos(@RequestBody ManoDTO manosDTO) {

        String cartasStrMano1 = manosDTO.getHand1();
        String cartasStrMano2 = manosDTO.getHand1();
        List<Carta> cartasMano1 = CartaUtils.convertirCadenaAMano(cartasStrMano1).getCartas();
        List<Carta> cartasMano2 = CartaUtils.convertirCadenaAMano(cartasStrMano2).getCartas();
        ManoSizeUtils.validarTamanoMano(cartasMano1);
        ManoSizeUtils.validarTamanoMano(cartasMano2);

        try {
            return pokerService.compararManos(manosDTO.getHand1(), manosDTO.getHand2());

        } catch (InvalidCardValueException e) {
            throw e; // se lanza excepcion y es capturada por GlobalExceptionHandler
        } catch (Exception e) {
            throw new RuntimeException("Error Inesperado al comparar manos", e);
        }

    }
}
