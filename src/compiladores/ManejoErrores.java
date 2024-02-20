/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiladores;

import java.util.Hashtable;

/**
 *
 * @author pereiracc
 */
public class ManejoErrores {
    
    public Hashtable<Integer, String> errores = new Hashtable<>();
    
    public ManejoErrores(){
        errores.put(1, "01. Error: Falta el componente End Module o esta mal escrito.");
        errores.put(2, "01. Error: Falta el componente Module Program o esta mal escrito.");
        errores.put(3, "02. Error: Los imports deben estar antes del Module Program");
        errores.put(4, "03. Error: Después del End Module no deberían haber comandos a excepción de los comentarios.");
        errores.put(5, "04. Error: El camando Sub Main no concluye en la misma linea.");
        errores.put(6, "05. Error: Falta el componente Sub Main o esta mal escrito.");
        errores.put(7, "06. Error: Falta el componente End Sub o esta mal escrito.");
        errores.put(8, "07. Error: La linea supera los 90 caracteres.");
        errores.put(9, "08. Error: Falta el componente While o esta mal escrito.");
        errores.put(10, "09. Error: Falta el componente End While o esta mal escrito.");
        errores.put(11, "10. Error: El componente While no tiene un identificador.");
    }
    
    public String getOneError(int idError){
        return errores.get(idError);
    }

}
