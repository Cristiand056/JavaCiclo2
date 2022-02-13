/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Modelo.Cliente.Cliente;
import Modelo.Cliente.ClienteSql;
import Vista.frmCliente;
import Vista.frmProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CtrCliente implements ActionListener {
    private Cliente mod;
    private ClienteSql sqlmod;
    private frmCliente frm;
    DefaultTableModel modT;
    
    public CtrCliente(Cliente mod,ClienteSql sqlmod, frmCliente frm)
    {
        this.mod = mod;
        this.sqlmod = sqlmod;
        this.frm = frm;
        
        this.frm.btnCrear.addActionListener(this);
        this.frm.btnActualizar.addActionListener( this);
        this.frm.btnLeer.addActionListener( this);
        this.frm.btnBorrar.addActionListener( this);
        this.frm.btnLimpiar.addActionListener( this);
        
        Listar(frm.TablaC);
    }
    public void Listar(JTable TablaPro) {
        DefaultTableModel modelo = new DefaultTableModel();
        TablaPro.setModel(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Nacionalidad");
        modelo.addColumn("Correo");
        Object[] columna = new Object[5];
        int Registro = sqlmod.Consulta().size();
        for (int i = 0; i < Registro; i++) {
            columna[0] = sqlmod.Consulta().get(i).getId();
            columna[1] = sqlmod.Consulta().get(i).getNombre();
            columna[2] = sqlmod.Consulta().get(i).getApellido();
            columna[3] = sqlmod.Consulta().get(i).getNacionalidad();
            columna[4] = sqlmod.Consulta().get(i).getCorreo();
            modelo.addRow(columna);
        }
    }
    public void iniciar()
    {
        frm.setTitle("Clientes");
        frm.setLocationRelativeTo(null);                             
    }
    
    public void actionPerformed(ActionEvent e)
    {
        
        
        if(e.getSource() == frm.btnCrear)
        {
            mod.setId(Integer.parseInt(frm.txtId.getText()));
            mod.setNombre(frm.txtNombre.getText());
            mod.setApellido(frm.txtApellido.getText());
            mod.setNacionalidad(frm.txtNacionalidad.getText());
            mod.setCorreo(frm.txtCorreo.getText());
            
            if(sqlmod.crear(mod))
            {
                JOptionPane.showMessageDialog(null, "Cliente Creado");
                Limpiar();
                Listar(frm.TablaC);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error al Crear el registro");
                Listar(frm.TablaC);
                Limpiar();
            }
        }
        
        if(e.getSource() == frm.btnActualizar)
        {
            mod.setId(Integer.parseInt(frm.txtId.getText()));
            mod.setNombre(frm.txtNombre.getText());
            mod.setApellido(frm.txtApellido.getText());
            mod.setNacionalidad(frm.txtNacionalidad.getText());
            mod.setCorreo(frm.txtCorreo.getText());
            
            if(sqlmod.actualizar(mod))
            {
                JOptionPane.showMessageDialog(null, "Cliente Actualizado");
                Limpiar();
                Listar(frm.TablaC);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error al Actualizar el registro");
                Limpiar();
                Listar(frm.TablaC);
            }
        }
        
        if(e.getSource() == frm.btnLeer)
        {
            mod.setId(Integer.parseInt(frm.txtId.getText()));
            
            if(sqlmod.leer(mod))
            {
                frm.txtId.setText(String.valueOf(mod.getId()));
                frm.txtNombre.setText(String.valueOf(mod.getNombre()));
                frm.txtApellido.setText(String.valueOf(mod.getApellido()));
                frm.txtNacionalidad.setText(String.valueOf(mod.getNacionalidad()));
                frm.txtCorreo.setText(String.valueOf(mod.getCorreo()));
                Listar(frm.TablaC);
                       
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Cliente no Encontrado registro");
                Limpiar();
                Listar(frm.TablaC);
            }
        }
        
        if(e.getSource() == frm.btnBorrar)
        {
            mod.setId(Integer.parseInt(frm.txtId.getText()));
            
            
            if(sqlmod.eliminar(mod))
            {
                JOptionPane.showMessageDialog(null, "cliente Eliminado");
                Limpiar();
                Listar(frm.TablaC);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error al Eliminar el registro");
                Limpiar();
                Listar(frm.TablaC);
            }
        }
        if(e.getSource() == frm.btnLimpiar)
        {
            Limpiar();
        }
    
    }
    
    public void Limpiar()
    {
        frm.txtId.setText(null);
        frm.txtNombre.setText(null);
        frm.txtApellido.setText(null);
        frm.txtNacionalidad.setText(null);
        frm.txtCorreo.setText(null);
    }
}
