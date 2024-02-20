/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author pereiracc
 */
public class AnalisisLexico {
    
    Tokens tokens = new Tokens();
    ManejoErrores manejoErrores = new ManejoErrores();
    List<String> lineasTextoErrores = new ArrayList<>();
    
    int contador = 0;
    
    // Crear la expresion regular
    private String patron = "(Imports|Module|Program|Sub|Main|As|String|Console|WriteLine|Dim|IO|StreamReader|Boolean|New|Try|While|Not|If|Is|Nothing|Then|True|Else|End|While|Close|Catch|Exception|System|Message)"
            + "|([a-zA-Z]+)|([<|>]+)|([+]|[-])|([=]+)|([0-9]+)|([++]{2}|[--]{2})|"
            + "([(|)]+)|([{|}]+)|([\\[|\\]])|(;)|(\\.)";
        
    public void clasificacionTokens(String texto) {
       
        
        Pattern p = Pattern.compile(patron);
        Matcher matcher = p.matcher(texto);
                
        //Se recorre todos los tokens
        while (matcher.find()) {
            contador++;
            String token1 = matcher.group(1);
            if (token1 != null) {
                tokens.setPalabrasReservadas(token1);
            }
            
            String token2 = matcher.group(2);
            if (token2 != null) {
                tokens.setIdentificadores(token2);
            }
            
            String token3 = matcher.group(3);
            if (token3 != null) {
                tokens.setOperadoresRelacionales(token3);
            }
            
            String token4 = matcher.group(4);
            if (token4 != null) {
                tokens.setOperadoresAritmeticos(token4);
            }
            
            String token5 = matcher.group(5);
            if (token5 != null) {
                tokens.setOperadoresAsignacion(token5);
            }
            
            String token6 = matcher.group(6);
            if (token6 != null) {
                tokens.setNumeros(token6);
            }
            
            String token7 = matcher.group(7);
            if (token7 != null) {
                tokens.setOperadoresIncrementoDecremento(token7);
            }
            
            String token8 = matcher.group(8);
            if (token8 != null) {
                tokens.setParentesis(token8);
            }
            
            String token9 = matcher.group(9);
            if (token9 != null) {
                tokens.setLlaves(token9);
            }
            
            String token10 = matcher.group(10);
            if (token10 != null) {
                tokens.setCorchetes(token10);
            }
            
            String token11 = matcher.group(11);
            if (token11 != null) {
                tokens.setPuntoComa(token11);
            }
            
            String token12 = matcher.group(12);
            if (token12 != null) {
                tokens.setPunto(token12);
            }
        }
                
    }
    
    public void analisisTexto(Archivo archivo){
        
        for(String line : archivo.lineasTexto){

            lineasTextoErrores.add(line);
        }
        
        primeraValidacion(archivo);
        
        segundaValidacion(archivo);
        
        terceraValidacion(archivo);
        
        cuartaValidacion(archivo);
        
        quitaValidacion(archivo);
        
        sextaValidacion(archivo);
        
        septimaValidacion(archivo);
        
        octavaValidacion(archivo);
        
        novenaValidacion(archivo);
        
        decimaValidacion(archivo);
        
    }
    
    public void mostrarTokens(){
        System.out.println("TOTAL DE: "+contador+" TOKENS EN LA ASIGNACION.");
        System.out.println("Palabras Reservadas: " + tokens.getPalabrasReservadas());
        System.out.println("Identificadores: " + tokens.getIdentificadores());
        System.out.println("Operadores Relacionales: " + tokens.getOperadoresRelacionales());
        System.out.println("Operadores Aritmeticos: " + tokens.getOperadoresAritmeticos());
        System.out.println("Operadores Asignacion: " + tokens.getOperadoresAsignacion());
        System.out.println("Numeros: " + tokens.getNumeros());
        System.out.println("Operadores Incremento / Decremento: " + tokens.getOperadoresIncrementoDecremento());
        System.out.println("Parentesis: " + tokens.getParentesis());
        System.out.println("Llaves: " + tokens.getLlaves());
        System.out.println("Corchetes: " + tokens.getCorchetes());
        System.out.println("Punto Coma: " + tokens.getPuntoComa());
        System.out.println("Punto: " + tokens.getPunto());
    }
    
