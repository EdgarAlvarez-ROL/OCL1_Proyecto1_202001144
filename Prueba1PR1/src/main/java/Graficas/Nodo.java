package Graficas;


import java.util.ArrayList;


public class Nodo {
    
    public String token;
    public String lexema;
    public int id; 
    public Nodo hijoIzq;
    public Nodo hijoDer;
    
    public ArrayList<Nodo> hijos = new ArrayList<Nodo>();
    
    public Nodo(String token, String lexema, int id, Nodo hijoIzq, Nodo hijoDer){
        this.token = token;
        this.lexema = lexema;
        this.hijoIzq = hijoIzq;
        this.hijoDer = hijoDer;
        
        if(hijoIzq != null){
        this.hijos.add(hijoIzq);
        }
        if(hijoDer != null){
            this.hijos.add(hijoDer);
        }
        
    }
    
    
    
}
