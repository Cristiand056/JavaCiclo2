package Controlador;

import Modelo.producto.Producto;
import Modelo.producto.ProductoSql;
import Vista.frmProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CtrProducto implements ActionListener {
    
    private Producto mod;
    private ProductoSql sqlmod;
    private frmProducto frm;
    DefaultTableModel modT;
    
    public CtrProducto(Producto mod,ProductoSql sqlmod, frmProducto frm)
    {
        this.mod = mod;
        this.sqlmod = sqlmod;
        this.frm = frm;
        
        this.frm.btnCrear.addActionListener(this);
        this.frm.btnActualizar.addActionListener( this);
        this.frm.btnLeer.addActionListener( this);
        this.frm.btnBorrar.addActionListener( this);
        this.frm.btnLimpiar.addActionListener( this);
        
        Listar(frm.TablaPro);
    }
    
    public void Listar(JTable TablaPro) {
        DefaultTableModel modelo = new DefaultTableModel();
        TablaPro.setModel(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("Precio($)");
        modelo.addColumn("Descripción");
        modelo.addColumn("Fecha Vencimiento");
        Object[] columna = new Object[5];
        int Registro = sqlmod.Consulta().size();
        for (int i = 0; i < Registro; i++) {
            columna[0] = sqlmod.Consulta().get(i).getId();
            columna[1] = sqlmod.Consulta().get(i).getNombre();
            columna[2] = sqlmod.Consulta().get(i).getPrecio();
            columna[3] = sqlmod.Consulta().get(i).getDescripcion();
            columna[4] = sqlmod.Consulta().get(i).getFecha_vemcimiento();
            modelo.addRow(columna);
        }
    }
    
    public void iniciar()
    {
        frm.setTitle("Productos");
        frm.setLocationRelativeTo(null);
        
        
                   
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        
        
        if(e.getSource() == frm.btnCrear)
        {
            //al presionar el boton en cuestion se establese las variables entradas, para agragarlas al modelo
            mod.setId(Integer.parseInt(frm.txtId.getText()));
            mod.setNombre(frm.txtNombre.getText());
            mod.setPrecio(Integer.parseInt(frm.txtPrecio.getText()));
            mod.setDescripcion(frm.txtDescripcion.getText());
            mod.setFecha_vemcimiento(frm.txtFecha_vencimiento.getText());
            
            if(sqlmod.crear(mod))
            {
                //funciono la consulta
                JOptionPane.showMessageDialog(null, "Producto Creado");
                Limpiar();
                Listar(frm.TablaPro);
            }
            else
            {
                //fallo en la acción
                JOptionPane.showMessageDialog(null, "Error al Crear registro");
                Listar(frm.TablaPro);
                Limpiar();
            }
        }
        
        if(e.getSource() == frm.btnActualizar)
        {
            mod.setId(Integer.parseInt(frm.txtId.getText()));
            mod.setNombre(frm.txtNombre.getText());
            mod.setPrecio(Integer.parseInt(frm.txtPrecio.getText()));
            mod.setDescripcion(frm.txtDescripcion.getText());
            mod.setFecha_vemcimiento(frm.txtFecha_vencimiento.getText());
            
            if(sqlmod.actualizar(mod))
            {
                JOptionPane.showMessageDialog(null, "Producto Actualizado");
                Limpiar();
                Listar(frm.TablaPro);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error al Actualizar registro");
                Limpiar();
                Listar(frm.TablaPro);
            }
        }
        
        if(e.getSource() == frm.btnLeer)
        {
            mod.setId(Integer.parseInt(frm.txtId.getText()));
            
            if(sqlmod.leer(mod))
            {
                frm.txtId.setText(String.valueOf(mod.getId()));
                frm.txtNombre.setText(String.valueOf(mod.getNombre()));
                frm.txtPrecio.setText(String.valueOf(mod.getPrecio()));
                frm.txtDescripcion.setText(String.valueOf(mod.getDescripcion()));
                frm.txtFecha_vencimiento.setText(String.valueOf(mod.getFecha_vemcimiento()));
                Listar(frm.TablaPro);
                       
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Producto no Encontrado registro");
                Limpiar();
                Listar(frm.TablaPro);
            }
        }
        
        if(e.getSource() == frm.btnBorrar)
        {
            mod.setId(Integer.parseInt(frm.txtId.getText()));
            
            
            if(sqlmod.eliminar(mod))
            {
                JOptionPane.showMessageDialog(null, "Producto Eliminado");
                Limpiar();
                Listar(frm.TablaPro);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error al Eliminar registro");
                Limpiar();
                Listar(frm.TablaPro);
            }
        }
        if(e.getSource() == frm.btnLimpiar)
        {
            //llama la función limpiar
            Limpiar();
        }
    
    }
    
    public void Limpiar()
    { 
        //limpia los campos de la interfaz
        frm.txtId.setText(null);
        frm.txtNombre.setText(null);
        frm.txtPrecio.setText(null);
        frm.txtDescripcion.setText(null);
        frm.txtFecha_vencimiento.setText(null);
    }
    
}
