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
public class NoHayClientesAsignadosException extends Exception {

    @Override
    public String getMessage() {
        return "No se han asignado clientes a la promocion";
    }
    
}
