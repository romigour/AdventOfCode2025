import util.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day5 {

    public static void main(String[] args) throws IOException {

        List<String> inputs = FileReader.read(5);

        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static long reponse1(List<String> inputs) {
        long resultat = 0;

        boolean firstStep = true;
        List<long[]> freshList = new ArrayList<>();
        for (String line : inputs) {
            if ("".equals(line)) {
                firstStep = false;
                continue;
            }

            if (firstStep) {
                String[] lineArray = line.split("-");
                long from = Long.parseLong(lineArray[0]);
                long to = Long.parseLong(lineArray[1]);
                freshList.add(new long[]{from, to});
            } else {
                long lineValue = Long.parseLong(line);
                for (long[] freshMarge: freshList) {
                    if (freshMarge[0] <= lineValue && freshMarge[1] >= lineValue) {
                        resultat++;
                        break;
                    }
                }
            }
        }

        return resultat;
    }

    private static long reponse2(List<String> inputs) {
        long resultat = 0;
        List<long[]> freshList = new ArrayList<>();
        for (String line : inputs) {
            if ("".equals(line)) {
                break;
            }
            String[] lineArray = line.split("-");
            long from = Long.parseLong(lineArray[0]);
            long to = Long.parseLong(lineArray[1]);

            for (long[] freshMarge: freshList) {
                if (from >= freshMarge[0] && from <= freshMarge[1] && to >= freshMarge[0] && to <= freshMarge[1]) {
                    from = -1;
                    to = -1;
                    break;
                } else if (from <= freshMarge[0] && to >= freshMarge[1]) {
                    freshMarge[0] = -1;
                    freshMarge[1] = -1;
                } else if (from <= freshMarge[0] && to >= freshMarge[0]) {
                    freshMarge[0] = to + 1;
                } else if (from >= freshMarge[0] && from <= freshMarge[1] && to >= freshMarge[1]) {
                    freshMarge[1] = from - 1;
                }
            }
            freshList.removeIf(marge -> marge[0] == -1 && marge[1] == -1);
            if (from == -1 && to == -1) {
                continue;
            }
            freshList.add(new long[]{from, to});
        }

        for (long[] freshMarge: freshList) {
            resultat += (freshMarge[1] - freshMarge[0] + 1);
        }

        return resultat;
    }
}
