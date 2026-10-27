import java.util.Random;
import java.util.Scanner;

public class Ghost extends Monster {

    private String image = "\uD83D\uDC7B";

    Ghost(int sizeBoard) {
        super(sizeBoard);
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public boolean taskMonster(int difficultGame) {
        Random random = new Random();

        int maxNumber = difficultGame * 10;
        int  trueAnswer = random.nextInt(maxNumber) + 1;

        System.out.println("Угадай число от 1 до " + maxNumber + ". У тебя 5 попыток.");

        Scanner scanner = new Scanner(System.in);

        for (int i = 1; i <= 5; i++) {
            System.out.print("Попытка №" + i + ". Введи число: ");
            int playerAnswer = scanner.nextInt();


            if (playerAnswer == trueAnswer) {
                System.out.println("\n Верно! Ты победил монстра!");
                return true;
            }


            if (i < 5) {
                if (playerAnswer < trueAnswer) {
                    System.out.println("Неверно. Число призрака БОЛЬШЕ");
                } else {
                    System.out.println("Неверно. Число призрака МЕНЬШЕ");
                }
            }
        }


        System.out.println("\n Попытки закончились! Ты проиграл.");
        System.out.println("Было загадано число: " + trueAnswer);
        return false;
    }
}


