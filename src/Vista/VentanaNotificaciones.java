/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
* Esta es una clase auxiliar para facilitar el hecho de mostrar ventanaas de notificaciones
* @author: Fermin Muñoz
* @version: 22/09/2014
*/
public class VentanaNotificaciones  {
    
    public static void ventanaError(String error,JFrame e){
        JOptionPane.showMessageDialog(e, error,
                "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void ventanaOk(String mensage,JFrame e){
        JOptionPane.showMessageDialog(e, mensage,
                "Ok", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
}
