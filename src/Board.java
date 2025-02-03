package src;

import java.util.*;

public class Board {
    private final List<Cell> cells;
    public Board() {
        this.cells = new ArrayList<>();
        initializeCell();
        System.out.println("Initial state of board");
        displayBoard();
    }

    private void initializeCell() {
        for(int i=0;i<9;++i){
            cells.add(new Cell(i/3, i%3));
        }
    }

    public boolean markCell(int row, int col, Symbol symbol) {
      //  if(row*3)
        if(row >= 3 || row < 0) {
            System.out.println("Enter valid row number");
            return false;
        }
        else if(col >= 3 || col < 0) {
            System.out.println("Enter valid column number");
            return false;
        }
        else if(cells.get(row*3 + col).getSymbol() != null) {
            System.out.println("The cell already has a symbol");
            return false;
        }
        else {
           Cell cell = cells.get(row*3 + col);
           cell.setSymbol(symbol);
           System.out.println("The updated board now looks as");
           displayBoard();
           return true;
        }
    }

    private void displayBoard() {
        for(int i=0;i<9;++i){
            if(i%3 == 2) System.out.println("   " + cells.get(i).getSymbol());
            else System.out.print("   " + cells.get(i).getSymbol());
        }
    }

    public boolean matchFinished(int row, int col, Symbol symbol) {
        boolean won = true;
        for(int i=0; i<3; ++i) {
            if(cells.get(row*3 + i).getSymbol() != null && cells.get(row*3 + i).getSymbol().equals(symbol)) continue;
            won = false;
            break;
        }
        if(won) {
            System.out.println(symbol + " won the game");
            return true;
        }

        won = true;
        for(int i=0; i<3; ++i) {
            if(cells.get(i*3 + col).getSymbol() != null && cells.get(i*3 + col).getSymbol().equals(symbol)) continue;
            won = false;
            break;
        }
        if(won) {
            System.out.println(symbol + " won the game");
            return true;
        }

        won = true;
        for(int i=0; i<3; ++i) {
            int n = i*3 + i;
            if(cells.get(n).getSymbol() != null && cells.get(n).getSymbol().equals(symbol)) continue;
            won = false;
            break;
        }
        if(won) {
            System.out.println(symbol + " won the game");
            return true;
        }

        won = true;
        for(int i=0; i<3; ++i) {
            int n = i*3 + (2-i);
            if(cells.get(n).getSymbol() != null && cells.get(n).getSymbol().equals(symbol)) continue;
            won = false;
            break;
        }
        if(won) {
            System.out.println(symbol + " won the game");
            return true;
        }

        for(int i=0;i<9;++i) {
            if(cells.get(i).getSymbol() == null) return false;
        }
        System.out.println("Game Ended in a draw");
        return true;
    }
}
