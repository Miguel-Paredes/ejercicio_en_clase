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

    public login() {
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean login=false;
                    Connection conn = DriverManager.getConnection(DB_RUL, USER, PASS);
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(QUERY);
                    if((rs.getString("nombre")=usuario) && (contrasenia=rs.getString("password"))){
                        login=true;
                    }
                    if(login==true){
                        JFrame  userFrame= new JFrame("usuario");
                        userFrame.setContentPane(userFrame.JPanel1);
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("login");
        frame.setContentPane(new login().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
