import java.util.Random;
import java.util.Scanner;


public class Skeleton extends Monster {


    private String image = "\uD83D\uDC80";

    Skeleton(int sizeBoard) {
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
        System.out.println("Быстрее! Запомни комбинацию чисел!");

        Random random = new Random();

        int countNumbers = 2 + difficultGame;
        String trueAnswer = "";

        for (int i = 0; i < countNumbers; i++) {
            int number = random.nextInt(9) + 1;
            trueAnswer = trueAnswer + number;
        }

        System.out.print("ЗАПОМИНАЙ: " + trueAnswer);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.print("\rЗАПОМИНАЙ:           \n");

        System.out.println("Время вышло! Комбинация скрыта.");
        System.out.print("Введите всю комбинацию целиком: ");

        Scanner scanner = new Scanner(System.in);
        String playerAnswer = scanner.next();

        if (playerAnswer.equals(trueAnswer)) {
            System.out.println("\n Верно! Ты победил Эльфа! ");
            return true;
        } else {
            System.out.println("\nНеверно! Правильная комбинация чисел была: " + trueAnswer);
            System.out.println("Ты проиграл эту битву!");
            return false;
        }
    }
}


