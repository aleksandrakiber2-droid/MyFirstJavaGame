
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String castle = "\uD83C\uDFF0";

        int sizeBoard = 5;
        int step = 0;
        int score = 0;

        Person person = new Person(sizeBoard);

        Random random = new Random();
        int castleX = 1 + random.nextInt(sizeBoard);
        int castleY = 1;

        String[][] board = new String[sizeBoard][sizeBoard];
        for (int y = 1; y <= sizeBoard; y++) {
            for (int x = 1; x <= sizeBoard; x++) {
                board[y - 1][x - 1] = "  ";
            }
        }

        int countMonster = sizeBoard * sizeBoard - sizeBoard - 1;
        Monster[] monsters = new Monster[countMonster];
        int count = 0;

        while (count < countMonster) {
            Monster monster;
            int monsterType = random.nextInt(4);

            if (monsterType == 0) {
                monster = new Monster(sizeBoard);
            } else if (monsterType == 1) {
                monster = new BigMonster(sizeBoard);
            } else if (monsterType == 2) {
                monster = new Ghost(sizeBoard);
            } else {
                monster = new Skeleton(sizeBoard);
            }


            int row = monster.getY() - 1;
            int column = monster.getX() - 1;

            if (board[row][column].equals("  ")) {
                board[row][column] = monster.getImage();
                monsters[count] = monster;
                count++;
            }
        }

        board[castleY - 1][castleX - 1] = castle;

        System.out.println("Привет! Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        System.out.println("Ваш ответ:\t" + answer);

        switch (answer) {
            case "ДА":
                System.out.println("Выбери сложность игры (от 1 до 5):");
                int difficultGame = scanner.nextInt();
                System.out.println("Выбранная сложность:\t" + difficultGame);

                while (true) {
                    board[person.getY() - 1][person.getX() - 1] = person.getImage();
                    outputBoard(board, person.getLive(), score);

                    System.out.println("Введите, куда будет ходить персонаж "
                            + "(ход возможен только по вертикали или горизонтали на одну клетку)"
                            + "\nКоординаты персонажа — (x: " + person.getX()
                            + ", y: " + person.getY() + ")");

                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    System.out.println(x + ", " + y);

                    if (person.isMoveCorrect(x, y)) {
                        if (board[y - 1][x - 1].equals("  ")) {
                            board[person.getY() - 1][person.getX() - 1] = "  ";
                            person.move(x, y);
                            step++;

                            System.out.println("Ход корректный; новые координаты: "
                                    + person.getX() + ", " + person.getY()
                                    + "\nХод номер: " + step);
                        } else if (board[y - 1][x - 1].equals(castle)) {
                            if (score < 15) {
                                System.out.println("Стража замка: «У тебя слишком мало очков (" + score + "/15). Мы не пустим тебя!»");
                                System.out.println("Победи больше монстров, чтобы войти.");
                            } else {
                                System.out.println("Вы прошли игру! Вы собрали достаточно очков: " + score);
                                break;
                            }
                        } else {
                            for (Monster monster : monsters) {
                                if (monster.conflictPerson(x, y)) {
                                    if (monster.taskMonster(difficultGame)) {
                                        board[person.getY() - 1][person.getX() - 1] = "  ";
                                        person.move(x, y);
                                        step++;
                                        score++;
                                        System.out.println("Текущие очки: " + score);
                                    } else {
                                        person.downLive();
                                    }
                                    break;
                                }
                            }
                        }
                    } else {
                        System.out.println("Некорректный ход");
                    }

                    if (person.getLive() <= 0) {
                        break;
                    }
                }

                if (person.getLive() <= 0) {
                    System.out.println("Закончились жизни. Итог: побеждено монстров — " + score);
                }
                break;

            case "НЕТ":
                System.out.println("Жаль, приходи ещё!");
                break;

            default:
                System.out.println("Данные введены некорректно");
                break;
        }
    }

    static void outputBoard(String[][] board, int live, int score) {
        String leftBlock = "| ";
        String rightBlock = "|";
        String wall = "+ —— + —— + —— + —— + —— +";

        for (String[] row : board) {
            System.out.println(wall);
            for (String col : row) {
                System.out.print(leftBlock + col + " ");
            }
            System.out.println(rightBlock);
        }
        System.out.println(wall);

        System.out.println("Количество жизней:\t" + live);
        System.out.println("Количество очков:\t" + score + "\n");
    }
}