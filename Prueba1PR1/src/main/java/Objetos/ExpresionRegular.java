
package Objetos;

/**
 *
 * @author wwwed
 */
public class ExpresionRegular {
    
    public String id;
    public String expolaca;
    
    public ExpresionRegular(String id, String expolaca){
        this.id = id;
        this.expolaca = expolaca;    
        
    }
    
    public String getExre(){
        return this.expolaca;
    }
    
    public String getId(){
        return this.id;
    }

}
