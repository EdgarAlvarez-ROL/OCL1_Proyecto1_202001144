package Graficas;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Arbol {
    
    public Nodo raiz;
    
    public Arbol(Nodo raiz) {
        this.raiz = raiz;
    }
    
     public void GraficarSintactico(String i){
  
        String grafica = "Digraph Arbol_Sintactico{\n\n" + GraficaNodos(this.raiz, "0") + "\n\n}";        
        System.out.println(grafica);
        GenerarDot(grafica, i);

    }
    
    private String GraficaNodos(Nodo nodo, String i){
        int k=0; 
        String r = "";
        String nodoTerm = nodo.token;
     
        nodoTerm = nodoTerm.replace("\"", "");
        r= "node" + i + "[label = \"" + nodoTerm + "\"];\n";
   
        for(int j =0 ; j<=nodo.hijos.size()-1; j++){
            r = r + "node" + i + " -> node" + i + k + "\n";
       
            r= r + GraficaNodos(nodo.hijos.get(j), ""+i+k);
            k++;
        }
        
        if( !(nodo.lexema.equals("")) ){
            String nodoToken = nodo.lexema;
          
            nodoToken = nodoToken.replace("\"", "");
            r += "node" + i + "c[label = \"" + nodoToken + "\"];\n";
            r += "node" + i + " -> node" + i + "c\n";
        }
        
        return r;
    }
    
    private void GenerarDot(String cadena, String i){
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try{
            //fichero = new FileWriter("Arbol_Sintactico"+i+".dot");
            fichero = new FileWriter("C:\\Users\\wwwed\\Downloads\\OLC1-1S2023-main\\Ejemplo2\\src\\clase4\\"+"Arbol_Sintactico"+i+".dot");
            escritor = new PrintWriter(fichero);
            escritor.println(cadena);
            escritor.close();
            fichero.close();
            reportar(i);
       
          } catch (Exception e) {
            System.out.println("error en generar dot");
            e.printStackTrace();
        }
    }
    
    public void reportar(String i) throws IOException {
        
        String file_input_path = ("C:\\Users\\wwwed\\Downloads\\OLC1-1S2023-main\\Ejemplo2\\src\\clase4\\"+"Arbol_Sintactico"+i+".dot");
        String do_path = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
                                  //"C:\\Users\\wwwed\\Downloads\\OLC1-1S2023-main\\Ejemplo2\\src\\clase4\\prueba.txt"
        String file_get_path   = ("C:\\Users\\wwwed\\Downloads\\"+"MIEDACUA"+i+".jpg");
        try {
            ProcessBuilder pBuilder;
            pBuilder = new ProcessBuilder(do_path, "-Tjpg", "-o", file_get_path, file_input_path);
            pBuilder.redirectErrorStream(true);
            pBuilder.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
     
       //Desktop.getDesktop().open(new File(file_get_path));
    }
    
    
    
}
