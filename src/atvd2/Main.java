package atvd2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número: ");

        int num = sc.nextInt();

        for (int i = num; i > 0; i--) {
            String result = "";
            for (int j = 0; j < i; j++) {
                result += "*";
            }
            System.out.println(result);
        }
    }
}