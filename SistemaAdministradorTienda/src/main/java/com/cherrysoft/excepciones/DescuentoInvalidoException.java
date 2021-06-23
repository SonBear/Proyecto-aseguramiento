/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.excepciones;

/**
 *
 * @author Equipo1
 */
public class DescuentoInvalidoException extends Exception {

    @Override
    public String getMessage() {
        return "El descuento es un porcentaje, debe ser un número entre 0 y 1";
    }
    
}
