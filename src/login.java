import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.lang.String;
import javax.swing.JPasswordField;

public class login {
    private JPanel JPanel;
    private JButton ingresarButton;
    private JTextField usuario;
    private JPasswordField contrasenia;
    static final String DB_URL = "jdbc:mysql://localhost/poo";
    static final String USER = "root";
    static final String PASS = "root_bas3";
    String QUERY = ("Select * From Estudiantes where id="+usuario);
    private String usu[];
    private String contra[];
    private int conta=0;
    private int encontrado;

    public login() {
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hola");
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
        });
    }
    private void informacion(){
        try(
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);)
        {while (rs.next()){
            System.out.println("id: "+rs.getInt("id"));
            System.out.println("nombre: "+rs.getString("nombre"));
        }
        }catch (SQLException f){
            throw new RuntimeException(f);
        }
        System.out.println("ingreso a la base de datos");
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("login");
        frame.setContentPane(new login().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
