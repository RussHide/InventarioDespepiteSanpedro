package Conexion;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

public class ConexionBD {
//
//    //public static final String URL = "jdbc:derby:";InventarioDespepiteBD
//    public static final String USUARIO = "InventarioDespepiteBD";
//    public static final String CONTRASEÑA = "InventarioDespepiteBD";
//    public static final String currentDir = System.getProperty("user.dir");
//
//
//    public static Connection SubConexion() {
//        Connection conn = null;
//        try {
//            Class.forName("org.apache.derby.jdbc.ClientDriver");
////            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
////            conn = (Connection) DriverManager.getConnection("jdbc:derby:" + currentDir + "//InventarioDespepiteBD", USUARIO, CONTRASEÑA);
//            conn = (Connection) DriverManager.getConnection("jdbc:derby://localhost/InventarioDespepiteBD", USUARIO, CONTRASEÑA);
//
//        } catch (Exception e) {
//            System.out.println("La conexion fallo: " + e);
//        }
//        return conn;
//    }

    public static final String URL = "jdbc:mysql://localhost:3306/inventariodespepite";
    public static final String USUARIO = "root";
    public static final String CONTRASEÑA = "";

    public static Connection SubConexion() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        } catch (Exception e) {
            System.out.println("La conexion fallo: " + e);
        }
        return conn;
    }

}
