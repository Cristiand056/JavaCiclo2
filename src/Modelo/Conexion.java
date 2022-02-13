package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    private final String base = "tienda";
    private final String user = "root";
    private final String password = "Sp5Qz2L077#0_4";
    private final String url = "jdbc:mysql://localhost:3306/"+base;
    private Connection con = null;
    //conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/estudiantes", "root", "administrator");
    public Connection getConexion()
    {
    
        try
        {
            //llamo al driver
            Class.forName("com.mysql.cj.jdbc.Driver"); //este driver es por el tema de la versíón, en que se tenia anteriormente fue "com.mysql.jdbc.Driver"
            //la variable de conexion la igualo a la conexion y le envio los datos
            con = DriverManager.getConnection(this.url,this.user,this.password);
        }
        catch(SQLException e)
        {
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    } 
}
