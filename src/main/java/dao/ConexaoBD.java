package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

    public static Connection getConexao() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/forum",
                    "postgres",
                    "1234"
            );
        } catch (ClassNotFoundException e) {
            System.out.println("Driver não encontrado");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("Erro ao conectar no banco");
            e.printStackTrace();
        }

        return null;
    }


}
