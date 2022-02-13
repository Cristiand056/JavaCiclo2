/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.empleado;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class EmpleadoSql extends Conexion{
    public boolean longin(Empleado emp)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT emp_id, emp_nombre, emp_password, emp_mail, emp_idTipo FROM empleado WHERE emp_id=?";
        
        try
        {
            ps = con.prepareStatement(sql);
            ps.setInt(1, emp.getEmp_id());           
            ps.execute();
            rs = ps.executeQuery();
            if(rs.next())
            {
                if (emp.getEmp_password().equals(rs.getString(3)))
                {
                    emp.setEmp_nombre(rs.getString(2));
                    emp.setEmp_idTipo(rs.getInt(5));
                    return true;
                }
                else
                {
                    return false; 
                }
                
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
}
