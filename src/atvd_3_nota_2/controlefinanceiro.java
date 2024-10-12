import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ControleFinanceiro {
    private static final String CAMINHO_BASE = "C:/Users/jande/IdeaProjects/paradigmas_de_linguagens/src/atvd_3_nota_2/";
    private static final String DESPESAS_CSV = "despesas.csv";
    private static final String RECEITAS_CSV = "receitas.csv";
    private static final String PROVISAO_CSV = "provisao.csv";

    private static Map<String, Double> despesasMap = new HashMap<>();
    private static Map<String, Double> receitasMap = new HashMap<>();
    private static Map<String, Double> provisoesMap = new HashMap<>();

    private static final CyclicBarrier barrier = new CyclicBarrier(3, () -> {

        System.out.println("Despesas agrupadas por data:");
        despesasMap.forEach((data, total) -> System.out.println(data + ": " + total));

        System.out.println("\nReceitas agrupadas por data:");
        receitasMap.forEach((data, total) -> System.out.println(data + ": " + total));

        System.out.println("\nProvisões agrupadas por data:");
        provisoesMap.forEach((data, total) -> System.out.println(data + ": " + total));

        double totalDespesas = despesasMap.values().stream().mapToDouble(Double::doubleValue).sum();
        double totalReceitas = receitasMap.values().stream().mapToDouble(Double::doubleValue).sum();
        double totalProvisoes = provisoesMap.values().stream().mapToDouble(Double::doubleValue).sum();

        System.out.println("\nTotal das despesas: " + totalDespesas);
        System.out.println("Total das receitas: " + totalReceitas);
        System.out.println("Total das provisões: " + totalProvisoes);
    });

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);


        executor.submit(() -> processarArquivo(CAMINHO_BASE + DESPESAS_CSV, despesasMap));
        executor.submit(() -> processarArquivo(CAMINHO_BASE + RECEITAS_CSV, receitasMap));
        executor.submit(() -> processarArquivo(CAMINHO_BASE + PROVISAO_CSV, provisoesMap));

        executor.shutdown();
    }

    private static void processarArquivo(String caminho, Map<String, Double> mapaFinanceiro) {
        try (BufferedReader br = new BufferedReader(new FileReader(Paths.get(caminho).toFile()))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                String data = partes[0];
                double valor = Double.parseDouble(partes[1].replace("\"", ""));
                mapaFinanceiro.merge(data, valor, Double::sum);
            }

    
            barrier.await();
        } catch (IOException | InterruptedException | java.util.concurrent.BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

