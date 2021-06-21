/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.controlador.util;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Emmanuel Chable
 */
public class TablaManager {

    public static Object[] obtenerFilaSeleccionada(JTable tabla) {

        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada < 0) {
            return null;
        }

        Object[] data = new Object[tabla.getColumnCount()];

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            data[i] = tabla.getValueAt(filaSeleccionada, i);
        }

        return data;
    }

    public static void configurarTabla(JTable tabla, String[] headers) {
        DefaultTableModel model = new DefaultTableModel(headers, 0);

        tabla.setModel(model);

        tabla.repaint();

    }

    public static void agregarFila(JTable table, String[] fila) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.addRow(fila);
        table.repaint();
    }

    public static void eliminarFilasTable(JTable tabla) {
        DefaultTableModel tableModel = (DefaultTableModel) tabla.getModel();
        for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }
        tabla.setModel(tableModel);
        tabla.repaint();
    }

}
