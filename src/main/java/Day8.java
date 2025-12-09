import util.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day8 {

    public static void main(String[] args) throws IOException {

        List<String> inputs = FileReader.read(8);

        System.out.println(reponse1(inputs));
//        System.out.println(reponse2(inputs));
    }

    private static long reponse1(List<String> inputs) {
        long resultat = 0;

        List<Point> points = inputs.stream().map(line -> {
            String[] coords = line.split(",");
            return new Point(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]));
        }).collect(Collectors.toCollection(ArrayList::new));

        List<Point> allPoints = new ArrayList<>(points);

        List<List<Point>> pairList = new ArrayList<>();

        List<Point> bestPair = new ArrayList<>();
        List<Set<Point>> circuits = new ArrayList<>();

        while (!points.isEmpty()) {
            double minDistance = Integer.MAX_VALUE;
            for (int i = 0; i < points.size(); i++) {
                for (int j = 0; j < allPoints.size(); j++) {
                    Point p1 = points.get(i);
                    Point p2 = allPoints.get(j);
                    if (p1.equals(p2)) continue;
                    double distance = distance(p1, p2);
                    if (distance < minDistance) {
                        minDistance = distance;
                        bestPair.clear();
                        bestPair.add(p1);
                        bestPair.add(p2);
                    }
                }
            }
            pairList.add(new ArrayList<>(bestPair));
            points.removeIf(p -> p.equals(bestPair.getFirst()));
            points.removeIf(p -> p.equals(bestPair.getLast()));
        }

        for (List<Point> pair: pairList) {
            boolean newCircuit = true;
            for (Set<Point> circuit: circuits) {
                if (circuit.contains(pair.getFirst()) || circuit.contains(pair.getLast())) {
                    circuit.add(pair.getFirst());
                    circuit.add(pair.getLast());
                    newCircuit = false;
                    break;
                }
            }

            if (newCircuit) {
                circuits.add(new HashSet<>(pair));
            }
        }

        return resultat;
    }

    private static long reponse2(List<String> inputs) {

        long resultat = 0;



        return resultat;
    }

    public static double distance(Point p1, Point p2) {
        double dx = p2.x - p1.x;
        double dy = p2.y - p1.y;
        double dz = p2.z - p1.z;
        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }

    static class Point {
        int x;
        int y;
        int z;

        Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            if (y != point.y) return false;
            return z == point.z;
        }

        @Override
        public String toString() {
            return "[" + x + "," + y + "," + z + "]";
        }
    }


}
// 162 / 425 / 431 / 346 / 592 => 5
// 906 / 805 / 739 => 3
// 862 / 984 => 2
// 52 / 117 / 216 => 3
// 819 / 941 / 970 => 3
// 352 / 542 / 466 / 57 => 4