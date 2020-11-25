import java.io.*;

public class SubarraySumsI {

    public static void main(String[] args) throws IOException {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Reader in = new Reader();

        int n = in.nextInt(); // The array size
        int x = in.nextInt(); // The target sum

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int i = 0;
        int j = 0;
        int counter = 0;
        int currentSum = arr[i];

        while (j < n) {

            if (currentSum <= x) {

                if (currentSum == x) {
                    counter++;
                    currentSum -= arr[i];
                    i++;
                }

                j++;
                if (j >= n) {
                    break;
                }
                currentSum += arr[j];

            } else {
                currentSum -= arr[i];
                i++;
            }
        }

        System.out.println(counter);
    }

    private static void setStandardInput() {

        String input = "100 61\n" +
                "7 6 4 6 2 9 4 8 10 4 10 3 7 10 2 9 4 1 7 4 5 9 9 7 9 6 5 10 8 4 7 10 5 3 3 1 6 7 6 1 5 7 7 7 7 2 3 5 10 8 4 8 7 5 8 6 8 6 7 9 3 10 6 6 8 1 5 8 5 9 9 5 8 8 4 6 6 3 1 2 1 2 9 2 10 9 9 6 5 7 9 10 5 8 2 7 4 1 4 7";

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
