import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class SumOfThreeValues {

    public static void main(String[] args) throws IOException {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Reader in = new Reader();

        int n = in.nextInt(); // The array size
        int x = in.nextInt(); // The target sum

        ArrayList<Pair> nums = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            nums.add(new Pair(in.nextLong(), i + 1));
        }

        Collections.sort(nums);

        // Fix the first element one by one and find the other two elements.
        for (int i = 0; i < n - 2; i++) {

            // To find the other two elements, start two index variables from
            // two corners of the array and move them toward each other
            int j = i + 1; // index of the first element in the remaining elements
            int k = n - 1; // index of the last element
            while (j < k) {

                if (nums.get(i).val + nums.get(j).val + nums.get(k).val == x) {

                    System.out.println(String.format("%d %d %d", nums.get(i).index, nums.get(j).index, nums.get(k).index));
                    return;
                }
                else if (nums.get(i).val + nums.get(j).val + nums.get(k).val < x) {
                    j++;
                }
                else { // A[i] + A[l] + A[r] > sum
                    k--;
                }
            }
        }

        System.out.println("IMPOSSIBLE");

    }

    static class Pair implements Comparable<Pair> {
        long val;
        int index;

        Pair(long val, int index) {
            this.val = val;
            this.index = index;
        }

        @Override
        public int compareTo(Pair o) {
            return Long.compare(val, o.val);
        }
    }

    private static void setStandardInput() {

        String input = "5 111\n" +
                "1, 100, 9, 55, 55";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String fileName) throws IOException {
            din = new DataInputStream(new FileInputStream(fileName));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}