
package Graficas;

import java.io.*;

public class Escritor{
   
    public void escribir(String data) {
        
        File f;
        f = new File("C:\\Users\\wwwed\\Downloads\\OLC1-1S2023-main\\Ejemplo2\\src\\clase4\\prueba.txt");

        //Escritura
        try{
        FileWriter w = new FileWriter(f,true);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);	
        //wr.write("Esta es una linea de codigo");//escribimos en el archivo 
        //wr.append(" - y aqui continua"); //concatenamos en el archivo sin borrar lo existente
                      //ahora cerramos los flujos de canales de datos, al cerrarlos el archivo quedará guardado con información escrita
                      //de no hacerlo no se escribirá nada en el archivo
        wr.append(data+"\n");
        
        
        wr.close();
        bw.close();
        }catch(IOException e){};
       }
        
    
    // recibe un nodo con los datos que necesita de padre e hijo
    public String GraficaNodos(Nodo nodo, String i){
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
    
    
    
    }
    
    


