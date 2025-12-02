import util.FileReader;

import java.io.IOException;
import java.util.List;

public class Day2 {

    public static void main(String[] args) throws IOException {

        List<String> inputs = FileReader.read(2);

        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static long reponse1(List<String> inputs) {
        long resultat = 0;

        for (String plage: inputs.getFirst().split(",")) {

            String[] split = plage.split("-");

            long debut = Long.parseLong(split[0]);
            long fin = Long.parseLong(split[1]);
            for (long i = debut; i <= fin; i++) {
                String value = String.valueOf(i);
                int length = value.length();
                if (length % 2 == 0 && value.substring(0, length / 2).equals(value.substring(length / 2, length))) {
                    System.out.println(i);
                    resultat += i;
                }
            }

        }


        return resultat;
    }

    private static long reponse2(List<String> inputs) {
        long resultat = 0;

        for (String plage: inputs.getFirst().split(",")) {

            String[] split = plage.split("-");

            long debut = Long.parseLong(split[0]);
            long fin = Long.parseLong(split[1]);
            for (long i = debut; i <= fin; i++) {
                String value = String.valueOf(i);
                int length = value.length();

                int step = 0;
                for (int j = 0; j < length / 2; j++) {
                    boolean isValid = true;
                    step++;
                    String sub = value.substring(0, step);
                    for (int x = step; x < length; x = x + step) {
                        if (length < x + step || !sub.equals(value.substring(x, x + step))) {
                            isValid = false;
                            break;
                        }
                    }
                    if (isValid) {
                        resultat += i;
                        break;
                    }
                }
            }
        }

        return resultat;
    }
}
