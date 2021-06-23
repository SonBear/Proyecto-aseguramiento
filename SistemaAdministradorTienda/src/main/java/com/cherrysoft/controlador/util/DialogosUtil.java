package com.cherrysoft.controlador.util;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * Utilidades para la generacion de mensajes emergentes
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
