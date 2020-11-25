import java.io.*;
import java.util.ArrayList;

public class MaximumSubarraySumII_NotDone {

    public static void main(String[] args) throws IOException {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Reader in = new Reader();

        int n = in.nextInt(); // The array size
        int a = in.nextInt(); // The minimum subarray length
        int b = in.nextInt(); // The maximum subarray length

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }

        // calculating prefix sum
        long[] pre = new long[n];
        pre[0] = nums[0];
        for (int i = 1; i < n; i++) {
            pre[i] = nums[i] + pre[i - 1];
        }

        ArrayList<Long> s1 = new ArrayList<>();

        s1.add(0L);

        int flag = 0;

        long ans = Long.MIN_VALUE;

        ans = Math.max(ans, pre[a - 1]);

        for (int i = a; i < n; i++) {

            // subarray too long
            if (i - b >= 0) {
                // Erase 0 from multiset once i=b
                if (flag == 0) {
                    s1.remove(0);
                    flag = 1;
                }
            }

            // subarray too short
            if (i - a >= 0) {
                s1.add(pre[i - a]);
            }

            // Find minimum value in multiset.
            ans = Math.max(ans, pre[i] - s1.get(0));

            // Erase pre[i-R]
            if (i - b >= 0) {
                s1.remove((Object) pre[i - b]);
            }

        }

        System.out.println(ans);

//        long maxSoFar = nums[0];
//        long currMax = nums[0];
//        int currLen = 1;
//
//        for (int i = 1; i < n; i++) {
//
//            if (currMax < 0) {
//                currMax = nums[i];
//                currLen = 1;
//            }
//            else {
//                currMax += nums[i];
//                currLen += 1;
//            }
//
//            if (a <= currLen && currLen <= b) {
//                maxSoFar = Math.max(maxSoFar, currMax);
//            }
//        }
//
//        System.out.println(maxSoFar);
    }

    private static void setStandardInput() {

        String input = "10 7 7\n" +
                "-22 0 78 -48 94 68 -7 -73 8 62";

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
