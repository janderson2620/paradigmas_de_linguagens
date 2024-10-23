package atvd_2_trat_ex_balanca.interfaces;

import java.util.List;

public interface IBalanca<T> {
    void exportar(List<T> produtos, String pastaArquivoTxt);
}

