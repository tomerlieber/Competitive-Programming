import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class XtremeDriving {

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        long K = in.nextLong();
        int N = in.nextInt();
        in.skip("\n");

        Cow[] cows = new Cow[N];
        for (int i = 0; i < N; i++) {
            int xi = in.nextInt();
            long yi = in.nextLong();

            cows[i] = new Cow(xi, yi);
        }

        long result = countWays(1, 1, K, cows);

        System.out.println((int) (result % (Math.pow(10, 9) + 7)));
    }

    public static long countWays(int lane, long unit, long K, Cow[] cows) {

        if (lane < 1 || lane > 4) {
            return 0;
        }

        for (Cow cow : cows) {
            if (cow.lane == lane && cow.unit == unit) {
                return 0;
            }
        }

        if (unit == K) {
            return lane == 1 ? 1 : 0;
        }

        long counter = 0;

        counter += countWays(lane + 1, unit + 1, K, cows);
        counter += countWays(lane, unit + 1, K, cows);
        counter += countWays(lane - 1, unit + 1, K, cows);

        return counter;
    }

    public static class Cow {

        private int lane;
        private long unit;

        public Cow(int lane, long unit) {
            this.lane = lane;
            this.unit = unit;
        }
    }

    public static void setStandardInput() {

        String input = "5 2\n" +
                "1 2\n" +
                "2 3";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}