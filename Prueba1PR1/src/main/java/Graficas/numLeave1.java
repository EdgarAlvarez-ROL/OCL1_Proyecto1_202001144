/*
    Organizacion de Lenguajes y Compiladores 1 "C"
    Clase 4
    Método del Árbol
 */
package Graficas;

import java.util.List;

public final class numLeave1 {
    
    public int content;


    public numLeave1(List<String> puta){
        int contador = puta.size();
        String matchesExpresion = "\\.|\\||\\*|\\?|\\+";
        for(int i = 0; i < puta.size(); i++){
            if ((puta.get(i)).matches(matchesExpresion)){
                contador = contador - 1;
            }
        }
        contador = contador + 1;
        this.content = contador;
    }
    
    public int getNum(){
        content -= 1;
        return content;
    }
    
    
    public int clean(String content){
        return content.replace(".", "").replace("|", "").replace("*", "").replace("+", "").replace("?", "").length();
    }
}
