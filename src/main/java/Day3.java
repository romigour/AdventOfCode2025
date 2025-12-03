import util.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 {

    public static void main(String[] args) throws IOException {

        List<String> inputs = FileReader.read(3);

        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static long reponse1(List<String> inputs) {
        long resultat = 0;

        for (String line : inputs) {
            String[] batterie = line.split("");
            long maxBatterie = 0;
            for (int i = 0; i < batterie.length; i++) {
                for (int j = i+1; j < batterie.length; j++) {
                    long currentBatterie = Long.parseLong(batterie[i] + batterie[j]);
                    if (currentBatterie > maxBatterie) {
                        maxBatterie = currentBatterie;
                    }
                }
            }

            resultat += maxBatterie;
        }


        return resultat;
    }

    private static long reponse2(List<String> inputs) {
        long resultat = 0;

        for (String line : inputs) {
            String[] batterie = line.split("");
            List<Long> valeursBatteries =  new ArrayList<>();
            for (int i = 0; i < batterie.length; i++) {
                long currentBatterie = Long.parseLong(batterie[i]);
                if (valeursBatteries.size() < 12) {
                    valeursBatteries.add(currentBatterie);
                } else {
                    List<Long> temp = new ArrayList<>(valeursBatteries);
                    long currVal = temp.getFirst();
                    for (int x = 1; x < temp.size(); x++) {
                        if (currVal < temp.get(x)) {
                            break;
                        }
                        currVal = temp.get(x);
                    }
                    int idx = temp.indexOf(currVal);
                    temp.remove(idx);
                    temp.add(currentBatterie);

                    if (Long.parseLong(temp.stream().map(String::valueOf).collect(Collectors.joining())) >
                            Long.parseLong(valeursBatteries.stream().map(String::valueOf).collect(Collectors.joining()))) {
                        valeursBatteries = new ArrayList<>(temp);
                    }
                }
            }

            long result = Long.parseLong(
                    valeursBatteries.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining())
            );

            resultat += result;

        }

        return resultat;
    }
}
