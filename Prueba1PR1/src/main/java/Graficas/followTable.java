/*
    Organizacion de Lenguajes y Compiladores 1 "C"
    Clase 4
    Método del Árbol
 */
package Graficas;

import Errores.Excepcion;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class followTable {
    public void append(int numNode, String lexeme, ArrayList flwList, ArrayList<ArrayList> table){
        for (ArrayList item : table){
            if( (int) item.get(0) == numNode && item.get(1) == lexeme ){
                for (Object flwItem : flwList){
                    if(! ((ArrayList)item.get(2)).contains((int)flwItem) ){
                       ((ArrayList)item.get(2)).add(flwItem);
                    }
                }
                return;
            }
        }
        ArrayList dato = new ArrayList();
        dato.add(numNode);
        dato.add(lexeme);
        dato.add(flwList);
        
        table.add(dato);
    }
    
    public ArrayList next(int numNode, ArrayList<ArrayList> table){
        ArrayList result = new ArrayList();
        for(ArrayList item : table){
            if( (int) item.get(0) == numNode ){
                result.add(item.get(1));
                result.add(((ArrayList)item.get(2)).clone());
                return result;
            }
        }
        result.add("");
        result.add(new ArrayList());
    return result;
    }
    
    public void printTable(ArrayList<ArrayList> table,String contador) throws IOException{
        for(ArrayList item : table){
            System.out.println(item.get(0) + " - " + item.get(1) + " - " + item.get(2) );
        }
        tablaSiguientesHTML(table,contador);
    }
    
    //HCAER HTML
    public static void tablaSiguientesHTML(ArrayList<ArrayList> table, String contador) throws IOException {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            
            String path = "C:\\Users\\wwwed\\OneDrive\\Escritorio\\7mo Semestre\\LAB COMPI\\Ejemplo1\\Prueba1PR1\\SIGUIENTES_202001144\\tablaSiguientes"+contador+".html";
            fichero = new FileWriter(path);
            pw = new PrintWriter(fichero);
            
            //Comenzamos a escribir el html
            pw.println("<html>");
            pw.println("<head><title>TABLA DE SIGUIENTES </title></head>");
            pw.println("<body>");
            pw.println("<div align=\"center\">");
            pw.println("<h1>Reporte de Errores</h1>");
            pw.println("<br></br>");
            pw.println("<table border=1>");
            pw.println("<tr>");
            pw.println("<td>Hoja</td>");
            pw.println("<td>Simbolo</td>");
            pw.println("<td>Siguiente</td>");
            pw.println("</tr>");

            for(ArrayList item : table){
                pw.println("<tr>");
                 pw.println("<td>" + item.get(0) + "</td>");
                pw.println("<td>" + item.get(1) + "</td>");
                pw.println("<td>" + item.get(2) + "</td>");
                pw.println("</tr>");
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
    //FIN HACER HTML
}
