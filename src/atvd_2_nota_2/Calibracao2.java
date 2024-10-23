package br.com.exemplos.concorrencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Calibracao2 {
    public static void main(String[] args) {
        String caminhoArquivo = "C:/Users/jande/IdeaProjects/paradigmas_de_linguagens/src/atvd_1_nota_2/new_calibration_text.txt";

        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(numThreads);
        AtomicInteger somaTotal = new AtomicInteger(0);

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            int linhasPorThread = 0;
            while ((linha = br.readLine()) != null) {
                executor.execute(new Worker(linha, somaTotal, latch));
                linhasPorThread++;
                if (linhasPorThread >= numThreads) {
                    latch.await(); 
                    linhasPorThread = 0;
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        try {
            latch.await();
            System.out.println("A soma dos valores de calibração é: " + somaTotal.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Worker implements Runnable {
        private final String linha;
        private final AtomicInteger somaTotal;
        private final CountDownLatch latch;

        public Worker(String linha, AtomicInteger somaTotal, CountDownLatch latch) {
            this.linha = linha;
            this.somaTotal = somaTotal;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                int somaParcial = valorCalibracao(linha);
                somaTotal.addAndGet(somaParcial);
                System.out.println(Thread.currentThread().getName() + " processou a linha e somou: " + somaParcial);
            } finally {
                latch.countDown();
            }
        }

        private int valorCalibracao(String linha) {
            Character primeiroCaractere = null;
            Character ultimoCaractere = null;

            for (char c : linha.toCharArray()) {
                if (Character.isDigit(c)) {
                    if (primeiroCaractere == null) {
                        primeiroCaractere = c;
                    }
                    ultimoCaractere = c;
                }
            }

            if (primeiroCaractere != null && ultimoCaractere != null) {
                return Integer.parseInt(primeiroCaractere.toString() + ultimoCaractere.toString());
            } else {
                return 0;
            }
        }
    }
}