    private void primeraValidacion(Archivo archivo){
        
        int cantidad = 0;
        boolean tienePrimeraPalabra = false;
        boolean tieneSegundaPalabra = false;
        List<String> errores = new ArrayList<>();
        
        String patternString = "\\b(Module Program|End Module)\\b";
        
        Pattern pattern = Pattern.compile(patternString);
        
        for(String line : archivo.lineasTexto){
            
            Matcher matcher = pattern.matcher(line);
            
            while (matcher.find()) {
                
                if(line.equals("Module Program") && !tienePrimeraPalabra){
                    tienePrimeraPalabra = true;
                    cantidad++;
                }
                
                if(line.equals("End Module") && !tieneSegundaPalabra){
                    tieneSegundaPalabra = true;
                    cantidad++;
                }
            }
        }
        
        if(tienePrimeraPalabra && tieneSegundaPalabra && cantidad == 2){
            //Se escribe el texto sin errores
            archivo.lineasTextoRevisado = lineasTextoErrores;
            archivo.escribirArchivo();
        } else {
            
            if(tienePrimeraPalabra && !tieneSegundaPalabra){
                
                for(String line : lineasTextoErrores){
                    
                    errores.add(line);
                                       
                    if(line.equals("Module Program")){
                        errores.add(manejoErrores.getOneError(1));
                    } 
                }
                
            } else if(!tienePrimeraPalabra && tieneSegundaPalabra) {
                
                for(String line : lineasTextoErrores){
                    
                    errores.add(line);
                                        
                    if(line.equals("End Module")){
                        errores.add(manejoErrores.getOneError(2));
                    } 
                }
                
            }
            
            lineasTextoErrores = errores;
            
            //Se escribe el texto sin errores
            archivo.lineasTextoRevisado = lineasTextoErrores;
            archivo.escribirArchivo();
        }
    }
        
    private void segundaValidacion(Archivo archivo){
        
        int posicion = 0;
        List<Integer> lineasImport = new ArrayList<>();
        int lineasModuleProgram = 0;
        boolean esValido = true;
        boolean tienePrimeraPalabra = false;
        List<Integer> posicionesConError = new ArrayList<>();
        List<String> errores = new ArrayList<>();
        
        String patternString = "(Imports|Module Program)";
                
        Pattern pattern = Pattern.compile(patternString);
        
        for(String line : archivo.lineasTexto){
            
            posicion++;
            
            Matcher matcher = pattern.matcher(line);
            
               while (matcher.find()) {
                
                if(line.contains("Imports")){
                    lineasImport.add(posicion);
                }
                
                if(line.equals("Module Program")){
                    tienePrimeraPalabra = true;
                    lineasModuleProgram = posicion;
                }
                
            }
            
        }
        
        // Se evaluan los resultados
        if(tienePrimeraPalabra){
            
            for(int index : lineasImport){
                if(index > lineasModuleProgram) {
                    esValido = false;
                    posicionesConError.add(index);
                }
            }
        }

        if(esValido){
            //Se escribe el texto sin errores
            archivo.lineasTextoRevisado = lineasTextoErrores;
            archivo.escribirArchivo();
            
        } else {
                       
            for(String line : lineasTextoErrores){
                errores.add(line);
            }
            
            for(int index : posicionesConError){
                if(errores.get(index).contains("Imports")){
                    errores.add(index + 1, manejoErrores.getOneError(3));
                } else {
                    errores.add(index, manejoErrores.getOneError(3));
                }
            }
                        
            lineasTextoErrores = errores;
                
            //Se escribe el texto sin errores
            archivo.lineasTextoRevisado = lineasTextoErrores;
            archivo.escribirArchivo();
        }
    }
    
