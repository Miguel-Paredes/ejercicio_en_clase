import java.sql.*;

public class conexion {
    //1.	Definir la url de la conexión
    static final String DB_URL = "jdbc:mysql://localhost/poo";
    //2.	Definir el usuario con el que nos vamos a conectar
    static final String USER = "root";
    //3.	Definir la contraseña del usuario
    static final String PASS = "root_bas3";
    //4.	Colocar la sentencia del QUERY
    static final String usu="Gilmar";
    static final String QUERY = "SELECT * FROM Estudiantes WHERE nombre='"+usu+"'";
    public void conexxion(){
        try(
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);)
        {while (rs.next()){
            System.out.println("id: "+rs.getInt("id"));
            System.out.println("nombre: "+rs.getString("nombre"));
        }
        }catch (SQLException a) {
            throw new RuntimeException(a);
        }
        System.out.println("ingreso a la base de datos");
    }
}
