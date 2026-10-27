import java.util.Random;
import java.util.Scanner;

public class Monster {
    private String image = "\uD83E\uDDDF";
    private final int x, y;

    Monster(int sizeBoard) {
        Random random = new Random();
        x = 1 + random.nextInt(sizeBoard);
        y = 1 + random.nextInt(sizeBoard - 1);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean conflictPerson(int personX, int personY) {
        return personX == x && personY == y;
    }

    public boolean taskMonster(int difficultGame) {
        System.out.println("Решите задачу:");

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int trueAnswer;

        if (difficultGame == 1) {
            int a = random.nextInt(100);
            int b = random.nextInt(100);
            trueAnswer = a + b;
            System.out.println("Реши пример: " + a + " + " + b + " = ?");
        } else {
            int maxRange = 100 * difficultGame;
            int a = random.nextInt(maxRange);
            int b = random.nextInt(maxRange);
            int operation = random.nextInt(2);

            if (operation == 0) {
                trueAnswer = a + b;
                System.out.println("Реши пример: " + a + " + " + b + " = ?");
            } else {
                trueAnswer = a - b;
                System.out.println("Реши пример: " + a + " - " + b + " = ?");
            }
        }

        int answer = scanner.nextInt();

        if (trueAnswer == answer) {
            System.out.println("Верно! Ты победил монстра");
            return true;
        } else {
            System.out.println("Ты проиграл эту битву!");
            return false;
        }
    }
}

