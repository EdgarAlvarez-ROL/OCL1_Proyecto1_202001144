/*
    Organizacion de Lenguajes y Compiladores 1 "C"
    Clase 4
    Método del Árbol
 */
package Graficas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class transitionTable {

    public ArrayList<ArrayList> states;
    public int cont;
    
    public ArrayList<String> transiciones;
    
    public transitionTable(node root, ArrayList tabla, ArrayList<node> leaves) {
        this.states = new ArrayList();
        
        this.transiciones = new ArrayList();
        
        ArrayList datos = new ArrayList();
        datos.add("S0");
        datos.add(root.first);
        datos.add(new ArrayList());
        datos.add(false);
        
        this.states.add(datos);
        this.cont = 1;
        
        for(int i = 0; i < states.size(); i++){
            ArrayList state = states.get(i);
            ArrayList<Integer> elementos = (ArrayList) state.get(1);
            
            // TODO  Aqui se encuentra el bug sobre las transiciones
            // Sucede cuando existe dos transisiciones con el mismo simbolos y diferentes siguientes
            // Ej: S0 ={1,2,3}  se ve la tabla de siguiente y se tiene  (S0,a) = {1,2,3} y (S0,a) = {4} el nuevo estado quedaria de la union de estos dos S1 = {1,2,3,4}
            // Lo que hace ahora es remplazar el estado final entonces queda asi S1 = {4} y no S1 = {1,2,3,4}
            
            for(int hoja : elementos){
                followTable sigTabla = new followTable();
                ArrayList lexemeNext = (ArrayList) sigTabla.next(hoja, tabla).clone();
                
                
                boolean existe = false;
                String found = "";
                
                for( ArrayList e : states ){
                    if(e.get(1).equals(lexemeNext.get(1))){
                        existe = true;
                        found = (String)e.get(0);
                        break;
                    }
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
                    transiciones.add((String) lexemeNext.get(0));
                
                }
                else{
                    leave hojas = new leave();
                    if(hojas.isAccept(hoja, leaves)){
                        state.set(3, true);
                    }
                    
                    boolean trans_exist = false;
                    
                    for(Object trans : (ArrayList)state.get(2)){
                        transicion t = (transicion) trans;
                        if(t.compare(state.get(0) + "", lexemeNext.get(0) + "")){
                            trans_exist = true;
                            break;
                        }
                    }
                    if(!trans_exist){
                        transicion trans = new transicion(state.get(0) + "", lexemeNext.get(0) + "", found + "");
                        ((ArrayList)state.get(2)).add(trans);
                    }
                }
            }
            
        }
        
        
    }
    
    public void impTable(String contador) throws IOException{
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            
            String path = "C:\\Users\\wwwed\\OneDrive\\Escritorio\\septimo_Semestre\\LAB_COMPI\\Ejemplo1\\Prueba1PR1\\TRANSICIONES_202001144\\tablaSiguientes"+contador+".html";
            fichero = new FileWriter(path);
            pw = new PrintWriter(fichero);
            
            //Comenzamos a escribir el html
            pw.println("<html>");
            pw.println("<head><title>TABLA DE TRANSICIONES </title></head>");
            pw.println("<body>");
            pw.println("<div align=\"center\">");
            pw.println("<h1>Reporte de TRANSICIONES</h1>");
            pw.println("<br></br>");
            pw.println("<table border=1>");
            pw.println("<tr>");
            pw.println("<td>Estado</td>");
            // ARREGLAR
            //System.out.println(transiciones);
            //HACER LISTA SIN TRANSICIONES REPETIDAS
            ArrayList<String> temporalTrans = new ArrayList<>();
            for(int add=0;add<transiciones.size();add++){
                boolean existe = temporalTrans.contains(transiciones.get(add));
                if (existe == false){
                    temporalTrans.add(transiciones.get(add));
                }
            }
            //COLOCAR LAS CABECERAS DE LAS TRANSICIONES EN EL HTML
            for(int add=0;add<temporalTrans.size();add++){
                pw.println("<td>"+ temporalTrans.get(add) +"</td>");
            }
            pw.println("</tr>");

            
            
            //Agregando las transiciones segun el S_
            for(ArrayList state : states){
                String tran = "[";
                pw.println("<tr>");
                pw.println("<td>" + (state.get(0) + " " + state.get(1)) + "</td>");
                for(Object tr : (ArrayList)state.get(2)){
                    transicion t = (transicion) tr;
                    tran += t.toString() + ", ";         
                    //System.out.println(t.toString());
                    for(int add=0;add<temporalTrans.size();add++){
                            if ((t.getTransiciones()).equals(temporalTrans.get(add)) ){
                                pw.println("<td>" + t.getFinalState() + "</td>");
                            }
                            /* else{
                                pw.println("<td> -- </td>");
                            }*/
                    }
                }
                pw.println("</tr>");
                
                tran += "]";
                tran = tran.replace(", ]", "]");
                System.out.println(state.get(0) + " " + state.get(1) + " " + tran + " " + state.get(3));
                
                
                
                
                
            }

            pw.println("</table>");
            pw.println("</div");
            pw.println("</body>");
            pw.println("</html>");
            //Desktop.getDesktop().open(new File(path));
            
            
        } catch (Exception e) {
        } finally {
            if (null != fichero) {
                fichero.close();
            }
        }
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
     public void impGraph(String contador){
        
        String cadena = "digraph AFN {\n\n";
        
        FileWriter fichero = null;
        PrintWriter escritor = null;
        
         //original
        for(ArrayList state : states){
            String graph = "";
            for(Object tr : (ArrayList)state.get(2)){
                transicion t = (transicion) tr;
                graph += t.graph();
            }
            if ((state.get(3).toString())=="true"){
                    graph += "\n" + state.get(0) + " [shape=doublecircle]"; //S3 [shape=doublecircle]
            }
            System.out.println(graph);
            cadena += graph + "\n";
        }
        //original
        
        cadena += "}";
        
        try{
            //fichero = new FileWriter("Arbol_Sintactico"+i+".dot");
            fichero = new FileWriter("C:\\Users\\wwwed\\OneDrive\\Escritorio\\septimo_semestre\\LAB_COMPI\\Ejemplo1\\Prueba1PR1\\AFD_202001144\\"+"AFD"+contador+".txt");
            
            escritor = new PrintWriter(fichero);
            escritor.println(cadena);
            escritor.close();
            fichero.close();
            reportar(contador);
       
        } catch (Exception e) {
            System.out.println("error en generar dot");
            e.printStackTrace();
        }
        
    }
     
    public void reportar(String i) throws IOException {
        
        String file_input_path = "C:\\Users\\wwwed\\OneDrive\\Escritorio\\septimo_semestre\\LAB_COMPI\\Ejemplo1\\Prueba1PR1\\AFD_202001144\\"+"AFD"+i+".txt";
        
        try {
            ProcessBuilder pBuilder;
            pBuilder = new ProcessBuilder("dot","-Tpng", "-O",file_input_path);
            pBuilder.redirectErrorStream(true);
            pBuilder.start();
            //System.out.println("SE SUPONE QUE SI JALO ESTA MIERDA");
            //system("dot -Tpng -O grafos/matrizAdyacencia.txt");
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
     
       //Desktop.getDesktop().open(new File(file_get_path));
    }
    
     
   
}
