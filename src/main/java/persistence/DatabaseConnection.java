package persistence;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {

    private DatabaseConnection(){}

    private static DataSource dataSource = null;

    private static Connection getConnection() throws Exception {
        if (dataSource == null) {
            Properties props = new Properties();
            InputStream is = DatabaseConnection.class.getClassLoader().getResourceAsStream("database.properties");
            props.load(is);
            is.close();
            dataSource = BasicDataSourceFactory.createDataSource(props);
        }
        return dataSource.getConnection();
    }


    public static Connection getInstance() throws Exception{
        Connection conn = getConnection();
        return conn;
    }

    public static void testConnection() throws IOException, Exception{

        //Objeto de conexión a base de datos tipo Connection
        Connection conn = null;

        //Objeto de propiedades donde vamos a cargar las propiedades del archivo
        Properties props = new Properties();


        //Resultset donde vamos a buscar el resultado que genera el query
        ResultSet rs = null;

        //Input Stream donde leemos el recurso donde está el archivo de propiedades
        InputStream is = DatabaseConnection.class.getClassLoader().getResourceAsStream("database.properties");

        //Objeto que utilizaremos para lanzar un query a la base de datos
        PreparedStatement ps = null;

        //Cargamos las propiedades que vienene del archivo
        props.load(is);

        //Cerramos el recurso
        is.close();

        //Abrimos try para controlar cualquier excepción de SQL que ocurra
        try {

            //Abrimos conexión a base de datos
            conn = DriverManager.getConnection(props.getProperty("url"), props);

            //Armamos el prepared statement con el query a realizar
            ps = conn.prepareStatement("select name as nombre, last_login as ultima_modificacion from usuario");

            //Ejecutamos el query
            rs = ps.executeQuery();

            //Obtenemos el resultado
            if (rs.next()){
                System.out.println(rs.getDate("ultima_modificacion").toString());
            }

            //Si continuamos entonces estamos conectados de forma satisfactoria
            System.out.println("Conectado a la Base de Datos");

            //Catch para atrapar alguna excepción de SQL
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; //TODO refactor

            //Bloque finally para cerrar la conexión
        }finally{
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw e; //TODO refactor
                }
            }
        }



    }
}