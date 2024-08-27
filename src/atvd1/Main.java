package atvd1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número: ");
        int num = sc.nextInt();

        String result = "";

        for (int i=0; i<num; i++) {
            String caractere = "*";
            result = result + caractere;
            System.out.println(result);
        }

    }
}