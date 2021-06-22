/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.util;

/**
 *
 * @author Equipo1
 */
public enum EnumTiposDePromociones {
    
    Tipo1("Promoción tipo 1"),
    Tipo2("Promoción tipo 2"),
    Tipo3("Promoción tipo 3"),
    Tipo4("Promoción tipo 4");
    private final String tipoPromocion;
        
    EnumTiposDePromociones(String tipo) {
        this.tipoPromocion = tipo;
    }
    
}
    
