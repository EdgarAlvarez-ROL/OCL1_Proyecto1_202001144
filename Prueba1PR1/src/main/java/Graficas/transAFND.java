/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graficas;

import java.util.ArrayList;

/**
 *
 * @author wwwed
 */
public class transAFND {
    public ArrayList<ArrayList> states;
    public int cont;
    public int contador;
    
    public transAFND(node root, ArrayList tabla, ArrayList<node> leaves){
        this.states = new ArrayList();
        
        /*
        System.out.println("LOS SIGUIENTES");
        for (int pala = 0;pala<tabla.size();pala++){
            System.out.println(tabla.get(pala));
        }
        */
        
        
        ArrayList datos = new ArrayList();
        datos.add("S0");
        datos.add(root.first);
        datos.add(new ArrayList());
        datos.add(false);
        datos.add("");
        
        this.states.add(datos);
        this.cont = 0;
        
        for(int i = 0; i < states.size(); i++){
            ArrayList state = states.get(i);
            ArrayList<Integer> elementos = (ArrayList) state.get(1);
            
            // TODO  Aqui se encuentra el bug sobre las transiciones
            // Sucede cuando existe dos transisiciones con el mismo simbolos y diferentes siguientes
            // Ej: S0 ={1,2,3}  se ve la tabla de siguiente y se tiene  (S0,a) = {1,2,3} y (S0,a) = {4} el nuevo estado quedaria de la union de estos dos S1 = {1,2,3,4}
            // Lo que hace ahora es remplazar el estado final entonces queda asi S1 = {4} y no S1 = {1,2,3,4}
            
            for(int hoja : elementos){
                followTable sigTabla = new followTable();
                ArrayList lexemeNext = (ArrayList) sigTabla.nextAFND(hoja, tabla).clone();
                //lexemeNext.add(null);
                /*
                * En 0 es el orden del AFD normal d d . d d
                * En 1 son los siguientes [-2 , 0] o [-2, 4] y asi
                * En 2 esta ahora si el PKLEENE
                */
                System.out.println("LexemeNet en 2");
                System.out.println(lexemeNext.get(2));
                
                //System.out.println(states.size());
                
                boolean existe = false;
                String found = "";
                
                for( ArrayList e : states ){
                    if(e.get(1).equals(lexemeNext.get(1))){
                        existe = true;
                        found = (String)e.get(0);
                        break;
                    }
                }
                
                if(lexemeNext.size() == contador){
                    break;
                }
                
                if(!existe){
                    leave hojas = new leave();
                    if(hojas.isAccept(hoja, leaves)){
                        state.set(3, true);
                    }
                    if(lexemeNext.get(0)==""){
                        continue;
                    }
                    
                    ArrayList nuevo = new ArrayList();
                    nuevo.add("S"+cont);
                    nuevo.add(lexemeNext.get(1));
                    nuevo.add(new ArrayList());
                    nuevo.add(false);
                    
                    transicion trans = new transicion(state.get(0) + "", lexemeNext.get(0) + "", nuevo.get(0) + "");
                    ((ArrayList)state.get(2)).add(trans);
                    
                    cont += 1;
                    states.add(nuevo);
                    contador += 1;
                
                }
                
           
            }
            
            
            
        }
    }
 
    
    public void impTable(){
        for(ArrayList state : states){
            String tran = "[";
            for(Object tr : (ArrayList)state.get(2)){
                transicion t = (transicion) tr;
                tran += t.toString() + ", ";           
            }
            tran += "]";
            tran = tran.replace(", ]", "]");
            System.out.println(state.get(0) + " " + tran + " " + state.get(3) + " " + state.get(4));
        }
    }
   
}
