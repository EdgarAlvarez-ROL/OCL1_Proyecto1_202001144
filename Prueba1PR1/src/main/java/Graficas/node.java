/*
    Organizacion de Lenguajes y Compiladores 1 "C"
    Clase 4
    Método del Árbol
 */
package Graficas;

import java.util.ArrayList;
import Graficas.type.Types;

public class node {

    ArrayList<Integer> first;
    ArrayList<Integer> last;
    boolean anullable;
    
    String lexeme;
    Types type;
    int number;
    
    boolean accept;
    
    Object left;
    Object right;
    
    ArrayList<node> leaves;
    ArrayList<ArrayList> table;
    
    public node(String lexeme, Types type, int number, Object left, Object right, ArrayList<node> leaves, ArrayList<ArrayList> table) {
        first = new ArrayList();
        last = new ArrayList();
        anullable = true;
        
        this.lexeme = lexeme;
        this.type = type;
        this.number = number;
        
        accept = "#".equals(this.lexeme);
        
        this.left = left;
        this.right = right;
        
        this.leaves = leaves;
        this.table = table;
    }
    
    public node getNode(){
        
        Object leftNode =  this.left instanceof node ? ((node) this.left).getNode(): null;
        Object rightNode = this.right instanceof node ? ((node) this.right).getNode(): null;
        
        
        if(null != this.type)switch (this.type) {
            case HOJA:
                this.anullable = false;
                this.first.add(this.number);
                this.last.add(this.number);
                break;
            case AND:
                if(leftNode instanceof node && rightNode instanceof node){
                    this.anullable = ((node)leftNode).anullable && ((node) rightNode).anullable;
                    
                    this.first.addAll(((node)leftNode).first);
                    if(((node)leftNode).anullable){
                        this.first.addAll(((node)rightNode).first);
                    }
                    
                    if(((node)rightNode).anullable){
                        this.last.addAll(((node)leftNode).last);
                    }
                    this.last.addAll(((node)rightNode).last);
                }   
                break;
            case OR:
                if(leftNode instanceof node && rightNode instanceof node){
                    this.anullable = ((node)leftNode).anullable || ((node) rightNode).anullable;
                    
                    this.first.addAll(((node)leftNode).first);
                    this.first.addAll(((node)rightNode).first);
                    
                    
                    this.last.addAll(((node)leftNode).last);
                    this.last.addAll(((node)rightNode).last);
                    
                    
                }  
                break;
            case KLEENE:
                if(leftNode instanceof node){
                    this.anullable = true;
                    this.first.addAll(((node)leftNode).first);
                    this.last.addAll(((node)leftNode).last);
                }
                break;
            case PKLEENE:
                if(leftNode instanceof node){
                    this.anullable = ((node)leftNode).anullable;
                    this.first.addAll(((node)leftNode).first);
                    this.last.addAll(((node)leftNode).last);
                }
                break;
            case QUESTION:
                if(leftNode instanceof node){
                    this.anullable = true;
                    this.first.addAll(((node)leftNode).first);
                    this.last.addAll(((node)leftNode).last);
                }
                break;
            default:
                break;
        }
        return this;
    }
    
    public Object follow(){
        Object leftFollow=  this.left instanceof node ? ((node) this.left).follow() : null;
        Object rightFollow =  this.right instanceof node ? ((node) this.right).follow() : null;
        
        if(null != this.type)switch (this.type) {
            case AND:
                for (int item : ((node)leftFollow).last) {
                    leave hoja = new leave();
                    node nodo = hoja.getLeave(item, leaves);
                    followTable tabla = new followTable();
                    tabla.append(nodo.number, nodo.lexeme, ((node) rightFollow).first, table,"AND");
                }
                break;
            case KLEENE:
                for (int item : ((node)leftFollow).last) {
                    leave hoja = new leave();
                    node nodo = hoja.getLeave(item, leaves);
                    followTable tabla = new followTable();
                    tabla.append(nodo.number, nodo.lexeme, ((node) leftFollow).first, table, "KLEENE");
                }
                break;
            case PKLEENE:
                for (int item : ((node)leftFollow).last) {
                    leave hoja = new leave();
                    node nodo = hoja.getLeave(item, leaves);
                    followTable tabla = new followTable();
                    tabla.append(nodo.number, nodo.lexeme, ((node) leftFollow).first, table, "PKLEENE");
                }
                break;
            case QUESTION:
                for (int item : ((node)leftFollow).last) {
                    leave hoja = new leave();
                    node nodo = hoja.getLeave(item, leaves);
                    followTable tabla = new followTable();
                    tabla.append(nodo.number, nodo.lexeme, ((node) leftFollow).first, table, "QUESTION");
                }
                break;
            default:
                break;
        }
        
        return this;
    }
    
    
    public Object followAFND(){
        Object leftFollowAFND  =  this.left instanceof node ? ((node) this.left).followAFND() : null;
        Object rightFollowAFND =  this.right instanceof node ? ((node) this.right).followAFND() : null;
        
        if(null != this.type)switch (this.type) {
            case AND:
                for (int item : ((node)leftFollowAFND).last) {
                    leave hoja = new leave();
                    node nodo = hoja.getLeave(item, leaves);
                    followTable tabla = new followTable();
                    tabla.append(nodo.number, nodo.lexeme, ((node) rightFollowAFND).first, table,"AND");
                }
                break;
            case OR:
                //System.out.println("============ ENTRAMOS EN UN OR ============");
                for (int item : ((node)leftFollowAFND).last) {
                    leave hoja = new leave();
                    node nodo = hoja.getLeave(item, leaves);
                    followTable tabla = new followTable();
                    tabla.append(nodo.number, nodo.lexeme, ((node) rightFollowAFND).first, table,"OR");
                }
                break; 
            case KLEENE:
                for (int item : ((node)leftFollowAFND).last) {
                    leave hoja = new leave();
                    node nodo = hoja.getLeave(item, leaves);
                    followTable tabla = new followTable();
                    tabla.append(nodo.number, nodo.lexeme, ((node) leftFollowAFND).first, table, "KLEENE");
                }
                break;
            case PKLEENE:
                 for (int item : ((node)leftFollowAFND).last) {
                    leave hoja = new leave();
                    node nodo = hoja.getLeave(item, leaves);
                    followTable tabla = new followTable();
                    tabla.append(nodo.number, nodo.lexeme, ((node) leftFollowAFND).first, table, "PKLEENE");
                }
                break;
            case QUESTION:
                for (int item : ((node)leftFollowAFND).last) {
                    leave hoja = new leave();
                    node nodo = hoja.getLeave(item, leaves);
                    followTable tabla = new followTable();
                    tabla.append(nodo.number, nodo.lexeme, ((node) leftFollowAFND).first, table, "QUESTION");
                }
                break;
            default:
                break;
        }
        
        return this;
    }
    
    
}

