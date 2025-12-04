import util.FileReader;

import java.io.IOException;
import java.util.List;

public class Day4 {

    public static void main(String[] args) throws IOException {

        List<String> inputs = FileReader.read(4);

        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static long reponse1(List<String> inputs) {
        long resultat = 0;

        int[][] grille = new int[inputs.size()][inputs.get(0).length()];

        for (int i = 0; i < inputs.size(); i++) {
            String[] lineArray = inputs.get(i).split("");
            for (int j = 0; j < lineArray.length; j++) {
                String caractere = lineArray[j];
                if (caractere.equals("@")) {
                    grille[i][j] = 1;
                }
            }
        }

        for (int y = 0; y < inputs.size(); y++) {
            for (int x = 0; x < inputs.size(); x++) {
                if (grille[y][x] == 1) {
                    if (compterVoisins(grille, x, y) < 4) {
                        resultat++;
                    }
                }
            }
        }


        return resultat;
    }

    private static int compterVoisins(int[][] grille, int x, int y) {
        int compteur = 0;
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                if (dx == 0 && dy == 0) continue;
                int nx = x + dx;
                int ny = y + dy;
                if (nx >= 0 && nx < grille[0].length && ny >= 0 && ny < grille.length) {
                    if (grille[ny][nx] == 1) {
                        compteur++;
                    }
                }
            }
        }
        return compteur;
    }

    private static long reponse2(List<String> inputs) {
        long resultat = 0;


        int[][] grille = new int[inputs.size()][inputs.get(0).length()];

        for (int i = 0; i < inputs.size(); i++) {
            String[] lineArray = inputs.get(i).split("");
            for (int j = 0; j < lineArray.length; j++) {
                String caractere = lineArray[j];
                if (caractere.equals("@")) {
                    grille[i][j] = 1;
                }
            }
        }

        long nextResultat = -1;
        while (nextResultat != resultat) {
            nextResultat = (int) resultat;
            for (int y = 0; y < inputs.size(); y++) {
                for (int x = 0; x < inputs.size(); x++) {
                    if (grille[y][x] == 1) {
                        if (compterVoisins(grille, x, y) < 4) {
                            resultat++;
                            grille[y][x] = 0;
                        }
                    }
                }
            }
        }
        return resultat;
    }
}
