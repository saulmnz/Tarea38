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