    private void terceraValidacion(Archivo archivo){
        
        int posicion = 0;
        int posicionRevision = 0;
        int lineasEndModule = 0;
        boolean esValido = true;
        boolean tienePalabra = false;
        List<Integer> posicionesConError = new ArrayList<>();
        List<String> errores = new ArrayList<>();
        
        String patternString = "\\b(End Module)\\b";
                
        Pattern pattern = Pattern.compile(patternString);
        
        for(String line : archivo.lineasTexto){
            
            posicion++;
            
            Matcher matcher = pattern.matcher(line);
            
               while (matcher.find()) {
                                
                if(line.equals("End Module")){
                    tienePalabra = true;
                    lineasEndModule = posicion;
                }
                
            }
            
        }
        
        // Se evaluan los resultados
        if(tienePalabra){
            
            for(String line : archivo.lineasTexto){
            
                posicionRevision++;

                if(posicionRevision > lineasEndModule){
                    if(!line.contains("'")){
                        esValido = false;
                        posicionesConError.add(posicionRevision);
                    }
                }
                
            }
        }

        if(esValido){
            //Se escribe el texto sin errores
            archivo.lineasTextoRevisado = lineasTextoErrores;
            archivo.escribirArchivo();
            
        } else {
                       
            for(String line : lineasTextoErrores){
                errores.add(line);
            }
            
            for(int index : posicionesConError){
                if(index >= errores.size()){
                    errores.add(manejoErrores.getOneError(4));
                } else {
                    if(!errores.get(index).contains("'")){
                        errores.add(index + 1, manejoErrores.getOneError(4));
                    } else {
                        errores.add(index, manejoErrores.getOneError(4));
                    }
                }          
            }
                        
            lineasTextoErrores = errores;
                
            //Se escribe el texto sin errores
            archivo.lineasTextoRevisado = lineasTextoErrores;
            archivo.escribirArchivo();
        }
    }
    
    private void cuartaValidacion(Archivo archivo){
        
        int posicion = 0;
        int lineaError = 0;
        boolean esValido = false;
        boolean tienePalabra = false;
        List<String> errores = new ArrayList<>();
        
        String patternString = "(Sub Main)";
        
        Pattern pattern = Pattern.compile(patternString);
        
        for(String line : archivo.lineasTexto){
            
            posicion++;
            
            Matcher matcher = pattern.matcher(line);
            
            while (matcher.find()) {
                                
                if(line.contains("Sub Main(") && line.substring(line.length() - 2).equals("))") && !tienePalabra ){
                    esValido = true;
                    tienePalabra = true;
                } else {
                    lineaError = posicion;
                }
                
            }
            
            if(tienePalabra){
                break;
            }
        }
        

        if(esValido){
            //Se escribe el texto sin errores
            archivo.lineasTextoRevisado = lineasTextoErrores;
            archivo.escribirArchivo();
            
        } else {
                       
            for(String line : lineasTextoErrores){
                errores.add(line);
            }
            
            errores.add(lineaError, manejoErrores.getOneError(5));                      
                                    
            lineasTextoErrores = errores;
                
            //Se escribe el texto sin errores
            archivo.lineasTextoRevisado = lineasTextoErrores;
            archivo.escribirArchivo();
        }
    }
    
