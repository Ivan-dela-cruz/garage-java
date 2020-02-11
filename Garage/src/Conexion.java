
import java.sql.*;

public class Conexion {
        public Connection sql; 
        public static String connectString = "jdbc:postgresql://localhost:5432/nuevabase"; // llamamos nuestra bd
        public static String user = "postgres"; // usuario postgres
        public static String password = "12345"; // no tiene password nuestra bd.
        public static Statement guardar;
        public void conectar(){
        try {
           Class.forName("org.postgresql.Driver");
           sql = DriverManager.getConnection(connectString, user, password);
           System.out.println("Conexion Exitosa!");
        }catch(ClassNotFoundException | SQLException e) {
           System.out.println("Conexion Fallida!");
        }
        } 
        ///////////////Insertar///////////////////////////
        public void insertar(String sentencia){
        try {
            guardar=sql.createStatement();
            int resul=guardar.executeUpdate(sentencia);
            if(resul==1){
                System.out.println("Informaciòn guardada con Exito!");
            }else{
                System.out.println("Informaciòn  no guardada Error!");
            }
            
        } catch (SQLException e) {
        }
        }
        ///////////////////Eliminar/////////////////////////////
        public void eliminar(String sentencia){
        try {
            guardar=sql.createStatement();
            int resul=guardar.executeUpdate(sentencia);
            if(resul==1){
                System.out.println("Informaciòn eliminada con Exito!");
            }else{
                System.out.println("Informaciòn  no eliminada Error!");
            }
            
        } catch (SQLException e) {
        }
        }
        ////////////////////Editar////////////////////////////////
        public void editar(String sentencia){
        try {
            guardar=sql.createStatement();
            int resul=guardar.executeUpdate(sentencia);
            if(resul==1){
                System.out.println("Informaciòn actualizo con Exito!");
            }else{
                System.out.println("Informaciòn  no actualizo Error!");
            }
            
        } catch (SQLException e) {
        }
        }
}


