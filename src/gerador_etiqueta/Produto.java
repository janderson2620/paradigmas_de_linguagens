package gerador_etiqueta;

public class Produto {
    private String descricao;
    private Double precoLata;
    private Double precoCaixa;
    private String codigoBarras;

    public Produto(String descricao, Double precoLata, Double precoCaixa, String codigoBarras) {
        this.descricao = descricao;
        this.precoLata = precoLata;
        this.precoCaixa = precoCaixa;
        this.codigoBarras = codigoBarras;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getPrecoLata() {
        return precoLata;
    }

    public Double getPrecoCaixa() {
        return precoCaixa;
    }


    public String getCodigoBarras() {
        return codigoBarras;
    }
}
