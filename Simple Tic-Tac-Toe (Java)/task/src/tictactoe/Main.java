package tictactoe;

import java.util.Scanner;

public class Main {
    private static boolean isInARow(String[][] game, char c) {
        String ch = Character.toString(c);
        return  (game[0][0].equals(ch) && game[0][1].equals(ch) && game[0][2].equals(ch)) ||
                (game[1][0].equals(ch) && game[1][1].equals(ch) && game[1][2].equals(ch)) ||
                (game[2][0].equals(ch) && game[2][1].equals(ch) && game[2][2].equals(ch)) ||
                (game[0][0].equals(ch) && game[1][1].equals(ch) && game[2][2].equals(ch)) ||
                (game[0][2].equals(ch) && game[1][1].equals(ch) && game[2][0].equals(ch)) ||
                (game[0][0].equals(ch) && game[1][0].equals(ch) && game[2][0].equals(ch)) ||
                (game[0][1].equals(ch) && game[1][1].equals(ch) && game[2][1].equals(ch)) ||
                (game[0][2].equals(ch) && game[1][2].equals(ch) && game[2][2].equals(ch));
    }

    private static int[] getNumCells(String[][] game) {
        int[] numCells = {0, 0, 0};
        for (String[] strings : game) {
            for (String string : strings) {
                switch (string) {
                    case " ", "_" -> numCells[0]++;
                    case "X" -> numCells[1]++;
                    case "O" -> numCells[2]++;
                }
            }
        }
        return numCells;
    }

    private static void printGame(String[][] game) {
        System.out.println("---------");
        for (int i = 0; i < 3; ++i) {
            System.out.println("| " + String.join(" ", game[i]) + " |");
        }
        System.out.println("---------");
    }

    private static String checkGame(String[][] game) {
        boolean xInARow = isInARow(game, 'X');
        boolean oInARow = isInARow(game, 'O');
        int[] numCells = getNumCells(game);
        if ((xInARow && oInARow) || Math.abs(numCells[1] - numCells[2]) >= 2) {
            return "Impossible";
        }else if (xInARow) {
            return "X wins";
        } else if (oInARow) {
            return "O wins";
        } else if (numCells[0] > 0) {
            return "Game not finished";
        } else {
            return "Draw";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "_________";
        String[][] game = new String[3][3];
        for (int i = 0; i < input.length(); ++i) {
            game[i / 3][i % 3] = input.substring(i, i + 1).replace("_", " ");
        }
        printGame(game);
        String player = "X";
        while (true) {
            while (true) {
                String[] move = scanner.nextLine().split(" ");
                try {
                    int row = Integer.parseInt(move[0]);
                    int col = Integer.parseInt(move[1]);
                    if (row < 1 || row > 3 || col < 1 || col > 3) {
                        System.out.println("Coordinates should be from 1 to 3!");
                    } else if (!game[row - 1][col - 1].equals(" ")) {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        game[row - 1][col - 1] = player;
                        printGame(game);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("You should enter numbers!");
                }
            }
            String state = checkGame(game);
            if (!state.equals("Game not finished")) {
                System.out.println(state);
                break;
            } else {
                player = player.equals("X") ? "O" : "X";
            }
        }
    }
}
