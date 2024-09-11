package atvd5;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Carrinho carrinho = new Carrinho();
        int opcao;

        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Inserir item ao carrinho");
            System.out.println("2. Acréscimo de item");
            System.out.println("3. Desconto de item");
            System.out.println("4. Acréscimo total");
            System.out.println("5. Desconto total");
            System.out.println("6. Finalizar venda");
            System.out.println("7. Ver produtos do carrinho");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Código do produto:");
                    int codigo = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Descrição do produto:");
                    String descricao = sc.nextLine();
                    System.out.println("Preço do produto:");
                    float preco = sc.nextFloat();

                    Produto produto = new Produto(codigo, descricao, preco);
                    carrinho.adicionarProduto(produto);
                    System.out.println("Você tem os produtos: ");
                    carrinho.buscarTodosProduto();
                    break;

                case 2:
                    System.out.println("Código do produto para acréscimo:");
                    int codigoAcrescimo = sc.nextInt();
                    System.out.println("Valor do acréscimo:");
                    float valorAcrescimo = sc.nextFloat();
                    carrinho.aplicarAcrescimoItem(codigoAcrescimo, valorAcrescimo);
                    break;

                case 3:
                    System.out.println("Código do produto para desconto:");
                    int codigoDesconto = sc.nextInt();
                    System.out.println("Valor do desconto:");
                    float valorDesconto = sc.nextFloat();
                    carrinho.aplicarDescontoItem(codigoDesconto, valorDesconto);
                    break;

                case 4:
                    System.out.println("Valor do acréscimo total:");
                    float acrescimoTotal = sc.nextFloat();
                    carrinho.aplicarAcrescimoTotal(acrescimoTotal);
                    break;

                case 5:
                    System.out.println("Valor do desconto total:");
                    float descontoTotal = sc.nextFloat();
                    carrinho.aplicarDescontoTotal(descontoTotal);
                    break;

                case 6:
                    carrinho.finalizarVenda();
                    break;

                case 7:
                    carrinho.buscarTodosProduto();
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 6);
    }
}