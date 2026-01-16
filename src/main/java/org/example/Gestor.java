package org.example;

import java.util.Scanner;

public class Gestor{

    // VARIABLE QUE GUARDA EL HASH DE LA CONTRASEÑA ( LO QUE SIMULA LA BASE DE DATOSS )
    private String hashGuardado;

    // MÉTODO PARA REGISTRAR AL USUARIO
    public void registrarUsuario() {

        Scanner teclado = new Scanner(System.in);
        System.out.print("PON TU CONTRASEÑA: ");
        String contra = teclado.nextLine();

        // CALCULAR EL HASH DE ESA CONTRASEÑA INTRODUCIDA Y GUARDARLO
        hashGuardado = Hash.cifrar(contra);

        System.out.println("TE HAS REGISTRADOOO, AHORA INICIA SESIÓN PARA PROBAR.");
    }

    // MÉTODO PARA INICIAR SESIÓN
    public void iniciarSesion() {

        Scanner teclado = new Scanner(System.in);
        System.out.print("ESCRIBE TU CONTRASEÑA MELÓN: ");
        String contra = teclado.nextLine();

        // CALCULAR EL HASH DE LO QUE ACABA DE ESCRIBIR
        String hashIntento = Hash.cifrar(contra);

        // COMPARAR EL HASH NUEVO CON EL QUE ESTÁ GUARDADO ANTERIORMENTE
        if (hashIntento.equals(hashGuardado)) {

            // SI COINCIDEN
            System.out.println("ERAS TUUUU");

        } else {
            System.out.println("ESTA NO ES TU CONTRASEÑA");
        }
    }
}