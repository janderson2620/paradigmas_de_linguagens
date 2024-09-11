package atvd4;
import java.util.HashMap;
import java.util.Map;

public class NomeParaNumero {


    private static final Map<String, Integer> mapPalavras = new HashMap<>();

    static {
        mapPalavras.put("zero", 0);
        mapPalavras.put("one", 1);
        mapPalavras.put("two", 2);
        mapPalavras.put("three", 3);
        mapPalavras.put("four", 4);
        mapPalavras.put("five", 5);
        mapPalavras.put("six", 6);
        mapPalavras.put("seven", 7);
        mapPalavras.put("eight", 8);
        mapPalavras.put("nine", 9);
    }

    public static void main(String[] args) {

        String[] dados = {
                "two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen"
        };

        int somaTotal = 0;

        for (String dado : dados) {
            int somaLinha = converteParaNumeroESoma(dado);
            somaTotal += somaLinha;
            System.out.println("Soma da linha '" + dado + "' = " + somaLinha);
        }

        System.out.println("Soma total = " + somaTotal);
    }


    public static int converteParaNumeroESoma(String dado) {
        StringBuilder numeroConvertido = new StringBuilder();
        StringBuilder palavra = new StringBuilder();

        for (int i = 0; i < dado.length(); i++) {
            char c = dado.charAt(i);


            if (Character.isDigit(c)) {
                numeroConvertido.append(c);
            } else {
                palavra.append(c);


                String palavraAtual = palavra.toString().toLowerCase();
                if (mapPalavras.containsKey(palavraAtual)) {
                    numeroConvertido.append(mapPalavras.get(palavraAtual));
                    palavra.setLength(0);
                }
            }
        }


        int soma = 0;
        for (char num : numeroConvertido.toString().toCharArray()) {
            soma += Character.getNumericValue(num);
        }

        return soma;
    }
}
