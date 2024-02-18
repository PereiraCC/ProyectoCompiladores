/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiladores;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pereiracc
 */
public class Tokens {
    
    private List<String> _palabrasReservadas;
    
    private List<String> _identificadores;
    
    private List<String> _operadoresRelacionales;
    
    private List<String> _operadoresAritmeticos;
    
    private List<String> _operadoresAsignacion;
    
    private List<String> _numeros;
    
    private List<String> _operadoresIncrementoDecremento;
    
    private List<String> _parentesis;
    
    private List<String> _llaves;
    
    private List<String> _corchetes;
    
    private List<String> _puntoComa;
    
    private List<String> _punto;
    
    public Tokens() {
        
        this._palabrasReservadas = new ArrayList<>();
        this._identificadores = new ArrayList<>();
        this._operadoresRelacionales = new ArrayList<>();
        this._operadoresAritmeticos = new ArrayList<>();
        this._operadoresAsignacion = new ArrayList<>();
        this._numeros = new ArrayList<>();
        this._operadoresIncrementoDecremento = new ArrayList<>();
        this._parentesis = new ArrayList<>();
        this._llaves = new ArrayList<>();
        this._corchetes = new ArrayList<>();
        this._puntoComa = new ArrayList<>();
        this._punto = new ArrayList<>();        
    }

    public List<String> getPalabrasReservadas() {
        return _palabrasReservadas;
    }

    public void setPalabrasReservadas(String palabrasReservada) {
        this._palabrasReservadas.add(palabrasReservada);
    }

    public List<String> getIdentificadores() {
        return _identificadores;
    }

    public void setIdentificadores(String identificador) {
        this._identificadores.add(identificador);
    }

    public List<String> getOperadoresRelacionales() {
        return _operadoresRelacionales;
    }

    public void setOperadoresRelacionales(String operadoresRelacional) {
        this._operadoresRelacionales.add(operadoresRelacional);
    }

    public List<String> getOperadoresAritmeticos() {
        return _operadoresAritmeticos;
    }

    public void setOperadoresAritmeticos(String operadoresAritmetico) {
        this._operadoresAritmeticos.add(operadoresAritmetico);
    }

    public List<String> getOperadoresAsignacion() {
        return _operadoresAsignacion;
    }

    public void setOperadoresAsignacion(String operadoresAsignacion) {
        this._operadoresAsignacion.add(operadoresAsignacion);
    }

    public List<String> getNumeros() {
        return _numeros;
    }

    public void setNumeros(String numero) {
        this._numeros.add(numero);
    }

    public List<String> getOperadoresIncrementoDecremento() {
        return _operadoresIncrementoDecremento;
    }

    public void setOperadoresIncrementoDecremento(String operadoresIncrementoDecremento) {
        this._operadoresIncrementoDecremento.add(operadoresIncrementoDecremento);
    }

    public List<String> getParentesis() {
        return _parentesis;
    }

    public void setParentesis(String parentesis) {
        this._parentesis.add(parentesis);
    }

    public List<String> getLlaves() {
        return _llaves;
    }

    public void setLlaves(String llave) {
        this._llaves.add(llave);
    }

    public List<String> getCorchetes() {
        return _corchetes;
    }

    public void setCorchetes(String corchete) {
        this._corchetes.add(corchete);
    }

    public List<String> getPuntoComa() {
        return _puntoComa;
    }

    public void setPuntoComa(String puntoComa) {
        this._puntoComa.add(puntoComa);
    }

    public List<String> getPunto() {
        return _punto;
    }

    public void setPunto(String punto) {
        this._punto.add(punto);
    }

}
