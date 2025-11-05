package Ex3;

import java.util.Scanner;

public class Ex3Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o número de threads que deseja gerar:");
        int num = sc.nextInt();

        for (int i = 0; i < num; i++) {
        Ex3Thread t = new Ex3Thread(i);
        t.start();
        }
    }
}
