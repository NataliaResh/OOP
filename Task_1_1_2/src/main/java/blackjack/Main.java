package blackjack;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Добро пожаловать в Блэкджек!");
        Game game = new Game();
        game.start();
    }
}
