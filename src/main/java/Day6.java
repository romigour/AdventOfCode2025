import util.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day6 {

    public static void main(String[] args) throws IOException {

        List<String> inputs = FileReader.read(6);

        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static long reponse1(List<String> inputs) {
        long resultat = 0;

        long[][] numbers = new long[0][];
        for (int i = 0; i < inputs.size() - 1; i++) {
            String[] lineArray = inputs.get(i).trim().split("\\s+");
            if (i == 0) {
                numbers = new long[lineArray.length][inputs.size() - 1];
            }
            for (int j = 0; j < lineArray.length; j++) {
                numbers[j][i] = Long.parseLong(lineArray[j]);
            }
        }

        String[] operateurs = inputs.get(inputs.size() - 1).split("\\s+");

        for (int i = 0; i < operateurs.length; i++) {
            long sousTotal = 0;
            if ("+".equals(operateurs[i])) {
                for (int j = 0; j < numbers[i].length; j++) {
                    sousTotal += numbers[i][j];
                }
            } else if ("*".equals(operateurs[i])) {
                sousTotal = 1;
                for (int j = 0; j < numbers[i].length; j++) {
                    sousTotal *= numbers[i][j];
                }
            }
            resultat += sousTotal;
        }

        return resultat;
    }

    private static long reponse2(List<String> inputs) {
        long resultat = 0;

        long[][] numbers = new long[9999][9999];
        int maxCol = inputs.getFirst().split("").length - 1;

        int idxColNumber = 0;
        int idxNumber = 0;
        for (int col = maxCol; col >= 0; col--) {
            String number = "";
            for (int i = 0; i < inputs.size() - 1; i++) {
                char c = inputs.get(i).charAt(col);
                number += String.valueOf(c);
            }
            String numberCleared = number.replaceAll("\\s+", "");
            if (numberCleared.isEmpty()) {
                idxColNumber++;
                idxNumber = 0;
                continue;
            }
            numbers[idxColNumber][idxNumber] = Long.parseLong(numberCleared);
            idxNumber++;
        }

        String[] operateurs = inputs.get(inputs.size() - 1).split("\\s+");

        for (int i = operateurs.length - 1; i >= 0; i--) {
            int idxOperateur = operateurs.length - 1 - i;
            long sousTotal = 0;
            if ("+".equals(operateurs[idxOperateur])) {
                int idx = 0;
                while (numbers[i][idx] != 0) {
                    sousTotal += numbers[i][idx];
                    idx++;
                }
            } else if ("*".equals(operateurs[idxOperateur])) {
                sousTotal = 1;
                int idx = 0;
                while (numbers[i][idx] != 0) {
                    sousTotal *= numbers[i][idx];
                    idx++;
                }
            }
            resultat += sousTotal;
        }


        return resultat;
    }
}
