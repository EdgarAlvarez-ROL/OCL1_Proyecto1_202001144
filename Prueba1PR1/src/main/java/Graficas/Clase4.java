/*
    Organizacion de Lenguajes y Compiladores 1 "C"
    Clase 4
    Método del Árbol
 */
package Graficas;

import java.util.ArrayList;

public class Clase4{

    
    //public static void main(String[] args) throws Exception {
      public Clase4(String er)throws Exception  {
        /*
            Pasos del metodo del arbol:
            1. Aumentar la ER con el simbolo de aceptacion # y su concatenación
            2. Construir el arbol
            3. Numerar cada hoja
            4. Encontrar los anulables
            5. Tabla de primeros y ultimos
            6. Tabla de siguientes
            7. Tabla de transiciones, tabla de estados y diagrama de estados
        */
        
        // La ER estara en prefijo
        // a . b -> . a b 
        // se aceptara el . | *
        
        //String er = "...ab*b*|ba"; //abb*(a|b)*
        //String er = "...*|ababb"; //En esta er ocurre el bug
        //String er = ".L*|_|LD"; // . {letra} * | \"_\" | {letra} {digito}
        //String er = ".+d.p+d"; // .+{DIGITO}."."+{DIGITO}
        //String er = ". \\' . + | | | | \\n {minus} {mayus} {digito} \" \" \\'";
        
        ArrayList<node> leaves = new ArrayList();
        ArrayList<ArrayList> table = new ArrayList();
        //er = "." + er + "#";
        
        
        modificarExp puta = new modificarExp(er);
        
        Tree1 arbol = new Tree1(puta, leaves, table); // CREA EL ARBOL
        node raiz = arbol.getRoot();

        raiz.getNode(); // DETERMINA SI LOS NODOS SON ANULABLES, SUS PRIMEROS Y ULTIMOS
        raiz.follow();
        
        
        System.out.println("==============================TABLA SIGUIENTES==============================");
        followTable ft = new followTable();
        ft.printTable(table);
        transitionTable tran = new transitionTable(raiz, table, leaves); // bug
        System.out.println("=============================TABLA TRANSICIONES=============================");
        tran.impTable();
        System.out.println("============================= GRAPHVIZ===============================================");
        tran.impGraph();
        
        
    }

   
}
