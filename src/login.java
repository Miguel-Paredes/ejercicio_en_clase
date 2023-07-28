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
    static final String DB_RUL = "jdbc:mysql://localhost/poo";
    static final String USER = "root";
    static final String PASS = "root_bas3";
    static final String QUERY = "Select * From Estudiantes";
    private String usu[];
    private String contra[];
    private int conta=0;
    private int encontrado;

    public login() {
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(
                    Connection conn = DriverManager.getConnection(DB_RUL, USER, PASS);
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(QUERY);)
                {
                    boolean login=false;
                    if((rs.getString("nombre").equals(usuario.getText()) && rs.getString("password").equals(new String(contrasenia.getPassword())))){
                        login=true;
                    }
                    if(login==true){
                        JFrame  userFrame= new JFrame("usuario");
                        //userFrame.setContentPane(userFrame.JPanel1);
                        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        userFrame.pack();
                        userFrame.setVisible(true);
                    }
                }
                catch (SQLException a){
                    throw new RuntimeException(a);
                }
            }
        });
    }
    private void informacion(){
        try(
                Connection conn = DriverManager.getConnection(DB_RUL, USER, PASS);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);)
        {while (rs.next()){
            usu[conta]=rs.getString("nombre");
            contra[conta]=rs.getString("password");
            conta++;
            if (usu[conta].equals(usuario.getText())){
                encontrado=conta;
            }
        }
        }catch (SQLException a){
            throw new RuntimeException(a);
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("login");
        frame.setContentPane(new login().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
