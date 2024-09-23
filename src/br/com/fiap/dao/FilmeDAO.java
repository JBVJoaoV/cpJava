package br.com.fiap.dao;

import br.com.fiap.dto.Filme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilmeDAO {
    private final Connection con; // Tornando 'con' final

    public FilmeDAO(Connection con) {
        this.con = con; // Atribuição do construtor
    }

    public Connection getCon() {
        return con; // Método getter
    }

    public String inserir(Filme filme) {
        String sql = "insert into ddd_filme(codigo, titulo, genero, produtora) values(?,?,?,?)";
        Connection con = null; // Declare a conexão aqui
        try {
            con = getCon(); // Obtenha a conexão
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, filme.getCodigo());
                ps.setString(2, filme.getTitulo());
                ps.setString(3, filme.getGenero());
                ps.setString(4, filme.getProdutora());
                if (ps.executeUpdate() > 0) {
                    return "Inserido com sucesso.";
                } else {
                    return "Erro ao inserir";
                }
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        } finally {
            ConnectionFactory.fecharConexao(con); // Feche a conexão aqui
        }
    }

    public String alterar(Filme filme) {
        String sql = "update ddd_filme set titulo = ?, genero = ?, produtora = ? where codigo = ?"; // Corrigido
        try (PreparedStatement ps = getCon().prepareStatement(sql)) { // Removido ponto e vírgula desnecessário
            ps.setString(1, filme.getTitulo());
            ps.setString(2, filme.getGenero());
            ps.setString(3, filme.getProdutora());
            ps.setInt(4, filme.getCodigo());
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso.";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String excluir(Filme filme) {
        String sql = "delete from ddd_filme where codigo = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) { // Removido ponto e vírgula desnecessário
            ps.setInt(1, filme.getCodigo());
            if (ps.executeUpdate() > 0) {
                return "Excluído com sucesso.";
            } else {
                return "Erro ao excluir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public ArrayList<Filme> listarTodosFilmes() {
        String sql = "select * from ddd_filme order by codigo";
        ArrayList<Filme> listaFilme = new ArrayList<>(); // Usando inferência de tipo
        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) { // Removido ponto e vírgula desnecessário
            while (rs.next()) {
                Filme filme = new Filme(
                        rs.getInt("codigo"),      // Código do filme
                        rs.getString("titulo"),   // Título do filme
                        rs.getString("genero"),   // Gênero do filme
                        rs.getString("produtora") // Produtora do filme
                );

                listaFilme.add(filme);
            }
            return listaFilme;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }
}
