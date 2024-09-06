package com.poker.pokerhandcomparator.controller;

import com.poker.pokerhandcomparator.model.ResultadoComparacion;
import com.poker.pokerhandcomparator.service.IPokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.poker.pokerhandcomparator.dto.ManoDTO;


@RestController
@RequestMapping("/poker")
public class PokerController {

    @Autowired
    private IPokerService pokerService;

    //Endpoint
    @PostMapping("/validation")
    public ResultadoComparacion compararManos(@RequestBody ManoDTO manosDTO) {
        return pokerService.compararManos(manosDTO.getHand1(), manosDTO.getHand2());
    }

}
