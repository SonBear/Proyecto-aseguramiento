/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cherrysoft;

import com.cherrysoft.model.data.Cliente;
import com.cherrysoft.model.data.Usuario;
import com.cherrysoft.model.repository.UsuarioRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Emmanuel Chable
 */
public class Main {

    public static void main(String[] args) {

        UsuarioRepository usR = new UsuarioRepository();
        Usuario us = new Usuario();
        us.setAdmin(1);
        us.setContraseña("password");
        us.setCorreo("correo");

        System.out.println(usR.findAll());
        System.out.println(usR.findById(2));

    }
}
