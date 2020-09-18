import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class EllipseArt {

    public static void main(String[] args) {

        // Uncomment the following line in order to stimulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        Random rand = new Random();
        int min = -50;
        int max = 50;
        int totalPoints = 1000000; // The points number to random in each test

        int t = in.nextInt();
        in.skip("\n");

        for (int i_test = 0; i_test < t; i_test++) {

            int n = in.nextInt();
            in.skip("\n");

            Ellipse[] ellipses = new Ellipse[n];

            for (int i = 0; i < n; i++) {

                ellipses[i] = new Ellipse(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
                // Skip to the next line, except in the last line
                in.skip(i < n - 1 ? "\n" : ""); //
            }

            int insidePoints = 0;
            for (int i = 0; i < totalPoints; i++) {
                double randX = rand.nextDouble() * (max - min) + min;
                double randY = rand.nextDouble() * (max - min) + min;;

                // Check if one of the ellipses contains the random point.
                for(int j = 0; j < n; j++) {
                    if (ellipses[j].isInside(randX, randY)) {
                        insidePoints++;
                        break;
                    }
                }
            }

            int outsidePoints = totalPoints - insidePoints;

            System.out.println(Math.round(((double) outsidePoints / totalPoints) * 100) + "%");
        }
    }

    public static void setStandardInput() {

        String input = "3\n" +
                "1\n" +
                "-40 0 40 0 100\n" +
                "1\n" +
                "10 50 90 50 100\n" +
                "2\n" +
                "15 -20 15 20 50\n" +
                "-10 10 30 30 100";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }

    public static class Ellipse {

        private int x1, y1, x2, y2, r;

        Ellipse(int x1, int y1, int x2, int y2, int r) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.r = r;
        }

        private double checkPoint(double x, double y) {
            double disFromLeftFocal = Math.sqrt(Math.pow(x - x1, 2) + Math.pow(y - y1, 2));
            double disFromRightFocal = Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2));

            return disFromLeftFocal + disFromRightFocal - r;
        }

        private boolean isInside(double x, double y) {
            return checkPoint(x, y) <= 0;
        }
    }
}