/*
    Organizacion de Lenguajes y Compiladores 1 "C"
    Clase 4
    Método del Árbol
 */
package Graficas;


public class transicion {

    public String initialState;
    public String transition;
    public String finalState;
    
    public transicion( String initialState, String transition, String finalState ) {
        this.initialState = initialState;
        this.transition = transition;
        this.finalState = finalState;
    }
    
    public boolean compare(String initialState, String transition){
        
        return this.initialState.equals(initialState) && this.transition.equals(transition);
    }
    
    @Override
    public String toString(){
        return this.initialState + " -> " + this.transition + " -> " + this.finalState;
    }
    
    public String graph(){
        return this.initialState +  "->"  + this.finalState + "[label=\"" + this.transition + "\"]";
    }
    
    public String finalCirclegraph(){
        return this.initialState +  "->"  + this.finalState + "[label=\"" + this.transition + "\"" + "shape=doublecircle" +"]";
    }
    
    public String getTransiciones(){
        return this.transition;
    }
    
    public String getInitialState(){
        return this.transition;
    }
    
    public String getFinalState(){
        return this.finalState;
    }
    
    // PARA LOS AFND
    public String getEtrans(){
        return this.initialState + " -> " + "e" + this.finalState;
    }
}
