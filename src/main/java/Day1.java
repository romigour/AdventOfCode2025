import util.FileReader;

import java.io.IOException;
import java.util.List;

public class Day1 {

    public static void main(String[] args) throws IOException {

        List<String> inputs = FileReader.read(1);

//        System.out.println(reponse1(inputs));
        System.out.println(reponse2(inputs));
    }

    private static int reponse1(List<String> inputs) {
        int code = 0;

        int start = 50;

        for (String input: inputs) {
            char direction = input.charAt(0);
            int distance = Integer.parseInt(input.substring(1));
            switch (direction) {
                case 'R':
                    start += distance;
                    while (start > 99) {
                        start = start - 100;
                    }
                    break;
                case 'L':
                    start -= distance;
                    while (start < 0) {
                        start = start + 100;
                    }
                    break;
            }
            if (start == 0) {
                code++;
            }
        }

        return code;
    }

    private static int reponse2(List<String> inputs) {
        int code = 0;

        int start = 50;

        for (String input: inputs) {
            char direction = input.charAt(0);
            int distance = Integer.parseInt(input.substring(1));
            switch (direction) {
                case 'R':
                    while (distance > 0) {
                        if (distance > 100) {
                            distance -= 100;
                            code++;
                        } else {
                            int diff = start + distance;
                            if (diff > 99) {
                                if (start != 0) {
                                    code++;
                                }
                                start = diff - 100;
                            } else {
                                start += distance;
                                if (start == 0) {
                                    code++;
                                }
                            }
                            distance = 0;
                        }
                    }
                    break;
                case 'L':

                    while (distance > 0) {
                        if (distance > 100) {
                            distance -= 100;
                            code++;
                        } else {
                            int diff = start - distance;
                            if (diff < 0) {
                                if (start != 0) {
                                    code++;
                                }
                                start = diff + 100;
                            } else {
                                start -= distance;
                                if (start == 0) {
                                    code++;
                                }
                            }
                            distance = 0;
                        }
                    }

                    break;
            }

        }

        return code;
    }
}
