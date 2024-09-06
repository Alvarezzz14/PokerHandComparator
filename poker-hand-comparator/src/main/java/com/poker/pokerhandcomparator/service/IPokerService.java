package com.poker.pokerhandcomparator.service;

import com.poker.pokerhandcomparator.model.ResultadoComparacion;


public interface IPokerService {

    //Metodo para comprarar dos manos de poker
    ResultadoComparacion compararManos(String mano1Str, String mano2Str);

}