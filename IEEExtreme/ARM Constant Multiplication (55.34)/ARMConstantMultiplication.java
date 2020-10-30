import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ARMConstantMultiplication {

    public static void main(String[] args) {
        // Uncomment the following line in order to simulate a specific input
        setStandardInput();

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        long[] powerOf2 = new long[33];
        for (int i = 0; i < powerOf2.length; i++) {
            powerOf2[i] = 1 << i;
        }

        for (int testNum = 0; testNum < t; testNum++) {

            long C = in.nextLong();

            if (C == 0 || C == 1) {
                System.out.println("END");
                continue;
            }


            if ((C & (C - 1)) == 0) {

                int numOfBits = Math.abs(Long.toBinaryString(C).length() - 1);
                // MOV R0, R0, LSL #3
                System.out.println("MOV R0, R0, LSL #" + numOfBits);
            } else if (((C - 1) & (C - 2)) == 0) {

                int numOfBits = Math.abs(Long.toBinaryString(C).length() - 1);
                System.out.println("ADD R0, R0, R0, LSL #" + numOfBits);

            } else {

                long sum = 0;

                while (sum != C) {

                    long remainingValue = Math.abs(C - sum);

                    int numOfBits = Long.toBinaryString(remainingValue).length() - 1;

                    long add1 = powerOf2[numOfBits];
                    long add2 = powerOf2[numOfBits + 1];

                    long dis1 = Math.abs(sum + add1 - C);
                    long dis2 = Math.abs(sum + add2 - C);
                    long dis3 = Math.abs(sum - add1 - C);
                    long dis4 = Math.abs(sum - add2 - C);

                    ArrayList<Long> distances = new ArrayList<>();
                    distances.add(dis1);
                    distances.add(dis2);
                    distances.add(dis3);
                    distances.add(dis4);

                    Long minDistance = Long.MAX_VALUE;
                    int minIndex = -1;

                    for (int i = 0; i < distances.size(); i++) {
                        if (minDistance > distances.get(i)) {
                            minDistance = distances.get(i);
                            minIndex = i;
                        }
                    }

                    String dest = minDistance == 0 ? "R0" : "R1";

                    switch (minIndex) {
                        case 0: {
                            System.out.println(String.format("ADD %s, R1, R0, LSL #%s", dest, numOfBits));
                            sum += add1;
                            break;
                        }
                        case 1: {
                            System.out.println(String.format("ADD %s, R1, R0, LSL #%s", dest, (numOfBits + 1)));
                            sum += add2;
                            break;
                        }
                        case 2: {
                            System.out.println(String.format("SUB %s, R1, R0, LSL #%s", dest, numOfBits));
                            sum -= add1;
                            break;
                        }
                        case 3: {
                            System.out.println(String.format("SUB %s, R1, R0, LSL #%s", dest, (numOfBits + 1)));
                            sum -= add2;
                            break;
                        }
                    }
                }
            }

            System.out.println("END");
        }
    }

    private static void setStandardInput() {

        String input = "2\n" +
                "115\n" +
                "105";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}
