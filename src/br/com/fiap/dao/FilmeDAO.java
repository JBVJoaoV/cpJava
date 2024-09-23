package br.com.fiap.dao;

import br.com.fiap.dto.Filme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilmeDAO {
    private Connection con;

    public FilmeDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(Filme filme) {
        String sql = "insert into ddd_filme(codigo, titulo,genero, produtora) values(?,?,?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setInt(1, filme.getCodigo());
            ps.setString(2, filme.getTitulo());
            ps.setString(3, filme.getGenero());
            ps.setString(4, filme.getProdutora());
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso.";
            } else {
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String alterar(Filme filme) {
        String sql = "update ddd_filme set codigo = ?,titulo = ?, genero = ?, where produtora = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
            ps.setInt(1, filme.getCodigo());
            ps.setString(2, filme.getTitulo());
            ps.setString(3, filme.getGenero());
            ps.setString(4, filme.getProdutora());
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso.";
            } else {
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String excluir(Filme filme) {
        String sql = "delete from ddd_filme where codigo = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql);) {
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
        ArrayList<Filme> listaFilme = new ArrayList<Filme>();
        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {
            if (rs != null) {
                while (rs.next()) {
                    // Passa os valores do ResultSet ao construtor do Filme
                    Filme filme = new Filme(
                            rs.getInt("codigo"),      // Código do filme
                            rs.getString("titulo"),   // Título do filme
                            rs.getString("genero"),   // Gênero do filme
                            rs.getString("produtora") // Produtora do filme
                    );

                    listaFilme.add(filme);
                }
                return listaFilme;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }
}