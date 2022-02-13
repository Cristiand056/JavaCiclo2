/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto5;

import Controlador.CtrProducto;
import Modelo.producto.Producto;
import Modelo.producto.ProductoSql;
import Vista.frmProducto;
import Vista.login;

/**
 *
 * @author USER
 */
public class Reto5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        //frmProducto frm = new frmProducto();
        login lon = new login();
        
        //CtrProducto CtrP = new CtrProducto(mod, modsql, frm);
        //CtrP.iniciar();
        
        //frm.setVisible(true);
        lon.setLocationRelativeTo(null);
        lon.setVisible(true);
    }
    
}
