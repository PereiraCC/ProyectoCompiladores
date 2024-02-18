/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package compiladores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author pereiracc
 */
public class Compiladores {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        try{
            
            // Se declara la clase de AnalisisLexico
            AnalisisLexico analisis = new AnalisisLexico();
            
             // Se declara la clase de Archivo
            Archivo archivo = new Archivo();
            
            String filename = "";
           
            // Se valida si existe un archivo para leer
            if( args.length > 0 ){
                
                // Se obtiene la ruta del archivo a evaluar
                filename = args[0];
                
                // Se valida la extension del archivo
                if(archivo.validoExtensionArchivo(filename)){
                    
                    // Lee el contenido del archivo
                    archivo.leerArchivo(filename, analisis);
                    
                    analisis.analisisTexto(archivo);
                    
                    // Se escribe el contenido del archivo
//                    archivo.escribirArchivo();
                    
                } else {
                    System.out.println("El archivo no tiene un extencion vb");
                }
                
                
            } else {
                System.out.println("Favor indicar un archivo con extencion vb para analizar.");
            }

            
        }
        catch(IOException ex){
            throw ex;
        }
        
    }
    
}
