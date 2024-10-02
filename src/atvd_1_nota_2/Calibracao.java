package br.com.exemplos.concorrencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Calibracao {
    public static void main(String[] args) {
        List<String> linhas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/jande/IdeaProjects/paradigmas_de_linguagens/src/atvd_1_nota_2/new_calibration_text.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int numThreads = 3;
        CountDownLatch latch = new CountDownLatch(numThreads);
        AtomicInteger somaTotal = new AtomicInteger(0);

        int tamanhoPorThread = (int) Math.ceil((double) linhas.size() / numThreads);

        for (int i = 0; i < numThreads; i++) {
            int inicio = i * tamanhoPorThread;
            int fim = Math.min(inicio + tamanhoPorThread, linhas.size());
            List<String> subLista = linhas.subList(inicio, fim);
            Thread worker = new Thread(new Worker(subLista, somaTotal, latch), "Worker-" + (i + 1));
            worker.start();
        }

        try {

            latch.await();
            System.out.println("A soma dos valores de calibração é: " + somaTotal.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Worker implements Runnable {
        private final List<String> linhas;
        private final AtomicInteger somaTotal;
        private final CountDownLatch latch;

        public Worker(List<String> linhas, AtomicInteger somaTotal, CountDownLatch latch) {
            this.linhas = linhas;
            this.somaTotal = somaTotal;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                int somaParcial = 0;
                for (String linha : linhas) {
                    somaParcial += valorCalibracao(linha);
                }

                somaTotal.addAndGet(somaParcial);
                System.out.println(Thread.currentThread().getName() + " completou a soma parcial: " + somaParcial);
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
