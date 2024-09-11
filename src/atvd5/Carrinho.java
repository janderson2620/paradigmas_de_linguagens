package atvd5;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private List<Produto> produtos;

    public Carrinho() {
        this.produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public Produto buscarProdutoPorCodigo(int codigo) {
        for (Produto produto : produtos) {
            if (produto.getCodigo().equals(codigo)) {
                return produto;
            }
        }
        return null;
    }

    public void buscarTodosProduto() {
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }

    public void aplicarAcrescimoItem(int codigo, float valor) {
        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto != null) {
            produto.aplicarAcrescimo(valor);
        }
    }

    public void aplicarDescontoItem(int codigo, float valor) {
        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto != null) {
            produto.aplicarDesconto(valor);
        }
    }

    public void aplicarAcrescimoTotal(float valor) {
        if (!produtos.isEmpty()) {
            float valorPorItem = valor / produtos.size();
            for (Produto produto : produtos) {
                produto.aplicarAcrescimo(valorPorItem);
            }
        }
    }

    public void aplicarDescontoTotal(float valor) {
        if (!produtos.isEmpty()) {
            float valorPorItem = valor / produtos.size();
            for (Produto produto : produtos) {
                produto.aplicarDesconto(valorPorItem);
            }
        }
    }

    public void finalizarVenda() {
        float totalAcrescimo = 0;
        float totalDesconto = 0;
        float totalGeral = 0;

        for (Produto produto : produtos) {
            totalAcrescimo += produto.getAcrescimo();
            totalDesconto += produto.getDesconto();
            totalGeral += produto.getTotal();
        }

        System.out.println("Itens do carrinho:");
        for (Produto produto : produtos) {
            System.out.println(produto);
        }

        System.out.println("Total de acréscimo: " + totalAcrescimo);
        System.out.println("Total de desconto: " + totalDesconto);
        System.out.println("Valor total a pagar: " + totalGeral);
    }
}