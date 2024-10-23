package atvd_2_trat_ex_balanca.factory;

import atvd_2_trat_ex_balanca.enums.TipoBalanca;
import atvd_2_trat_ex_balanca.interfaces.IBalanca;
import atvd_2_trat_ex_balanca.models.Produto;
import atvd_2_trat_ex_balanca.services.FilizolaSmart;
import atvd_2_trat_ex_balanca.services.ToledoMGV6;
import atvd_2_trat_ex_balanca.services.UranoIntegra;

public class BalancaFactory {
    public static IBalanca<Produto> getBalanca(TipoBalanca tipo) {

        switch (tipo) {
            case FINIZOLA_SMART:
                return (IBalanca<Produto>) new FilizolaSmart();
            case TOLEDO_MGV6:
                return (IBalanca<Produto>) new ToledoMGV6();
            case URANO_INTEGRA:
                return (IBalanca<Produto>) new UranoIntegra();
            default:
                return null;
        }
    }
}
