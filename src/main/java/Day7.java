import util.FileReader;

import java.io.IOException;
import java.util.List;

public class Day7 {

    public static void main(String[] args) throws IOException {

        List<String> inputs = FileReader.read(7);

        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static long reponse1(List<String> inputs) {
        long resultat = 0;

        String[][] grid = new String[inputs.size()][inputs.getFirst().split("").length];
        grid[0] = inputs.getFirst().split("");
        for (int i = 1; i < inputs.size(); i++) {
            String[] colCurrent = inputs.get(i).split("");
            String[] colPrecedent = grid[i-1];
            for (int j = 0; j < colCurrent.length; j++) {
                if (colPrecedent[j].equals("S") || colPrecedent[j].equals("|")) {
                    if (colCurrent[j].equals("^")) {
                        resultat++;
                        if (j > 0) {
                            colCurrent[j - 1] = "|";
                        }
                        if (j < colCurrent.length - 1) {
                            colCurrent[j + 1] = "|";
                        }
                    } else {
                        colCurrent[j] = "|";
                    }
                }
            }
            grid[i] = colCurrent;
        }

//        for (String[] strings : grid) {
//            System.out.println(Arrays.toString(strings));
//        }

        return resultat;
    }

    private static long reponse2(List<String> inputs) {

        String[][] grid = new String[inputs.size()][inputs.getFirst().split("").length];
        grid[0] = inputs.getFirst().split("");
        for (int i = 1; i < inputs.size(); i++) {
            String[] colCurrent = inputs.get(i).split("");
            String[] colPrecedent = grid[i-1];
            for (int j = 0; j < colCurrent.length; j++) {
                if (colPrecedent[j].equals("S") || colPrecedent[j].equals("|")) {
                    if (colCurrent[j].equals("^")) {
                        if (j > 0) {
                            colCurrent[j - 1] = "|";
                        }
                        if (j < colCurrent.length - 1) {
                            colCurrent[j + 1] = "|";
                        }
                    } else {
                        colCurrent[j] = "|";
                    }
                }
            }
            grid[i] = colCurrent;
        }

        return calculChemins(grid);
    }

    private static long calculChemins(String[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        long[][] nbChemins = new long[rows][cols];

        for (int col = 0; col < cols; col++) {
            if (grid[0][col].equals("S")) {
                nbChemins[0][col] = 1;
            }
        }

        for (int row = 0; row < rows - 1; row++) {
            for (int col = 0; col < cols; col++) {

                if (nbChemins[row][col] == 0) continue;

                String cell = grid[row][col];

                if (cell.equals("S") || cell.equals("|")) {
                    addIfValid(nbChemins, grid, row, col, row + 1, col);
                } else if (cell.equals("^")) {
                    addIfValid(nbChemins, grid, row, col, row + 1, col - 1);
                    addIfValid(nbChemins, grid, row, col, row + 1, col + 1);
                }
            }
        }

        long resultat = 0;
        for (long chemins : nbChemins[rows - 1]) {
            resultat += chemins;
        }

        return resultat;
    }

    private static void addIfValid(long[][] nbChemins, String[][] grid, int row, int col, int row2, int col2) {
        int cols = grid[0].length;
        if (col2 < 0 || col2 >= cols) return;

        String dest = grid[row2][col2];
        if (dest.equals("|") || dest.equals("^")) {
            nbChemins[row2][col2] += nbChemins[row][col];
        }
    }

// .   .   .   .   .   .   .    1  .   .   .   .   .   .   .
// .   .   .   .   .   .   .    1  .   .   .   .   .   .   .
// .   .   .   .   .   .    0   1   0  .   .   .   .   .   .
// .   .   .   .   .   .    1  .    1  .   .   .   .   .   .
// .   .   .   .   .    0   1   0   1   0  .   .   .   .   .
// .   .   .   .   .    1  .    2  .    1  .   .   .   .   .
// .   .   .   .    0   1   0   2   0   1   0  .   .   .   .
// .   .   .   .    1  .    3  .    3  .    1  .   .   .   .
// .   .   .    0   1   0   3   0   3   0   1   0  .   .   .
// .   .   .    1  .    4  .    3   3   1  .    1  .   .   .
// .   .    0   1   0   4   0   3   3   1   0   1   0  .   .
// .   .    1  .    5  .    4   3   4  .    2  .    1  .   .
// .    0   1   0   5   0   4   3   4  .    2   0   1   0  .
// .    1  .    1   5   4  .    7   4  .    2   1  .    1  .
//  0   1   0   1   5   4   0   7   4   0   2   1   0   1   0
//  1  .    2  .   10  .   11  .   11  .    2   1   1  .    1
}
