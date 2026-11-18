package br.com.starlog.main;

import br.com.starlog.controller.ProdutoController;
import br.com.starlog.dao.ProdutoDAOSqlite;
import br.com.starlog.factory.ConexaoFactory;
import br.com.starlog.model.Produto;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        ConexaoFactory.inicializarBanco();
        ProdutoController controller = new ProdutoController(new ProdutoDAOSqlite());
        Scanner scanner = new Scanner(System.in);
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=== STARLOG ALMOXARIFADO ===");
            System.out.println(" [1] Listar produtos");
            System.out.println(" [2] Cadastrar produto");
            System.out.println(" [3] Atualizar produto");
            System.out.println(" [4] Excluir produto");
            System.out.println(" [0] Sair");
            System.out.print("Opção: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> listar(controller);
                case "2" -> cadastrar(controller, scanner);
                case "3" -> atualizar(controller, scanner);
                case "4" -> excluir(controller, scanner);
                case "0" -> rodando = false;
                default -> System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }

    private static void listar(ProdutoController controller) {
        List<Produto> produtos = controller.obterTodos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        produtos.forEach(System.out::println);
    }

    private static void cadastrar(ProdutoController controller, Scanner scanner) {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine().trim();
            System.out.print("Preço (R$): ");
            double preco = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            System.out.print("Estoque: ");
            int estoque = Integer.parseInt(scanner.nextLine().trim());
            controller.cadastrar(nome, preco, estoque);
            System.out.println("Produto cadastrado!");
        } catch (NumberFormatException e) {
            System.out.println("Digite valores numéricos válidos.");
        }
    }

    private static void atualizar(ProdutoController controller, Scanner scanner) {
        try {
            System.out.print("ID do produto: ");
            Long id = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Novo nome: ");
            String nome = scanner.nextLine().trim();
            System.out.print("Novo preço (R$): ");
            double preco = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            System.out.print("Novo estoque: ");
            int estoque = Integer.parseInt(scanner.nextLine().trim());
            System.out.println(controller.alterar(id, nome, preco, estoque)
                    ? "Produto atualizado!" : "Nenhum produto com esse ID.");
        } catch (NumberFormatException e) {
            System.out.println("Digite valores numéricos válidos.");
        }
    }

    private static void excluir(ProdutoController controller, Scanner scanner) {
        try {
            System.out.print("ID do produto: ");
            Long id = Long.parseLong(scanner.nextLine().trim());
            System.out.println(controller.remover(id)
                    ? "Produto removido!" : "Nenhum produto com esse ID.");
        } catch (NumberFormatException e) {
            System.out.println("Digite um ID numérico válido.");
        }
    }
}