    private void quitaValidacion(Archivo archivo){
        
        int posicion = 0;
        int cantidad = 0;
        boolean tienePrimeraPalabra = false;
        boolean tieneSegundaPalabra = false;
        int lineaSubMain = 0;
        int lineaEndSub = 0;
        List<String> errores = new ArrayList<>();
        
        String patternString = "(Sub Main|End Sub)";
        
        Pattern pattern = Pattern.compile(patternString);
        
        for(String line : archivo.lineasTexto){
            
            posicion++;
            
            Matcher matcher = pattern.matcher(line);
            
            while (matcher.find()) {
                                
                if(line.contains("Sub Main(") && line.substring(line.length() - 2).equals("))") && !tienePrimeraPalabra ){
                    tienePrimeraPalabra = true;
                    cantidad++;
                    lineaSubMain = posicion;
                } else if(line.contains("End Sub") && !tieneSegundaPalabra){
                    tieneSegundaPalabra = true;
                    cantidad++;
                    lineaEndSub = posicion;
                }
                
            }
            
        }
        

        if(tienePrimeraPalabra && tieneSegundaPalabra && cantidad == 2){
            //Se escribe el texto sin errores
            archivo.lineasTextoRevisado = lineasTextoErrores;
            archivo.escribirArchivo();
            
        } else {
                       
            for(String line : lineasTextoErrores){
                errores.add(line);
            }
            
            if(tienePrimeraPalabra && !tieneSegundaPalabra){
                if(errores.get(lineaSubMain).contains("Sub Main(")){
                    errores.add(lineaSubMain + 1, manejoErrores.getOneError(7));
                } else {
                    errores.add(lineaSubMain, manejoErrores.getOneError(7));
                }
            } else if(!tienePrimeraPalabra && tieneSegundaPalabra) {
                if(errores.get(lineaEndSub).contains("End Sub")){
                    errores.add(lineaEndSub + 1, manejoErrores.getOneError(6));
                } else {
                    errores.add(lineaEndSub, manejoErrores.getOneError(6));
                }
            }
                                            
            lineasTextoErrores = errores;
                
            //Se escribe el texto sin errores
            archivo.lineasTextoRevisado = lineasTextoErrores;
            archivo.escribirArchivo();
        }
    }
    
    private void sextaValidacion(Archivo archivo){
        
        int cantidadComentarios = 0;
        List<String> comentarios = new ArrayList<>();
        List<String> errores = new ArrayList<>();
        
        for(String line : archivo.lineasTexto){
                        
            if(line.contains("'")){
                comentarios.add(line);
                cantidadComentarios++;
            }
        }
                               
        for(String line : lineasTextoErrores){
            errores.add(line);
        }
        
        errores.add("");
        errores.add("Cantidad de comentarios: " + cantidadComentarios);
        errores.add("Comentarios");

        for(String comentario : comentarios){
            errores.add(comentario.replaceFirst("^ *", ""));
        }

        lineasTextoErrores = errores;

        //Se escribe el texto sin errores
        archivo.lineasTextoRevisado = lineasTextoErrores;
        archivo.escribirArchivo();
    }
    
    private void septimaValidacion(Archivo archivo){
        
        int posicion = 0;
        List<Integer> posicionesConError = new ArrayList<>();
        List<String> errores = new ArrayList<>();
        List<String> lineasError = new ArrayList<>();
        
        for(String line : archivo.lineasTexto){
            
            posicion++;
            
            if(line.length() > 90){
                posicionesConError.add(posicion);
                lineasError.add(line);
            }
        }
                               
        for(String line : lineasTextoErrores){
            errores.add(line);
        }
        
        for(int index : posicionesConError){
            errores.add(index, manejoErrores.getOneError(8));
        }

        lineasTextoErrores = errores;

        //Se escribe el texto sin errores
        archivo.lineasTextoRevisado = lineasTextoErrores;
        archivo.escribirArchivo();
    }
    
