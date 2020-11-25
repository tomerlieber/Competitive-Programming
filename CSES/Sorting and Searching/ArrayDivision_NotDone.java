import java.io.*;
import java.util.Arrays;

public class ArrayDivision_NotDone {

    public static void main(String[] args) throws IOException {

        // Uncomment the following line in order to simulate a specific input
        setStandardInput();

        Reader in = new Reader();

        int n = in.nextInt();
        int k = in.nextInt() - 1;

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }

        Pair[] gaps = new Pair[n - 1];
        for (int i = 0; i < n - 1; i++) {
            gaps[i] = new Pair(Math.abs(nums[i] - nums[i + 1]), i + 1);
        }

        Arrays.sort(gaps);

        int[] indeces = new int[k];
        for (int i = 0; i < k; i++) {
            indeces[i] = gaps[gaps.length - i - 1].index;
        }

        Arrays.sort(indeces);

        int maxSum = 0;
        int curSum = 0;

        int j = 0;
        for (int i = 0; i < n; i++) {

            if (j < k && i == indeces[j]) {

                j++;
                maxSum = Math.max(maxSum, curSum);
                curSum = 0;
            }

            curSum += nums[i];
        }

        if (curSum != 0) {
            maxSum = Math.max(maxSum, curSum);
        }

        System.out.println(maxSum);

    }

    private static void setStandardInput() {

        String input = "100 4\n" +
                "549 68 76 896 358 942 723 480 940 797 504 655 616 920 584 288 396 237 85 100 705 938 969 489 472 907 948 577 465 783 136 346 260 475 357 931 101 785 166 80 133 982 976 804 301 588 40 812 510 334 56 46 263 670 173 732 474 57 210 117 44 322 255 363 145 888 977 839 997 895 954 883 451 331 721 829 37 502 84 149 718 757 197 406 616 795 321 294 433 284 842 480 245 142 427 336 767 650 691 917";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
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
