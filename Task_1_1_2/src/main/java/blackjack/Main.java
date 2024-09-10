package blackjack;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}


//import java.util.Scanner;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Введите число:");
//
//        int number = sc.nextInt();
//
//        System.out.println("Спасибо! Вы ввели число " + number);
//
//        number = sc.nextInt();
//
//        System.out.println("Спасибо! Вы ввели число " + number);
//        sc.close();//вот теперь мы сделали все правильно!
//
//    }
//}