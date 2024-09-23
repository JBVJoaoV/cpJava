package br.com.fiap.main;

import br.com.fiap.dto.FilmeController;

import javax.swing.JOptionPane;

public class FilmeView {
    public static void main(String[] args) {
        FilmeController controller = new FilmeController(); // Instancia o controlador

        int opcao;
        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog(
                    "Escolha uma opção:\n" +
                            "1. Inserir Filme\n" +
                            "2. Alterar Filme\n" +
                            "3. Excluir Filme\n" +
                            "4. Listar Filmes\n" +
                            "5. Sair"));

            switch (opcao) {
                case 1: // Inserir Filme
                    String tituloInserir = JOptionPane.showInputDialog("Digite o título do filme:");
                    String generoInserir = JOptionPane.showInputDialog("Digite o gênero do filme:");
                    String produtoraInserir = JOptionPane.showInputDialog("Digite a produtora do filme:");
                    String resultadoInserir = controller.inserirFilme(tituloInserir, generoInserir, produtoraInserir);
                    System.out.println(resultadoInserir);
                    break;
                case 2: // Alterar Filme
                    int codigoAlterar = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do filme a ser alterado:"));
                    String novoTitulo = JOptionPane.showInputDialog("Digite o novo título do filme:");
                    String novoGenero = JOptionPane.showInputDialog("Digite o novo gênero do filme:");
                    String novaProdutora = JOptionPane.showInputDialog("Digite a nova produtora do filme:");

                    // Solicita ao usuário um novo código
                    String inputNovoCodigo = JOptionPane.showInputDialog("Digite o novo código do filme (ou pressione Enter para manter o código atual):");
                    Integer novoCodigo = inputNovoCodigo.isEmpty() ? null : Integer.parseInt(inputNovoCodigo);

                    String resultadoAlterar = controller.alterarFilme(codigoAlterar, novoTitulo, novoGenero, novaProdutora, novoCodigo);
                    System.out.println(resultadoAlterar);
                    break;
                case 3: // Excluir Filme
                    int codigoExcluir = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do filme a ser excluído:"));
                    String resultadoExcluir = controller.excluirFilme(codigoExcluir);
                    System.out.println(resultadoExcluir);
                    break;
                case 4: // Listar Filmes
                    String listaFilmes = controller.listarTodosFilmes();
                    JOptionPane.showMessageDialog(null, listaFilmes);
                    break;
                case 5: // Sair
                    System.out.println("Fim do programa");
                    controller.fecharConexao(); // Fecha a conexão ao sair
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        } while (opcao != 0);
    }
}
