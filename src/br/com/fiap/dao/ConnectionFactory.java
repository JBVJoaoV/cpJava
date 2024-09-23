package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // Constantes para facilitar futuras alterações
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "RM551659";
    private static final String PASS = "261103";

    // Método para abrir a conexão
    public static Connection abrirConexao() {
        Connection con = null;
        try {
            // Carregar o driver Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // Tentar estabelecer a conexão
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexão aberta.");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro: A classe de conexão não foi encontrada!\n" + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro de SQL!\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return con;
    }

    // Método para fechar a conexão
    public static void fecharConexao(Connection con) {
        if (con != null) { // Verificar se a conexão não é nula
            try {
                if (!con.isClosed()) { // Verificar se a conexão ainda está aberta
                    con.close();
                    System.out.println("Conexão fechada.");
                }
            } catch (SQLException e) {
                System.out.println("Erro de SQL!\n" + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } else {
            System.out.println("Conexão já estava fechada ou não foi criada.");
        }
    }
}
