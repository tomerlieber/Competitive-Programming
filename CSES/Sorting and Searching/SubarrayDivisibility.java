import java.io.*;
import java.util.HashMap;

public class SubarrayDivisibility {

    public static void main(String[] args) throws IOException {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Reader in = new Reader();

        int n = in.nextInt(); // The array size

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        long counter = 0;

        // currSum holds the sum of all the entries(up to arr[i]) starting from arr[0]
        long curSum = 0;
        HashMap<Long, Long> aux = new HashMap<>();

        for (int i = 0; i < n; i++) {
            curSum += arr[i];

            // Check if the sum of the subarray from index 0 to index i is divided by n
            if (curSum % n == 0) {
                counter ++;
            }

            // as the module can be negative, taking modulo twice
            long positiveRem = ((curSum % n) + n) % n;

            // Check if there is a subarray from index different from 0 to index i that equal to curSum % n.
            if (aux.containsKey(positiveRem)) {
                counter += aux.get(positiveRem);
            }

            aux.put(positiveRem, aux.getOrDefault(positiveRem, 0L) + 1);
        }

        System.out.println(counter);
    }

    private static void setStandardInput() {

        String input = "4\n" +
                "5, 0, 2, 3";

        // Results:
        // 5
        // 5 0
        // 0
        // 5 0 2 3
        // 2 3
        // 2 3 0

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
