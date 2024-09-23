package br.com.fiap.dto;

import java.util.ArrayList;
import java.util.List;

public class FilmeController {
    private final List<Filme> filmes = new ArrayList<>();
    private int codigoAtual = 1; // Adicionado: código inicial para filmes

    public String inserirFilme(int codigo, String titulo, String genero, String produtora) {
        // Validação dos dados
        if (titulo == null || genero == null || produtora == null) {
            return "Erro: Campos obrigatórios não podem ser nulos.";
        }

        Filme filme = new Filme(codigo, titulo, genero, produtora);
        filmes.add(filme);
        return "Filme inserido com sucesso!";
    }


    public String alterarFilme(int codigo, String titulo, String genero, String produtora) {
        // Procurar o filme pelo código
        Filme filme = encontrarFilmePorCodigo(codigo);
        if (filme == null) {
            return "Filme não encontrado.";
        }

        // Atualizar os dados do filme
        filme.setTitulo(titulo);
        filme.setGenero(genero);
        filme.setProdutora(produtora);
        return "Filme alterado com sucesso!";
    }

    public String excluirFilme(int codigo) {
        // Procurar o filme pelo código
        Filme filme = encontrarFilmePorCodigo(codigo);
        if (filme == null) {
            return "Filme não encontrado.";
        }

        filmes.remove(filme);
        return "Filme excluído com sucesso!";
    }

    public String listarTodosFilmes() {
        StringBuilder sb = new StringBuilder();
        for (Filme filme : filmes) {
            sb.append(filme.toString()).append("\n");
        }
        return sb.toString();
    }

    private Filme encontrarFilmePorCodigo(int codigo) {
        for (Filme filme : filmes) {
            if (filme.getCodigo() == codigo) {
                return filme;
            }
        }
        return null;
    }
}
