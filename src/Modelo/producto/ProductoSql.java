package Modelo.producto;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import Vista.frmProducto;
import java.util.ArrayList;

public class ProductoSql extends Conexion{
    //se realiza la herencia para no tener que realizar objetos o instancias de conexón
    public boolean crear(Producto pro)
    {
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "INSERT INTO PRODUCTO (pro_nombre, pro_precio, pro_descripcion, pro_fecha_vencimiento)"
                + "VALUES(?,?,?,?)";
        //se crea la variable con la consulta sql 
        
        try
        {
            //si es correcta la consulta se agregaran las variables al modelo ya establecido 
            ps = con.prepareStatement(sql); // se establece la conexión y se crea la consulta
            ps.setString(1, pro.getNombre());
            ps.setInt(2, pro.getPrecio());
            ps.setString(3, pro.getDescripcion());
            ps.setString(4, pro.getFecha_vemcimiento());
            ps.execute(); // se ejecuta la consulta
            return true;
        } catch(SQLException e)
        {
            //en caso de fallar la exceción
            System.err.println(e); 
            return false;
        }   
        
        finally
        { 
            //esto se efectua siempre 
            try 
            {
                //cierra la conexión
                con.close();            
            }
            catch (SQLException e)
            {
                //por si llega a haber un error en el cierre
                System.err.println(e);
            }
        }
    }
    public boolean actualizar(Producto pro)
    {
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "UPDATE producto SET pro_nombre=?, pro_precio=?, pro_descripcion=?, pro_fecha_vencimiento=? WHERE pro_id=?";
        try
        {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getNombre());
            ps.setInt(2, pro.getPrecio());
            ps.setString(3, pro.getDescripcion());
            ps.setString(4, pro.getFecha_vemcimiento());
            ps.setInt(5, pro.getId());
            ps.execute();
            return true;
        } catch(SQLException e)
        {
            System.err.println(e);
            return false;
        }   
        
        finally
        {
            try 
            {
                con.close();            
            }
            catch (SQLException e)
            {
                System.err.println(e);
            }
        }
    }
    public boolean eliminar(Producto pro)
    {
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "DELETE FROM producto WHERE pro_id=?";
        try
        {
            ps = con.prepareStatement(sql);           
            ps.setInt(1, pro.getId());
            ps.execute();
            return true;
        } catch(SQLException e)
        {
            System.err.println(e);
            return false;
        }   
        
        finally
        {
            try 
            {
                con.close();            
            }
            catch (SQLException e)
            {
                System.err.println(e);
            }
        }
    }
    public boolean leer(Producto pro)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT * FROM producto WHERE pro_id=?";
        try
        {
            ps = con.prepareStatement(sql);           
            ps.setInt(1, pro.getId());
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                pro.setId( Integer.parseInt(rs.getString("pro_id")));
                pro.setNombre(rs.getString("pro_nombre"));
                pro.setPrecio(Integer.parseInt(rs.getString("pro_precio")));
                pro.setDescripcion(rs.getString("pro_descripcion"));
                pro.setFecha_vemcimiento(rs.getString("pro_fecha_vencimiento"));
                return true;
            }
            
            return false;
        } catch(SQLException e)
        {
            System.err.println(e);
            return false;
        }   
        
        finally
        {
            try 
            {
                con.close();            
            }
            catch (SQLException e)
            {
                System.err.println(e);
            }
        }
    }
    
    public boolean lista(Producto pro) 
    {
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        
        String sql = "DELETE FROM producto WHERE pro_id=?";
        try
        {
            ps = con.prepareStatement(sql);           
            ps.setInt(1, pro.getId());
            ps.execute();
            return true;
        } catch(SQLException e)
        {
            System.err.println(e);
            return false;
        }   
        
        finally
        {
            try 
            {
                con.close();            
            }
            catch (SQLException e)
            {
                System.err.println(e);
            }
        }
    }
    public ArrayList<Producto> Consulta() 
    {
           PreparedStatement ps = null;
            Connection con = getConexion();
            DefaultTableModel modT;
            ResultSet rs = null;
            
            Producto pro;
            ArrayList<Producto> data = new ArrayList<>();
            try{
                Statement sentencia = con.createStatement();          
            
                ResultSet datos = sentencia.executeQuery("SELECT * FROM producto");
                
                while(datos.next())
                {
                    pro = new Producto();
                    pro.setId(Integer.parseInt(datos.getString(1)));
                    pro.setNombre(datos.getString(2));
                    pro.setPrecio(Integer.parseInt(datos.getString(3)));
                    pro.setDescripcion(datos.getString(4));
                    pro.setFecha_vemcimiento(datos.getString(5));
                    
                    data.add(pro);
                }
                
                
            }
            catch(Exception e)
            {
                
                e.printStackTrace();
            }
        return data;
    }

}
