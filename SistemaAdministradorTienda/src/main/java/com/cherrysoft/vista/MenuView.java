/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft.vista;

import javax.swing.JButton;

/**
 *
 * @author VictorEnrique
 */
public class MenuView extends javax.swing.JFrame {

    /**
     * Creates new form MenuView
     */
    public MenuView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        botonVender = new javax.swing.JButton();
        botonClientes = new javax.swing.JButton();
        botonUsuarios = new javax.swing.JButton();
        botonTickets = new javax.swing.JButton();
        botonPromosiones = new javax.swing.JButton();
        botonInventario = new javax.swing.JButton();
        botonUtilidades = new javax.swing.JButton();
        botonProveedores = new javax.swing.JButton();
        botonVentasDiarias = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        botonCerrarSesion = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(29, 29, 29));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        botonVender.setBackground(new java.awt.Color(102, 102, 102));
        botonVender.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        botonVender.setForeground(new java.awt.Color(255, 255, 255));
        botonVender.setText("Vender");
        botonVender.setBorderPainted(false);

        botonClientes.setBackground(new java.awt.Color(102, 102, 102));
        botonClientes.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        botonClientes.setForeground(new java.awt.Color(255, 255, 255));
        botonClientes.setText("Clientes");
        botonClientes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonClientes.setBorderPainted(false);

        botonUsuarios.setBackground(new java.awt.Color(102, 102, 102));
        botonUsuarios.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        botonUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        botonUsuarios.setText("Usuarios");
        botonUsuarios.setBorderPainted(false);

        botonTickets.setBackground(new java.awt.Color(102, 102, 102));
        botonTickets.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        botonTickets.setForeground(new java.awt.Color(255, 255, 255));
        botonTickets.setText("Tickets");
        botonTickets.setBorderPainted(false);

        botonPromosiones.setBackground(new java.awt.Color(102, 102, 102));
        botonPromosiones.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        botonPromosiones.setForeground(new java.awt.Color(255, 255, 255));
        botonPromosiones.setText("Promociones");
        botonPromosiones.setBorderPainted(false);

        botonInventario.setBackground(new java.awt.Color(102, 102, 102));
        botonInventario.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        botonInventario.setForeground(new java.awt.Color(255, 255, 255));
        botonInventario.setText("Inventario");
        botonInventario.setBorderPainted(false);

        botonUtilidades.setBackground(new java.awt.Color(102, 102, 102));
        botonUtilidades.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        botonUtilidades.setForeground(new java.awt.Color(255, 255, 255));
        botonUtilidades.setText("Utilidades");
        botonUtilidades.setBorderPainted(false);

        botonProveedores.setBackground(new java.awt.Color(102, 102, 102));
        botonProveedores.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        botonProveedores.setForeground(new java.awt.Color(255, 255, 255));
        botonProveedores.setText("Proveedores");
        botonProveedores.setBorderPainted(false);

        botonVentasDiarias.setBackground(new java.awt.Color(102, 102, 102));
        botonVentasDiarias.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        botonVentasDiarias.setForeground(new java.awt.Color(255, 255, 255));
        botonVentasDiarias.setText("Ventas Diarias");
        botonVentasDiarias.setBorderPainted(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Men??");

        botonCerrarSesion.setBackground(new java.awt.Color(168, 8, 8));
        botonCerrarSesion.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        botonCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        botonCerrarSesion.setText("Cerrar Sesi??n");
        botonCerrarSesion.setToolTipText("");
        botonCerrarSesion.setBorderPainted(false);

        botonSalir.setBackground(new java.awt.Color(168, 8, 8));
        botonSalir.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        botonSalir.setForeground(new java.awt.Color(255, 255, 255));
        botonSalir.setText("Salir");
        botonSalir.setBorderPainted(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botonUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonPromosiones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botonUtilidades, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonVentasDiarias, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonCerrarSesion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(41, 41, 41))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(279, 279, 279)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonTickets, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonVender, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(283, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(botonVender, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonInventario)
                .addGap(11, 11, 11)
                .addComponent(botonTickets)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botonUsuarios)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonVentasDiarias))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonPromosiones)
                            .addComponent(botonCerrarSesion)))
                    .addComponent(botonUtilidades))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonProveedores)
                    .addComponent(botonSalir))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCerrarSesion;
    private javax.swing.JButton botonClientes;
    private javax.swing.JButton botonInventario;
    private javax.swing.JButton botonPromosiones;
    private javax.swing.JButton botonProveedores;
    private javax.swing.JButton botonSalir;
    private javax.swing.JButton botonTickets;
    private javax.swing.JButton botonUsuarios;
    private javax.swing.JButton botonUtilidades;
    private javax.swing.JButton botonVender;
    private javax.swing.JButton botonVentasDiarias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public JButton getBotonCerrarSesion() {
        return botonCerrarSesion;
    }

    public JButton getBotonClientes() {
        return botonClientes;
    }

    public JButton getBotonInventario() {
        return botonInventario;
    }

    public JButton getBotonPromosiones() {
        return botonPromosiones;
    }

    public JButton getBotonProveedores() {
        return botonProveedores;
    }

    public JButton getBotonSalir() {
        return botonSalir;
    }

    public JButton getBotonTickets() {
        return botonTickets;
    }

    public JButton getBotonUsuarios() {
        return botonUsuarios;
    }

    public JButton getBotonUtilidades() {
        return botonUtilidades;
    }

    public JButton getBotonVender() {
        return botonVender;
    }

    public JButton getBotonVentasDiarias() {
        return botonVentasDiarias;
    }

}
