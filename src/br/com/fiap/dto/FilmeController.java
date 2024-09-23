package br.com.fiap.dto;

import br.com.fiap.dao.FilmeDAO;
import br.com.fiap.dao.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmeController {
    private final List<Filme> filmes = new ArrayList<>();
    private int codigoAtual = 1; // Código inicial
    private final FilmeDAO filmeDAO; // Adicionando final

    public FilmeController() {
        // Abre a conexão ao criar o controlador
        Connection con = ConnectionFactory.abrirConexao();
        filmeDAO = new FilmeDAO(con);
    }

    public String inserirFilme(String titulo, String genero, String produtora) {
        if (titulo == null || genero == null || produtora == null) {
            return "Erro: Campos obrigatórios não podem ser nulos.";
        }

        Filme filme = new Filme(codigoAtual++, titulo, genero, produtora);
        filmes.add(filme);
        return filmeDAO.inserir(filme); // Adiciona ao banco de dados
    }

    public String alterarFilme(int codigo, String novoTitulo, String novoGenero, String novaProdutora, Integer novoCodigo) {
        Filme filme = encontrarFilmePorCodigo(codigo);
        if (filme == null) {
            return "Filme não encontrado.";
        }

        // Verifica se um novo código foi fornecido e se é único
        if (novoCodigo != null) {
            if (encontrarFilmePorCodigo(novoCodigo) != null) {
                return "Erro: O novo código já está em uso.";
            }
            filme.setCodigo(novoCodigo); // Altera o código
        }

        filme.setTitulo(novoTitulo);
        filme.setGenero(novoGenero);
        filme.setProdutora(novaProdutora);
        return filmeDAO.alterar(filme); // Atualiza no banco de dados
    }

    public String excluirFilme(int codigo) {
        Filme filme = encontrarFilmePorCodigo(codigo);
        if (filme == null) {
            return "Filme não encontrado.";
        }

        filmes.remove(filme);
        return filmeDAO.excluir(filme); // Exclui do banco de dados
    }

    public String listarTodosFilmes() {
        return filmeDAO.listarTodosFilmes().toString(); // Lista filmes do banco de dados
    }

    private Filme encontrarFilmePorCodigo(int codigo) {
        for (Filme filme : filmes) {
            if (filme.getCodigo() == codigo) {
                return filme;
            }
        }
        return null;
    }

    public void fecharConexao() {
        try {
            filmeDAO.getCon().close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}
