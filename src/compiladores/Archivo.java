/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiladores;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pereiracc
 */
public class Archivo {
    
    // Se declaran las variables
    public String nameFile = "";
    public String textoCompleto = "";
    public List<String> lineasTexto = new ArrayList<>();
    public List<String> lineasTextoRevisado = new ArrayList<>();
        
    public boolean validoExtensionArchivo(String filename){
        
        // Se obtiene la extension del archivo
        String extension = "";
        boolean esValido = false;

        int exten = filename.lastIndexOf('.');
        
        if (exten >= 0) {
            extension = filename.substring(exten+1);
        }
        
        if(extension.equals("vb")){
           esValido = true;
        }
        
        // Se retorna el resultado
        return esValido;
        
    }
    
    public void leerArchivo(String filename, AnalisisLexico analisisLexico) throws IOException {
        
        String linea;
        BufferedReader br;
        
        try{
            
            // Se obtiene el nombre del archivo
            nameFile = filename;
            
            // Lee el todo contenido del archivo
            String fileContent = Files.readString(Paths.get(filename));

            // Se insertar el texto en la variable Texto
            textoCompleto = fileContent;

            br = new BufferedReader(new FileReader(filename));
            
            //Lectura del archivo por línea
            while ((linea = br.readLine()) != null) 
            {
                // Se inserta cada linea
                lineasTexto.add(linea);
                
                // Se analiza lexico la linea
                analisisLexico.clasificacionTokens(linea);
            }
            
        }
        catch(IOException ex){
            System.out.println("Archivo no encontrado o no se pudo abrir");
        }
    }
    
    public void escribirArchivo() {
        
        try {
            // Se agrega las variables
            int contadorLineas = 1;
            String nameFileErrors = nameFile + "-Errores.txt";
            FileWriter fstream;
            
            // Se utiliza FileWriter para escribir
            fstream = new FileWriter(nameFileErrors);
            BufferedWriter out = new BufferedWriter(fstream);
            
            // Se escribe cada linea en el archivo
            for (String line : lineasTextoRevisado) 
            { 
                // Se adjunta el numero de la linea
                String numberLine = "00";
                if(contadorLineas >= 1 && contadorLineas <= 9){
                    numberLine = "000";
                }
                
                // Se escribe en el archivo
                String newLine = numberLine + contadorLineas + " " + line;
                
                out.write(newLine);
                out.newLine();
                
                contadorLineas++;
            }
            
            // Se cierre el archivo
            out.close();  
            
        } catch (IOException e) {
          System.out.println("Error al momento de escribir el archivo");
        }
    }
}
