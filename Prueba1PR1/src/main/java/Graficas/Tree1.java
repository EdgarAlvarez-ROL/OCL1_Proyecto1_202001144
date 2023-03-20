/*
    Organizacion de Lenguajes y Compiladores 1 "C"
    Clase 4
    Método del Árbol
 */
package Graficas;

import Graficas.type.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

//para el escritor



public class Tree1 {
    
    node root;
    
    Nodo raiz;

    public Tree1( modificarExp puta, ArrayList<node> leaves, ArrayList<ArrayList> table, String contadorcua ) {
        
        numLeave1 numHoja = new numLeave1(puta.getPuta());
        
        
        Stack pila = new Stack();
        
        Stack pila2 = new Stack();
     
        //String[] erSplit = er.split("");
        //ArrayList<String> strList = new ArrayList<String>(".", ".", "{Letra}", "*", "|", "_", "|", "{Letra}", "{Digito}", "#");
        
        ArrayList<String> strList = puta.getPuta();
        Collections.reverse(strList);
        
        
        Escritor cua = new Escritor();
        strList.forEach((character) -> {
       
            switch (character) {
                case "|":
                    node lefto = (node) pila.pop();
                    node righto = (node) pila.pop();
                      
                    node no = new node(character, Types.OR, 0, lefto, righto, leaves, table);
                    pila.push(no);
                    
                    //System.out.println(no.lexeme);
                    //cua.escribir("PADRE" + no.lexeme);
                    //cua.escribir(lefto.lexeme);
                    //cua.escribir(righto.lexeme);
                    Nodo lef_to = (Nodo) pila2.pop();
                    Nodo righ_to = (Nodo) pila2.pop();
                    
                    Nodo n_o = new Nodo(character,"",-1,lef_to,righ_to);
                    pila2.push(n_o);
                    
                    break;
                case ".":
                    node lefta = (node) pila.pop();
                    node righta = (node) pila.pop();
                    
                    node na = new node(character, Types.AND, 0, lefta, righta, leaves, table);
                    pila.push(na);
                    
                    //System.out.println(righta.lexeme);
                    //cua.escribir("PADRE" + na.lexeme);
                    //cua.escribir(lefta.lexeme);
                    //cua.escribir(righta.lexeme);
                    Nodo left_a = (Nodo) pila2.pop();
                    Nodo right_a = (Nodo) pila2.pop();
                    
                    Nodo n_a = new Nodo(character,"",-1,left_a,right_a);
                    pila2.push(n_a);
                    
                    
                    break;
                case "*":
                    node unario = (node) pila.pop();
                    
                    node nk = new node(character, Types.KLEENE, 0, unario, null, leaves, table);
                    pila.push(nk);
                    
                    //System.out.println(unario.lexeme);
                    //cua.escribir("PADRE" + nk.lexeme);
                    //cua.escribir(unario.lexeme);
                    Nodo unario_0 = (Nodo) pila2.pop();
                    Nodo n_k = new Nodo(character,"",-1,unario_0,null);
                    pila2.push(n_k);
                    
                    break;
                case "+":
                    node unario2 = (node) pila.pop();
                    
                    node nk2 = new node(character, Types.PKLEENE, 0, unario2, null, leaves, table);
                    pila.push(nk2);
                    
                    //System.out.println(nk2.lexeme);
                    //cua.escribir("PADRE" + nk2.lexeme);
                    //cua.escribir(unario2.lexeme);
                    Nodo unario_2 = (Nodo) pila2.pop();
                    Nodo n_k2 = new Nodo(character,"",-1,unario_2,null);
                    pila2.push(n_k2);
                    
                    break;
                case "?":
                    node unario3 = (node) pila.pop();
                    
                    node nk3 = new node(character, Types.QUESTION, 0, unario3, null, leaves, table);
                    pila.push(nk3);
                    
                    //System.out.println(nk3.lexeme);
                    //cua.escribir("PADRE" + nk3.lexeme);
                    //cua.escribir(unario3.lexeme);
                    Nodo unario_3 = (Nodo) pila2.pop();
                    Nodo n_k3 = new Nodo(character,"",-1,unario_3,null);
                    pila2.push(n_k3);
                    
                    
                    break;
                case " ":
                    break;
                default:
                    if ("\\\"".equals(character)){
                        character = "comilla";
                    }else if("\" \"".equals(character)){
                        character = "espacio";
                    }else if("\\\n".equals(character)){
                        character = "enter";
                    }
                    
                    node nd = new node(character.replaceAll("\"", ""), Types.HOJA, numHoja.getNum(), null, null, leaves, table);
                    pila.push(nd); //Contruir el arbol
                    leave hoja = new leave();
                    hoja.addLeave(nd, leaves); //Tabla de siguientes o transiciones
                    
                    //System.out.println(nd.lexeme);
                    Nodo n_d = new Nodo(character,"",numHoja.getNum(),null,null);
                    pila2.push(n_d);
                    
                    
                    
                    break;
            }
            
        });
        
        this.raiz = (Nodo) pila2.pop();
        //System.out.println(raiz.hijos.get(1).token);
        Arbol arbolito = new Arbol(raiz);
        arbolito.GraficarSintactico(contadorcua);
        
        this.root = (node) pila.pop();
        
    }
    
    public node getRoot(){
        return this.root;
    }
    
    public Nodo getRoot2(){
        return this.raiz;
    }
    
 
}
