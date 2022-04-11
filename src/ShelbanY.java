import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ShelbanY {
    static int number;
    static int check = 0;

    public static void main(String[] args) throws InterruptedException {
        // input number of tries
        System.out.println("Орёл-Решка-ЩЕЛБАНЫ: Игрок1 (Да-совпало) против Игрок2 (Нет -не совпало)");
        System.out.print("Количество попыток ? : ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        System.out.println(number);

        //create two threads and - Action !!!
        PlayerAction playerOne = new PlayerAction(number);
        PlayerAction playerTwo = new PlayerAction(number);
        Random random = new Random();;
        if (random.nextBoolean()) {
                playerOne.start();
                playerTwo.start(); }
            else {
                playerTwo.start();
                playerOne.start();
        }
        // wait or not to wait ??? not to wait !

        //not to wait did not work... to wait !
        playerOne.join();
        playerTwo.join();

        List<Boolean> fix1 = playerOne.getList();
        List<Boolean> fix2 = playerTwo.getList();

        //calculate results
        int fix1Num = fix1.size();
        int fix2Num = fix2.size();
        check = fix1Num - fix2Num;

        if ((fix1Num == number) && (fix2Num == number)) {
            check = 0;
            for (int i = 1; i<number; i++) {
                if ((fix1.get(i))^(fix2.get(i))) { check = check+ 1; } else { check = check - 1; };
            }
            System.out.println("Если число (" + check + ") положительное, столько щелбанов Игроку2");
            System.out.println("иначе - наоборот; если 0 - редкое везение!");;
        } else {
            System.out.println(check + " !!! Кто-то всё-таки не успел. Странно...Редкое событие");
        }

    }
}

class PlayerAction extends Thread {
    private int num = 0;

    public PlayerAction(int num) {
        this.num = num;
    }
    public List<Boolean> action = new ArrayList<>();

    @Override
    public void run () {
        Random random = new Random();
        for (int i = 0; i<=num-1; i++) {
            action.add(i, random.nextBoolean());
        }
    }

    public List<Boolean> getList () {
        return action;
    }

}
