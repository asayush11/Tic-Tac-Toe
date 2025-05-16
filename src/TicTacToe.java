package src;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class TicTacToe {

    private final Board board;
    private final Deque<Player> players;
    private static volatile TicTacToe instance;

    private TicTacToe() {
        this.board = new Board();
        this.players = new ArrayDeque<>();
        players.add(new Player(Symbol.CROSS));
        players.addLast(new Player(Symbol.ZERO));
        System.out.println("Game is set up and Player1 is set to " + Symbol.CROSS + " and Player2 is set to " + Symbol.ZERO);
    }

    public synchronized static TicTacToe getInstance() {
        if(instance == null) {
            synchronized (TicTacToe.class){
                if(instance == null){
                    instance = new TicTacToe();
                }
            }
        }
        return instance;
    }

    public void startGame() {
      while(true) {
          Player player = players.getFirst();
          players.remove();
          players.addLast(player);
          System.out.println("It's " + player.getSymbol() + " chance to play");

          Scanner scanner = new Scanner(System.in);
          int row = -1, col = -1;
          boolean played = false;
          while (!played) {
              System.out.println("Enter row: ");
              row = scanner.nextInt();
              System.out.println("Enter column: ");
              col = scanner.nextInt();
              played = board.markCell(row, col, player.getSymbol());
          }
          if (board.matchFinished(row, col, player.getSymbol())) return;
      }
    }
}
