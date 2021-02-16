import java.io.*;

public class RangeMinimumQueriesI {

    private static int[] powerOfTwo;

    private static int[][] lookup;

    public static void main(String[] args) throws IOException {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Reader in = new Reader();

        int n = in.nextInt(); // The number of values
        int q = in.nextInt(); // The number of queries

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        // Calculate the values of: 2^0, 2^1, 2^2...
        powerOfTwo = new int[log2(n) + 1];
        for (int i = 0; i < powerOfTwo.length; i++) {
            powerOfTwo[i] = 1 << i;
        }

        // lookup[i][j] is going to store index of minimum value in arr[i..j]
        lookup = new int[n][log2(n) + 1];
        FillSparseTable(arr, n);

        for (int i = 1; i <= q; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;

            int minIndex = query(arr, a, b);
            int minValue = arr[minIndex];
            System.out.println(minValue);
        }
    }

    static int log2(int n) {
        return (int) (Math.log(n) / Math.log(2));
    }

    // Fills lookup array lookup[][] in bottom up manner.
    static void FillSparseTable(int arr[], int n) {

        // Initialize lookup table for the intervals with length 1
        for (int i = 0; i < n; i++) {
            lookup[i][0] = i;
        }

        // Compute values from smaller to bigger intervals
        for (int j = 1; j <= log2(n); j++) {
            // Compute minimum value for all intervals with size 2^j
            for (int i = 0; i + powerOfTwo[j] <= n; i++) {

                int firstHalfIndex = lookup[i][j - 1];
                int secondHalfIndex = lookup[i + powerOfTwo[j - 1]][j - 1];

                if (arr[firstHalfIndex] < arr[secondHalfIndex]) {
                    lookup[i][j] = firstHalfIndex;
                } else {
                    lookup[i][j] = secondHalfIndex;
                }
            }
        }
    }

    // Returns minimum of arr[a..b]
    static int query(int[] arr, int a, int b) {

        // Find highest power of 2 that is smaller than or equal to count of elements in given range. For [2, 10], j = 3
        int length = b - a + 1;
        int j = log2(length);

        // Compute minimum of the first 2^j elements with the last 2^j elements in the range [a, b]
        // For [2,10], we compare arr[lookup[0][3]] and arr[lookup[3][3]].
        int minIndexFirst = lookup[a][j];                       // The index of the minimum of the first 2^j elements in the range
        int minIndexLast = lookup[b - powerOfTwo[j] + 1][j];    // The index of the minimum of the last 2^j elements in the range
        if (arr[minIndexFirst] <= arr[minIndexLast]) {
            return minIndexFirst;
        } else {
            return minIndexLast;
        }
    }

    private static void setStandardInput() {

        String input = "8 4\n" +
                "3 2 4 5 1 1 5 3\n" +
                "2 4\n" +
                "5 6\n" +
                "1 8\n" +
                "3 3";

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
