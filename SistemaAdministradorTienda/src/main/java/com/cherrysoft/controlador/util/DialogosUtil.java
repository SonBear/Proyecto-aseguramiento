/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.controlador.util;

import java.awt.Component;
import java.awt.Frame;
import javax.swing.JOptionPane;

/**
 *
 * @author Emmanuel Chable
 */
public class DialogosUtil {

    public static boolean mostrarDialogoDeConfirmacion(Component owner, String msj) {
        return JOptionPane.showConfirmDialog(owner, msj, "¿Esta seguro?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION;
    }

    public static void mostrarDialogoDeError(Component owner, String mjs) {
        JOptionPane.showMessageDialog(owner, mjs, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
