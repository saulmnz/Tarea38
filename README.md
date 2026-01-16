# SISTEMA LOGIN 🔐

> ***Esta tarea trata sobre el desarrollo de una app que se encarga de simular el sistema de log in utilizando el algoritmo SHA-256. Para ello, he separado la lógica del programa en dos clases, siendo tres en total, consta de una clase Main, clase ejecutora del programa ( llama al resto de clases ), la clase Gestor, esta clase contiene métodos para el registro del usuario y su inicio de sesión posterior y, la última clase Hash, se encarga de cifrar la contraseña y devolver el hash hexadecimal, esta clase será llamada por la clase gestor para el cifrado correcto de la contraseña... Toda la lógica del programa está explicada línea por línea ☺️🦄⛓️‍💥***

---

## CLASE HASH 🏺

```java
package org.example;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Hash {

    // MÉTODO QUE RECIBE UNA CONTRASEÑA Y DEVUELVE EL HASH HEXADECIMAL
    public static String cifrar(String contra) {

        try {
            // OBTENER LA INSTANCIA DEL ALGORITMO
            // CREAR EL OBJETO MessageDigest CON EL ALGORITMO SHASHASHA CUATROPAQUETEDESHÁ-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // CONVERTIR LA CONTRASINALE A BYTES Y CALCULAR SU HASH
            byte[] bytesDelHash = md.digest(contra.getBytes());

            // CONSTRUIR CADENA HEXADECIMAL A PARTIR DE LOS BYTES
            StringBuilder cadenaHex = new StringBuilder();

            // RECORRER CADA BYTE DEL HASH QUE DEVOLVIÓ EL MÉTODO DIGEST
            for (byte b : bytesDelHash) {

                // CONVERTIR EL BYTE A ENTERO Y LUEGO A HEXADECIMAL
                // 0xff & b ASEGURA QUE EL BYTE SE TRATE COMO VALOR POSITIVO
                // ESTO ES DIFICIL EXPLICARLO CON MIS PALABRAS, INTRODUZCO EXPLICACIÓN TEÓRICA
                /**
                 * En Java, el tipo byte va de -128 a 127, pero, un byte en criptografía es un valor de 0 a 255, 8 bits sin signo.
                 * Al hacer 0xff & b aplicamos una especie de máscara binaria que convierte el byte a su valor sin signo.
                 * Si b = -1, en binario es 11111111.
                 * 0xff & (-1) → da 255 → en hex: "ff".
                 * Si no se raliza este paso, Integer.toHexString(-1) daría "ffffffff" lo cual está mal.
                 */
                String hex = Integer.toHexString(0xff & b);

                // SI EL HEX TIENE SOLO UN DÍGITO, AÑADIR UN CERO A LA IZQUIERDA PARA QUE CADA BYTE OCUPE SIEMPRE 2 CARACTERES
                // ESTO SE HACE PORQUE HAY BYTES QUE DAN SOLO UN DÍGITO EN HEX 10->A ( CON ESTO TENDREMOS 0A ), PERO CADA BYTE TIENE QUE...
                // ...REPRESENTARSE CON DOS DÍGITOS PARA QUE EL HASH SEA CORRECTO Y ASÍ GARANTIZAR LA LONGITUD ESPERADA
                if (hex.length() == 1) {
                    cadenaHex.append('0');
                }

                // AÑADIR EL HEX AL RESULTADO FINAL
                cadenaHex.append(hex);
            }

            // DEVOLVEEEMOS LA CADENA HEXADECIMAL POR COMPLETO
            return cadenaHex.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("ERROR CON EL ALGORITMO", e);
        }
    }
}
```

---

## CLASE GESTOR 🐐

```java
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
```

---

## CLASE MAIN 🦇

```java
package org.example;
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

```
