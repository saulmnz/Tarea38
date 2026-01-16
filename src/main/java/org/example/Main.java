package org.example;

import java.util.Scanner;

public class Main {

        public static void main(String[] args) {

            // CREAR UN OBJETO DEL GESTOR DE CLAVES
            Gestor gestor = new Gestor();

            System.out.println("LOGEATE DE FORMA SEGURA NIÑO");

            System.out.println("REGÍSTRATEEE");
            gestor.registrarUsuario();
            System.out.println();

            System.out.println("AHORA LOGEATE CON TU CONTRASEÑA:");
            gestor.iniciarSesion();

            System.out.println("\nCHAO NIÑO.");
        }
}