    private void octavaValidacion(Archivo archivo){
        
        int posicion = 0;
        int cantidad = 0;
        boolean tienePrimeraPalabra = false;
        boolean tieneSegundaPalabra = false;
        int lineaWhile = 0;
        int lineaEndWhile = 0;
        List<String> errores = new ArrayList<>();
        List<String> lineasEntreWhile = new ArrayList<>();
        
        String patternString = "(While|End While)";
        
        Pattern pattern = Pattern.compile(patternString);
        
        // Se sacan las palabras While
        for(String line : archivo.lineasTexto){
            
            posicion++;
            
            Matcher matcher = pattern.matcher(line);
            
            while (matcher.find()) {
                                
                if(line.contains("End While") && !tieneSegundaPalabra ){
                    tieneSegundaPalabra = true;
                    cantidad++;
                    lineaEndWhile = posicion;
                } else if(line.contains("While") && !tienePrimeraPalabra){
                    tienePrimeraPalabra = true;
                    cantidad++;
                    lineaWhile = posicion;
                }
                
            }
            
        }
                
        if(tienePrimeraPalabra && tieneSegundaPalabra && cantidad == 2) {
            
            for (int i = lineaWhile + 1; i < lineaEndWhile; i++) {
                if(archivo.lineasTexto.get(i) != null){
                    lineasEntreWhile.add(archivo.lineasTexto.get(i));
                }
            }
            
            if( lineasEntreWhile.size() > 0 ){
                //Se escribe el texto sin errores
                archivo.lineasTextoRevisado = lineasTextoErrores;
                archivo.escribirArchivo();
            } else {
                
                for(String line : lineasTextoErrores){
                    errores.add(line);
                }

                if(errores.get(lineaWhile).contains("While")){
                    errores.add(lineaWhile + 1, manejoErrores.getOneError(11));
                } else {
                    errores.add(lineaWhile, manejoErrores.getOneError(11));
                }

                lineasTextoErrores = errores;

                //Se escribe el texto sin errores
                archivo.lineasTextoRevisado = lineasTextoErrores;
                archivo.escribirArchivo();
            }
            
        } else {
            
            for(String line : lineasTextoErrores){
                errores.add(line);
            }

            if(tienePrimeraPalabra && !tieneSegundaPalabra){
                if(errores.get(lineaWhile).contains("While")){
                    errores.add(lineaWhile + 1, manejoErrores.getOneError(10));
                } else {
                    errores.add(lineaWhile, manejoErrores.getOneError(10));
                }
            } else if(!tienePrimeraPalabra && tieneSegundaPalabra) {
                if(errores.get(lineaEndWhile).contains("End While")){
                    errores.add(lineaEndWhile + 1, manejoErrores.getOneError(9));
                } else {
                    errores.add(lineaEndWhile, manejoErrores.getOneError(9));
                }
            }

            lineasTextoErrores = errores;

            //Se escribe el texto sin errores
            archivo.lineasTextoRevisado = lineasTextoErrores;
            archivo.escribirArchivo();
            
        }

    }
    
