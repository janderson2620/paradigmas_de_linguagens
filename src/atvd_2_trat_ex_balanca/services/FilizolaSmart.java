package atvd_2_trat_ex_balanca.services;

import atvd_2_trat_ex_balanca.interfaces.IBalanca;
import atvd_2_trat_ex_balanca.models.Produto;

import java.io.*;
import java.util.List;

public class FilizolaSmart implements IBalanca<Produto> {
    @Override
    public void exportar(List<Produto> produtos, String pastaArquivoTxt) {


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pastaArquivoTxt + "/CADTXT.TXT"))) {
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
            String tipo = "9".equals(produto.getTipo()) ? "P" : "U";
            String descricao = String.format("%-22s", produto.getDescricao());
            String preco = String.format("%07d", (int) (produto.getValor() * 100));

            return codigo + tipo + descricao + preco + "000";
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Formato inválido para o produto: " + produto.getDescricao());
        }
    }
}
