package com.cherrysoft.controlador.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Utilidades para el menejo y uso de JTables
 *
 * @author Emmanuel Chable
 */
public class TablaManager {

    public interface EventoClick {

        public void enClick(MouseEvent e);
    }

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

    public static void establecerEventoClick(JTable tabla, EventoClick event) {
        tabla.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                event.enClick(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
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
