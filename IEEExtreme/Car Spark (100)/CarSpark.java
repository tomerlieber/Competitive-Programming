import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class CarSpark {

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        int T = in.nextInt(); // Denotes number of test cases.
        in.skip("\n");

        for (int testNum = 0; testNum < T; testNum++) {

            int N = in.nextInt(); // The number of bookings John received
            in.skip("\n");

            Order[] orders = new Order[N];

            for (int i = 0; i < N; i++) {

                int Bs = in.nextInt(); // The booking start time
                int Be = in.nextInt(); // The booking end time
                int Ai = in.nextInt(); // The amount that the customer is willing to spend for the entire booking

                orders[i] = new Order(Bs, Be, Ai);
            }

            Arrays.sort(orders);

            int maxHour = 48;

            // Let revenue[i] denotes the maximum revenue John can generate by hour i.
            int[] revenue = new int[maxHour + 1];

            // Base case
            revenue[0] = 0;

            // Recursion case
            int i = 0;
            for (int hour = 1; hour <= maxHour; hour++) {

                while (i < orders.length && orders[i].Be == hour) {

                    int opt1 = orders[i].Ai + revenue[orders[i].Bs];// Process the i order.
                    int opt2 = revenue[hour - 1];                   // Skip the i order and stay with the same orders as last hour.
                    int opt3 = revenue[hour];                       // Skip the i order and stay with the same orders as now.

                    revenue[hour] = Math.max(Math.max(opt1, opt2), opt3);

                    // Advance to next order.
                    i++;
                }

                if (revenue[hour] == 0) {
                    revenue[hour] = revenue[hour - 1];
                }
            }

            System.out.println(revenue[maxHour]);
        }
    }

    public static class Order implements Comparable<Order> {

        private int Bs;
        private int Be;
        private int Ai;

        Order(int Bs, int Be, int Ai) {
            this.Bs = Bs;
            this.Be = Be;
            this.Ai = Ai;
        }

        @Override
        public int compareTo(Order o) {
            return Integer.compare(this.Be, o.Be);
        }
    }

    private static void setStandardInput() {

        String input = "2\n" +
                "4\n" +
                "1 2 100\n" +
                "2 3 200\n" +
                "3 4 1600\n" +
                "1 3 2100\n" +
                "3\n" +
                "1 10 2000\n" +
                "2 5 100\n" +
                "6 9 400";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}
