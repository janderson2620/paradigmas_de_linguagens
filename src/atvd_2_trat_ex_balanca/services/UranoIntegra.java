package atvd_2_trat_ex_balanca.services;

import atvd_2_trat_ex_balanca.interfaces.IBalanca;
import atvd_2_trat_ex_balanca.models.Produto;

import java.io.*;
import java.util.List;

public class UranoIntegra implements IBalanca<Produto> {

    @Override
    public void exportar(List<Produto> produtos, String pastaArquivoTxt) {
        File directory = new File(pastaArquivoTxt);

        if (!directory.exists()) {
            throw new IllegalArgumentException("Diretório não existe: " + pastaArquivoTxt);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pastaArquivoTxt + "/PRODUTOS.TXT"))) {
            for (Produto produto : produtos) {
                if (produto == null || produto.getDescricao() == null) {
                    throw new NullPointerException("Produto inválido ou descrição nula.");
                }
                String linha = formatarProduto(produto);
                writer.write(linha);
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro de I/O ao escrever no arquivo: " + e.getMessage());
        } catch (SecurityException e) {
            System.err.println("Erro de segurança ao acessar o arquivo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Argumento inválido: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro desconhecido: " + e.getMessage());
        }
    }

    private String formatarProduto(Produto produto) {
        try {
            String codigo = String.format("%06d", produto.getCodigo());
            String flag = "*";
            String tipo = "9".equals(produto.getTipo()) ? "0" : "6";
            String descricao = String.format("%-20s", produto.getDescricao());
            String preco = String.format("%09.2f", produto.getValor()).replace(".", ",");

            return codigo + flag + tipo + descricao + preco + "00000D";
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Formato inválido para o produto: " + produto.getDescricao());
        }
    }
}
