package atvd5;

public class Produto {

    private Integer codigo;
    private String descricao;
    private float preco;
    private float acrescimo;
    private float desconto;
    private float total;

    public Produto(Integer codigo, String descricao, float preco) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
        this.acrescimo = 0;
        this.desconto = 0;
        this.total = preco;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void aplicarAcrescimo(float valor) {
        acrescimo += valor;
        total += valor;
    }

    public void aplicarDesconto(float valor) {
        desconto += valor;
        total -= valor;
    }

    public float getTotal() {
        return total;
    }

    public float getAcrescimo() {
        return acrescimo;
    }

    public float getDesconto() {
        return desconto;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo=" + codigo +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", acrescimo=" + acrescimo +
                ", desconto=" + desconto +
                ", total=" + total +
                '}';
    }
}