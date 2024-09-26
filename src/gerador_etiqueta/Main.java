package gerador_etiqueta;

public class Main {
    public static void main(String[] args) {
        Produto produto = new Produto("CERVEJA LAGER HEINEKEN", 6.0, 35.0, "78936683");
        String etiquetaZPL = GeradorEtiqueta.gerarEtiqueta(produto);
        System.out.println(etiquetaZPL);
    }
}
