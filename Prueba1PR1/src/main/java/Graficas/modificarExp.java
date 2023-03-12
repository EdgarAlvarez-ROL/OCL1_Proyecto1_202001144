package Graficas;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author wwwed
 */
public class modificarExp {
    
    ArrayList<String> puta = new ArrayList<>();
    
    public modificarExp(String cua) throws Exception {
        
        
        //String cua =  ".{Letra}* |  \"_\"|{Letra}{Digito}";
        //String cua = ".\"C\".\"O\".\"M\".\"P\".\"I\".\"1\" ? + | | {letra} {digito} \" \"";
        //String cua = ". \\' . + | | | | \\n {minus} {mayus} {digito} \" \" \\'";
        cua = "."+cua+"#";
        String cadena = cua.replaceAll(" +","");
        cadena = cadena.replaceAll("\"\""," ");
        String expresion = "[a-zA-ZñÑ]([a-zA-ZñÑ]|[0-9]+|_)*";
        //cadena = cadena.replaceAll(expresion, "cua");
        
        System.out.println(cadena);
        
        String cualquierMamada = "[\\!\\\"\\#\\$\\%\\&\\'\\(\\)\\*\\+\\,\\-\\.\\/\\:\\;\\<\\=\\>\\?\\@\\[\\\\\\]\\^\\_\\`\\{\\|\\}]";
        
        String[] listilla = cadena.split("");
        
        
        //System.out.println(listilla[1]);
        
        int contador = 0;
        String temporal = "";
        
        for(int i = 0; i < listilla.length; i++)
        {//Notemos que escribir i-- es lo mismo a escribir i = i - 1
            //System.out.println(listilla[i]);
            if (contador == 0){
                if ("{".equals(listilla[i])) {
                    temporal = temporal + listilla[i];
                }else if (listilla[i].matches(expresion)) {
                    //System.out.println(listilla[i]);
                    temporal = temporal + listilla[i];
                }else if ("}".equals(listilla[i])) {
                    temporal = temporal + listilla[i];
                    this.puta.add(temporal);
                    temporal = "";
                }else if("\"".equals(listilla[i])) {
                    temporal = temporal + listilla[i] + listilla[i+1];
                    i = i +1;
                    contador = 1;
                }else if(".".equals(listilla[i])){
                    this.puta.add(listilla[i]);
                }else if("+".equals(listilla[i])){
                    this.puta.add(listilla[i]);
                }else if("*".equals(listilla[i])){
                    this.puta.add(listilla[i]);
                }else if("?".equals(listilla[i])){
                    this.puta.add(listilla[i]);
                }else if("|".equals(listilla[i])){
                    this.puta.add(listilla[i]);
                }else if("#".equals(listilla[i])){
                    this.puta.add(listilla[i]);
                }else if(" ".equals(listilla[i])){
                    this.puta.add(listilla[i]);
                }else if("\\".equals(listilla[i])){
                    String lere = listilla[i] + listilla[i+1];
                    this.puta.add(lere);
                    lere = "";
                    i = i +1 ;
                }
                    
            }else{
                if (listilla[i].matches(expresion)) {
                    temporal = temporal + listilla[i];
                }else if(listilla[i].matches(" ")){
                    temporal = temporal + listilla[i];
                }else if(listilla[i].matches("\'")){
                    temporal = temporal + listilla[i];
                }else if("\"".equals(listilla[i]) && ("\"".equals(listilla[i+1])==true)){
                    temporal = temporal + listilla[i];
                }else if("\"".equals(listilla[i]) && ("\"".equals(listilla[i+1])==false)){
                    temporal = temporal + listilla[i];
                    this.puta.add(temporal);
                    temporal = "";
                    contador = 0;
                }else if(listilla[i].matches(cualquierMamada)){
                    temporal = temporal + listilla[i];
                }
            }            
        }
        
        System.out.println("final");
        System.out.println(this.puta);
        
        
    }
    
    public ArrayList<String> getPuta(){
        return puta;
    }
    
}
