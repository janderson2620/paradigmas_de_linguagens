package gerador_etiqueta;
import java.text.NumberFormat;
import java.util.Locale;

public class GeradorEtiqueta {

    public static String gerarEtiqueta(Produto produto) {

        String descricao = produto.getDescricao();
        if (descricao.length() > 22) {
            descricao = descricao.substring(0, 22);
        }

        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String valorLata = formatoMoeda.format(produto.getPrecoLata());
        String valorCaixa = formatoMoeda.format(produto.getPrecoCaixa());

        String etiquetaZPL = "^XA\n" +
                "^CF0,60\n" +
                "^FO50,50^FD" + descricao + "^FS\n" +
                "^CFA,50\n" +
                "^FO50,200^FDLata   " + valorLata + "^FS\n" +
                "^FO50,280^FDCaixa   " + valorCaixa + "^FS\n" +
                "^BY5,2,270\n" +
                "^FO100,450^BC^FD" + produto.getCodigoBarras() + "^FS\n" +
                "^XZ";

        return etiquetaZPL;
    }

    public static void main(String[] args) {
        Produto produto = new Produto("CERVEJA LAGER HEINEKEN", 6.0, 35.0, "78936683");
        String etiquetaZPL = GeradorEtiqueta.gerarEtiqueta(produto);
        System.out.println(etiquetaZPL);
    }
}
