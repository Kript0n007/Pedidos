package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("==== Menu ====");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Buscar/Alterar cliente");
            System.out.println("3. Realizar pedido");
            System.out.println("4. Imprimir quantidade de pedidos");
            System.out.println("5. Imprimir quantidade de pedidos encerrados");
            System.out.println("6. Gerar arquivo de pedidos encerrados");
            System.out.println("7. Encerrar pedido");
            System.out.println("0. Sair");
            System.out.print("Escolha a opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do cliente: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o endereço do cliente: ");
                    String endereco = scanner.nextLine();
                    clientes.add(new Cliente(nome, endereco));
                    break;
                case 2:
                    System.out.print("Digite o nome do cliente a ser buscado: ");
                    String nomeBusca = scanner.nextLine();
                    boolean encontrado = false;
                    for (Cliente cliente : clientes) {
                        if (cliente.nome.equalsIgnoreCase(nomeBusca)) {
                            System.out.println("Cliente encontrado!");
                            System.out.println("Nome: " + cliente.nome);
                            System.out.println("Endereço: " + cliente.endereco);
                            System.out.print("Deseja alterar o endereço? (S/N): ");
                            String alterarEndereco = scanner.nextLine();
                            if (alterarEndereco.equalsIgnoreCase("S")) {
                                System.out.print("Digite o novo endereço: ");
                                cliente.endereco = scanner.nextLine();
                                System.out.println("Endereço alterado com sucesso!");
                            }
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("Digite o nome do cliente que realizará o pedido: ");
                    String nomeClientePedido = scanner.nextLine();
                    Cliente clientePedido = null;
                    for (Cliente cliente : clientes) {
                        if (cliente.nome.equalsIgnoreCase(nomeClientePedido)) {
                            clientePedido = cliente;
                            break;
                        }
                    }
                    if (clientePedido != null) {
                        pedidos.add(new Pedido(clientePedido));
                        System.out.println("Pedido realizado com sucesso!");
                    } else {
                        System.out.println("Cliente não encontrado. Pedido não realizado.");
                    }
                    break;
                case 4:
                    System.out.println("Quantidade de pedidos: " + pedidos.size());
                    break;
                case 5:
                    int pedidosEncerrados = 0;
                    for (Pedido pedido : pedidos) {
                        if (pedido.encerrado) {
                            pedidosEncerrados++;
                        }
                    }
                    System.out.println("Quantidade de pedidos encerrados: " + pedidosEncerrados);
                    break;
                case 6:
                    gerarArquivoPedidosEncerrados(pedidos);
                    break;
                case 7:
                    System.out.print("Digite o nome do cliente para encerrar o pedido: ");
                    String nomeClienteEncerrarPedido = scanner.nextLine();
                    boolean pedidoEncerrado = false;
                    for (Pedido pedido : pedidos) {
                        if (!pedido.encerrado && pedido.cliente.nome.equalsIgnoreCase(nomeClienteEncerrarPedido)) {
                            pedido.encerrarPedido();
                            pedidoEncerrado = true;
                            System.out.println("Pedido encerrado com sucesso!");
                            break;
                        }
                    }
                    if (!pedidoEncerrado) {
                        System.out.println("Pedido não encontrado ou já está encerrado.");
                    }
                    break;
                case 0:
                    // Sair do programa
                    System.out.println("Saindo do programa...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    public static void gerarArquivoPedidosEncerrados(ArrayList<Pedido> pedidos) {
        try {
            FileWriter fileWriter = new FileWriter("pedidos_encerrados.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (Pedido pedido : pedidos) {
                if (pedido.encerrado) {
                    printWriter.println("Nome cliente: " + pedido.cliente.nome);
                    printWriter.println("Endereço: " + pedido.cliente.endereco);
                    printWriter.println(); // Linha em branco para separar os pedidos
                }
            }

            printWriter.close();
            System.out.println("Arquivo de pedidos encerrados criado com sucesso: pedidos_encerrados.txt");
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo de pedidos encerrados.");
            e.printStackTrace();
        }
    }
}