package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Pessoa> listaPessoas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1 - Cadastrar pessoa");
            System.out.println("2 - Buscar pessoa por nome");
            System.out.println("3 - Sair");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            if (opcao == 1) {
                Pessoa pessoa = cadastrarPessoa(scanner);
                listaPessoas.add(pessoa);
                System.out.println("Pessoa cadastrada com sucesso!");
            } else if (opcao == 2) {
                System.out.print("Digite o nome da pessoa que deseja buscar: ");
                String nomeBusca = scanner.nextLine();
                Pessoa pessoaEncontrada = buscarPessoaPorNome(listaPessoas, nomeBusca);
                if (pessoaEncontrada != null) {
                    System.out.println("Pessoa encontrada:");
                    System.out.println("Nome: " + pessoaEncontrada.getNome());
                    System.out.println("Endereços:");
                    for (Endereco endereco : pessoaEncontrada.getEnderecos()) {
                        System.out.println(endereco.toString());
                    }
                } else {
                    System.out.println("Pessoa não encontrada.");
                }
            } else if (opcao == 3) {
                break;
            } else {
                System.out.println("Opção inválida. Digite novamente.");
            }
        }
    }

    public static Pessoa cadastrarPessoa(Scanner scanner) {
        System.out.print("Digite o nome da pessoa: ");
        String nome = scanner.nextLine();
        Pessoa pessoa = new Pessoa(nome);

        while (true) {
            System.out.print("Digite o nome da rua: ");
            String rua = scanner.nextLine();
            System.out.print("Digite o número do endereço: ");
            String numero = scanner.nextLine();
            System.out.print("Digite o nome da cidade: ");
            String cidade = scanner.nextLine();
            System.out.print("Digite a sigla do estado: ");
            String estado = scanner.nextLine();

            Endereco endereco = new Endereco(rua, numero, cidade, estado);
            pessoa.adicionarEndereco(endereco);

            System.out.print("Deseja cadastrar outro endereço? (S/N) ");
            String opcao = scanner.nextLine();
            if (!opcao.equalsIgnoreCase("S")) {
                break;
            }
        }

        return pessoa;
    }

    public static Pessoa buscarPessoaPorNome(ArrayList<Pessoa> listaPessoas, String nome) {
        for (Pessoa pessoa : listaPessoas) {
            if (pessoa.getNome().equalsIgnoreCase(nome)) {
                return pessoa;
            }
        }
        return null;
    }
}