
package Model;

import java.util.ArrayList;

/**
 *
 * @author FerminMF
 */
public class Secuencia {
    private String id;
    private ArrayList<Click> clicksDeLaSecuencia = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Click> getClicksDeLaSecuencia() {
        return clicksDeLaSecuencia;
    }

    public void setClicksDeLaSecuencia(ArrayList<Click> clicksDeLaSecuencia) {
        this.clicksDeLaSecuencia = clicksDeLaSecuencia;
    }
    
}
