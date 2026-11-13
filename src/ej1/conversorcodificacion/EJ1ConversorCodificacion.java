/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ej1.conversorcodificacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author Angela's-PC
 */
public class EJ1ConversorCodificacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Definimos las rutas de los ficheros de origen y destino
        File ficheroOrigen = new File("src/ej1/conversorcodificacion/origen_utf8.txt");
        File ficheroDestino = new File("src/ej1/conversorcodificacion/destino_iso.txt");

        // Comprobamos que el fichero de origen existe antes de intentar leerlo
        if (!ficheroOrigen.exists()) {
            System.out.println("El fichero de origen no existe. Créalo primero para continuar...");
            return;
        }

        // Utilizamos try-with-resources para asegurar el cierre automático de flujos
        try (
                // 1 -> Flujo de entrada de bytes del fichero origen
                FileInputStream fis = new FileInputStream(ficheroOrigen); // 2 -> Envolvemos el flujo de entrada para que lea en UTF-8
                 InputStreamReader isr = new InputStreamReader(fis, "UTF-8"); // 3 -> Usamos BufferedReader para una lectura eficiente línea a línea
                 BufferedReader br = new BufferedReader(isr); // 4 -> Flujo de salida de bytes para el fichero destino
                 FileOutputStream fos = new FileOutputStream(ficheroDestino); // 5 -> Envolvemos el flujo de salida para que escriba en ISO-8859-1
                 OutputStreamWriter osw = new OutputStreamWriter(fos, "ISO-8859-1"); // 6 -> Usamos BufferedWriter para escribir de forma eficiente
                 BufferedWriter bw = new BufferedWriter(osw);) {
            String linea;
            int contadorLineas = 0;

            System.out.println("Iniciando la conversión de UTF-8 a ISO-8859-1...");

            // Bucle para leer línea a línea el fichero de origen
            while ((linea = br.readLine()) != null) {
                // Escribimos la misma línea en el fichero destino
                bw.write(linea);
                // Añadimos un salto de línea
                bw.newLine();
                contadorLineas++;
            }

            // Forzamos el vaciado del búfer de escritura para asegurar que todo se grabe en el disco
            bw.flush();

            System.out.println("¡Conversión completada con éxito!");
            System.out.println("Se ha procesado " + contadorLineas + " líneas.");

        } catch (Exception e) {
            // Capturamos cualqueir error de E/S que pueda ocurrir
            System.out.println("Error drante el procesamiento del fichero: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
