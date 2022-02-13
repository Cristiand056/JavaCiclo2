package Modelo.Cliente;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import Vista.frmProducto;
import java.util.ArrayList;

public class ClienteSql extends Conexion{
    
    public boolean crear(Cliente cli) 
    {
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "INSERT INTO CLIENTE (cli_nombre, cli_apellido, cli_nacionalidad, cli_correo)"
                + "VALUES(?,?,?,?)";
        try
        {
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getNombre());
            ps.setString(2, cli.getApellido());
            ps.setString(3, cli.getNacionalidad());
            ps.setString(4, cli.getCorreo());
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
    public boolean actualizar(Cliente cli) 
    {
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "UPDATE cliente SET cli_nombre=?, cli_apellido=?, cli_nacionalidad=?, cli_correo=? WHERE cli_id=?";
                
        try
        {
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getNombre());
            ps.setString(2, cli.getApellido());
            ps.setString(3, cli.getNacionalidad());
            ps.setString(4, cli.getCorreo());
            ps.setInt(5, cli.getId());
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
    public boolean eliminar(Cliente cli)
    {
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "DELETE FROM CLIENTE WHERE cli_id=?";
        try
        {
            ps = con.prepareStatement(sql);           
            ps.setInt(1, cli.getId());
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
    public boolean leer(Cliente cli)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT * FROM CLIENTE WHERE cli_id=?";
        try
        {
            ps = con.prepareStatement(sql);           
            ps.setInt(1, cli.getId());
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                cli.setId( Integer.parseInt(rs.getString("cli_id")));
                cli.setNombre(rs.getString("cli_nombre"));
                cli.setApellido(rs.getString("cli_apellido"));
                cli.setNacionalidad(rs.getString("cli_nacionalidad"));
                cli.setCorreo(rs.getString("cli_correo"));
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
    public ArrayList<Cliente> Consulta() 
    {
            PreparedStatement ps = null;
            Connection con = getConexion();
            DefaultTableModel modT;
            ResultSet rs = null;
            
            Cliente cli;
            ArrayList<Cliente> data = new ArrayList<>();
            try{
                Statement sentencia = con.createStatement();          
            
                ResultSet datos = sentencia.executeQuery("SELECT * FROM cliente");
                
                while(datos.next())
                {
                    cli = new Cliente();
                    cli.setId(Integer.parseInt(datos.getString(1)));
                    cli.setNombre(datos.getString(2));
                    cli.setApellido(datos.getString(3));
                    cli.setNacionalidad(datos.getString(4));
                    cli.setCorreo(datos.getString(5));
                    
                    
                    data.add(cli);
                }
                
                
            }
            catch(Exception e)
            {
                
                e.printStackTrace();
            }
        return data;
    }
    
}