    private void novenaValidacion(Archivo archivo){
        
        int posicion = 0;
        int cantidad = 0;
        boolean tienePalabraTry = false;
        boolean tienePalabraCatch = false;
        boolean tienePalabraEndTry = false;
        int lineaPalabraTry = 0;
        int lineaPalabraCatch = 0;
        int lineaPalabraEndTry = 0;
        List<String> errores = new ArrayList<>();
        List<String> lineasEntreTry = new ArrayList<>();
        List<String> lineasEntreEndTry = new ArrayList<>();
        
        String patternString = "(Try|Catch|End Try)";
        
        Pattern pattern = Pattern.compile(patternString);
        
        // Se sacan las palabras Try
        for(String line : archivo.lineasTexto){
            
            posicion++;
            
            Matcher matcher = pattern.matcher(line);
            
            while (matcher.find()) {
                                
                if(line.contains("Try") && !tienePalabraTry ){
                    tienePalabraTry = true;
                    cantidad++;
                    lineaPalabraTry = posicion;
                } else if(line.contains("Catch") && line.contains("As") && line.contains("Exception") && !tienePalabraCatch){
                    tienePalabraCatch = true;
                    cantidad++;
                    lineaPalabraCatch = posicion;
                } else if(line.contains("End Try") && !tienePalabraEndTry){
                    tienePalabraEndTry = true;
                    cantidad++;
                    lineaPalabraEndTry = posicion;
                }
                
            }
            
        }
                
        if(tienePalabraTry && tienePalabraCatch && tienePalabraEndTry && cantidad == 3) {
            
            for (int i = lineaPalabraTry + 1; i < lineaPalabraCatch; i++) {
                if(archivo.lineasTexto.get(i) != null){
                    lineasEntreTry.add(archivo.lineasTexto.get(i));
                }
            }
            
            for (int i = lineaPalabraCatch + 1; i < lineaPalabraEndTry; i++) {
                if(archivo.lineasTexto.get(i) != null){
                    lineasEntreEndTry.add(archivo.lineasTexto.get(i));
                }
            }
            
            if( lineasEntreTry.size() > 0 && lineasEntreEndTry.size() > 0){
                //Se escribe el texto sin errores
                archivo.lineasTextoRevisado = lineasTextoErrores;
                archivo.escribirArchivo();
            } else {
                
                for(String line : lineasTextoErrores){
                    errores.add(line);
                }
                
                if( lineasEntreTry.size() == 0) {
                    if(errores.get(lineaPalabraTry).contains("Try")){
                        errores.add(lineaPalabraTry + 1, manejoErrores.getOneError(15));
                    } else {
                        errores.add(lineaPalabraTry, manejoErrores.getOneError(15));
                    }
                } 
                
                if( lineasEntreEndTry.size() == 0) {
                    if(errores.get(lineaPalabraCatch).contains("Catch")){
                        errores.add(lineaPalabraCatch + 1, manejoErrores.getOneError(16));
                    } else {
                        errores.add(lineaPalabraCatch, manejoErrores.getOneError(16));
                    }
                }

                lineasTextoErrores = errores;

                //Se escribe el texto sin errores
                archivo.lineasTextoRevisado = lineasTextoErrores;
                archivo.escribirArchivo();
            }
            
        } else {
            
            for(String line : lineasTextoErrores){
                errores.add(line);
            }

            if(tienePalabraTry && !tienePalabraCatch){
                if(errores.get(lineaPalabraTry).contains("Try")){
                    errores.add(lineaPalabraTry + 1, manejoErrores.getOneError(13));
                } else {
                    errores.add(lineaPalabraTry, manejoErrores.getOneError(13));
                }
            } else if(!tienePalabraCatch && tienePalabraTry) {
                if(errores.get(lineaPalabraCatch).contains("Catch")){
                    errores.add(lineaPalabraCatch + 1, manejoErrores.getOneError(12));
                } else {
                    errores.add(lineaPalabraCatch, manejoErrores.getOneError(12));
                }
            } else if(tienePalabraCatch && !tienePalabraEndTry) {
                if(errores.get(lineaPalabraCatch).contains("Catch")){
                    errores.add(lineaPalabraCatch + 1, manejoErrores.getOneError(14));
                } else {
                    errores.add(lineaPalabraCatch, manejoErrores.getOneError(14));
                }
            } else if(tienePalabraEndTry && !tienePalabraCatch) {
                if(errores.get(lineaPalabraCatch).contains("End Try")){
                    errores.add(lineaPalabraCatch + 1, manejoErrores.getOneError(13));
                } else {
                    errores.add(lineaPalabraCatch, manejoErrores.getOneError(13));
                }
            }

            lineasTextoErrores = errores;

            //Se escribe el texto sin errores
            archivo.lineasTextoRevisado = lineasTextoErrores;
            archivo.escribirArchivo();
            
        }

    }
    
    private void decimaValidacion(Archivo archivo){
        
        int cantidadIdentificadores = 0;
        List<String> identificadores = new ArrayList<>();
        List<String> identificadoresSinValidar = new ArrayList<>();
        List<String> errores = new ArrayList<>();
        
        for(String line : archivo.lineasTexto){
                        
            if(line.contains("Dim") && line.contains("As")){
                identificadoresSinValidar.add(line);
            }
        }
        
        for(String identificador : identificadoresSinValidar){
            
            String[] words = identificador.split("\\W+");
            if(words[1].equals("Dim") && words[3].equals("As")){
                identificadores.add(identificador);
                cantidadIdentificadores++;
            }
        }
                               
        for(String line : lineasTextoErrores){
            errores.add(line);
        }
        
        errores.add("");
        errores.add("Cantidad de Identificadores: " + cantidadIdentificadores);
        errores.add("Identificadores");

        for(String identificador : identificadores){
            errores.add(identificador.replaceFirst("^ *", ""));
        }

        lineasTextoErrores = errores;

        //Se escribe el texto sin errores
        archivo.lineasTextoRevisado = lineasTextoErrores;
        archivo.escribirArchivo();
    }
}
